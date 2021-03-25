package com.ug.ensibuuko.data.models

data class Album(
    val id: Int?,
    val userId: Int?,
    var title: String?

) {

    constructor() :
            this(null, null, null)

    override fun toString(): String {
        return "Album(id=$id, userId=$userId, title=$title )"
    }

}