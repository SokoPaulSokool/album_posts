package com.ug.ensibuuko.data.models


data class Geo(
    var lat: String?,
    var lng: String?

) {

    constructor() :
            this(null, null)

    override fun toString(): String {
        return "Geo(lat=$lat, lng=$lng)"
    }

}