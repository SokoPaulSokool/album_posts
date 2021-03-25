package com.ug.ensibuuko.data.repository.base

import com.google.gson.stream.MalformedJsonException
import com.tranzkargo.mobile.util.logger.Logger
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.response.ErrorCode
import com.ug.ensibuuko.data.response.ErrorResponse
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import java.net.ConnectException

private const val TAG = "IRepository"

/**
 * Base interface to be implemented by all repositories
 */
interface IRepository {

    suspend fun <T> handleRequest(call: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val apiResponse = call.invoke()
            if (apiResponse.isSuccessful) {
                ApiResponse.Success(apiResponse.body())
            } else {
                handleFailureResponse(apiResponse.errorBody())
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            handleException(ex)
        }
    }

    fun <T> handleFailureResponse(response: ResponseBody?): ApiResponse<T> {
        response?.let {
            try {
            val jsonObject = JSONObject(it.string())

                var errorMessage = ""
                val keys = jsonObject.keys()

                while (keys.hasNext()) {
                    val key = keys.next()
                    if (jsonObject[key] is JSONArray) {
                        errorMessage = jsonObject.getJSONArray(key).getString(0)
                        if (errorMessage.contains("This field is required.")) {
                            errorMessage = "$key field is required."
                        }
                    } else {
                        errorMessage = jsonObject.getString(key)
                    }
                }


                return ApiResponse.Failure(ErrorResponse(ErrorCode.BAD_REQUEST, errorMessage))
            } catch (ex: Exception) {
                Logger.Debug(
                        "BaseRepository",
                        ex.message ?: "Server error"
                )
                return ApiResponse.Failure(ErrorResponse(ErrorCode.UNKNOWN, "Server error"))
            }
        }
        return ApiResponse.Failure(ErrorResponse(ErrorCode.UNKNOWN, "UNKNOWN ERROR"))
    }

    private fun <T> handleException(exception: Exception?): ApiResponse<T> {
        exception?.let {
            var message = exception.message.toString()
            val errorResponse =
                    when {
                        message.contains("<html>") -> {
                            ErrorResponse(ErrorCode.BAD_RESPONSE, "Server error")
                        }
                        exception is ConnectException -> {
                            ErrorResponse(ErrorCode.BAD_RESPONSE, message)
                        }
                        exception is MalformedJsonException -> {
                            ErrorResponse(ErrorCode.BAD_RESPONSE, message)
                        }
                        message == "timeout" -> {
                            ErrorResponse(ErrorCode.BAD_RESPONSE, message)
                        }
                        else -> {
                            ErrorResponse(ErrorCode.BAD_RESPONSE, message)
                        }
                    }
            return ApiResponse.Failure(errorResponse)
        }
        return ApiResponse.Failure(ErrorResponse(ErrorCode.BAD_RESPONSE, "failed"))
    }
}