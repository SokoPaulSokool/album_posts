package com.ug.ensibuuko.data.models

data class User(
    val id: Int?,
    var name: String?,
    var username: String?,
    var email: String?,
    var address: Address?,
    var phone: String?,
    var website: String?

) {

    constructor() :
            this(null, null, null, null, null, null, null)

    override fun toString(): String {
        return "User(id=$id, name=$name, username=$username, email=$email, address=$address, phone=$phone, website=$website  )"
    }

}