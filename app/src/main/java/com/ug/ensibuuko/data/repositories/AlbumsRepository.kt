package com.ug.ensibuuko.data.repositories

import com.ug.ensibuuko.data.database.entity.AlbumEntity
import com.ug.ensibuuko.data.database.entity.PhotoEntity
import com.ug.ensibuuko.data.models.Album
import com.ug.ensibuuko.data.repository.base.IRepository
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.service.AlbumsService
import javax.inject.Inject

abstract class BaseAlbumsRepository(service: AlbumsService) : IRepository {

    abstract suspend fun getAlbums(): ApiResponse<ArrayList<AlbumEntity>>

    abstract suspend fun getUserAlbums(userId: Int): ApiResponse<ArrayList<AlbumEntity>>


}

class AlbumsRepository @Inject constructor(private val service: AlbumsService) : BaseAlbumsRepository(service) {

    override suspend fun getAlbums(): ApiResponse<ArrayList<AlbumEntity>> {
        return handleRequest { service.getAlbums() }
    }

    override suspend fun getUserAlbums(userId: Int): ApiResponse<ArrayList<AlbumEntity>> {
        return handleRequest { service.getUserAlbums(userId) }
    }


}