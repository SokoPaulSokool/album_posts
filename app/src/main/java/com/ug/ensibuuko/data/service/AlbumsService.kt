package com.ug.ensibuuko.data.service

import com.ug.ensibuuko.data.database.entity.AlbumEntity
import com.ug.ensibuuko.data.database.entity.PhotoEntity
import com.ug.ensibuuko.data.database.entity.PostEntity
import com.ug.ensibuuko.data.models.Album
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumsService {

    @GET("/albums")
    suspend fun getAlbums(): Response<ArrayList<AlbumEntity>>


    @GET("/albums")
    suspend fun getUserAlbums(@Query("userId") userId: Int): Response<ArrayList<AlbumEntity>>

}