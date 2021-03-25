package com.ug.ensibuuko.data.models


data class Address(
    var street: String?,
    var suit: String?,
    var city: String?,
    var zipcode: String?,
    var geo: Geo?

) {

    constructor() :
            this(null, null, null, null, null)

    override fun toString(): String {
        return "Address(street=$street, suit=$suit, city=$city, zipcode=$zipcode, geo=${geo.toString()} )"
    }

}