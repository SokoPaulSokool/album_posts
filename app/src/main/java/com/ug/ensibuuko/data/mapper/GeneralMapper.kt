package com.ug.ensibuuko.data.mapper

import com.tranzkargo.mobile.data.mapper.base.BaseMapper
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.response.ErrorResponse
import javax.inject.Inject


class GeneralMapper<T>  @Inject constructor()
    : BaseMapper<ApiResponse<T>, ApiResponse<T>>() {

    override fun map(entity: ApiResponse<T>?): ApiResponse<T>? {
        entity?.let {
            if(it.isSuccessful)
                return ApiResponse.Success(it.data)
            else
                return ApiResponse.Failure(it.errorResponse)
        }
        return ApiResponse.Failure(ErrorResponse())
    }
}