package com.ug.ensibuuko.data.service

import com.ug.ensibuuko.data.database.entity.AlbumEntity
import com.ug.ensibuuko.data.database.entity.PhotoEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PhotosService {

    @GET("/photos")
    suspend fun getPhotos(): Response<ArrayList<PhotoEntity>>

    @GET("/photos")
    suspend fun getAlbumPhotos(@Query("albumId") albumId: Int): Response<ArrayList<PhotoEntity>>
}