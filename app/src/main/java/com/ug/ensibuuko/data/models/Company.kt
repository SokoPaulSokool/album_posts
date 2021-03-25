package com.ug.ensibuuko.data.models

data class Company(
    val name: String?,
    val catchPhrase: String?,
    var bs: String?

) {

    constructor() :
            this(null, null, null)

    override fun toString(): String {
        return "Company(name=$name, catchPhrase=$catchPhrase, bs=$bs )"
    }

}