package com.ug.ensibuuko.data.models

data class Todo(
    val id: Int?,
    val userId: Int?,
    var title: String?,
    var completed: Boolean?

) {

    constructor() :
            this(null, null, null, null)

    override fun toString(): String {
        return "Todo(id=$id, userId=$userId, title=$title, completed=$completed )"
    }

}