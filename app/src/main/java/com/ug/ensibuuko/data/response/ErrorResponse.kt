package com.ug.ensibuuko.data.response

enum class ErrorCode {
    UNAUTHORIZED,
    NOT_FOUND,
    NO_NETWORK,
    BAD_RESPONSE,
    TIME_OUT,
    BAD_REQUEST,
    UNKNOWN
}

data class ErrorResponse(val code : ErrorCode = ErrorCode.UNKNOWN,
                         val message : String = "") {
    override fun toString(): String {
        return  "" + message
    }
}

//
//data class ErrorResponse(val code : ErrorCode = ErrorCode.UNKNOWN,
//                         val error : String = "",
//                         val message : String = "") {
//    override fun toString(): String {
//        return code.name + " - " + error + ": " + message
//    }
//}
//
