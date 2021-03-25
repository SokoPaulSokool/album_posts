package com.ug.ensibuuko.data.models


data class Post(
    val id: Int?,
    val userId: Int?,
    var title: String?,
    var body: String?

) {

    constructor() :
            this(null, null, null, null)

    override fun toString(): String {
        return "Post(id=$id, userId=$userId, title=$title, body=$body )"
    }

}