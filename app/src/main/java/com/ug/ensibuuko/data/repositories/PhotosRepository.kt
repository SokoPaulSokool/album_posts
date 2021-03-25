package com.ug.ensibuuko.data.repositories

import com.ug.ensibuuko.data.database.entity.PhotoEntity
import com.ug.ensibuuko.data.repository.base.IRepository
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.service.PhotosService
import javax.inject.Inject



abstract class BasePhotosRepository(service: PhotosService) : IRepository {

    abstract suspend fun getPhotos(): ApiResponse<ArrayList<PhotoEntity>>

    abstract suspend fun getAlbumPhotos(albumId: Int): ApiResponse<ArrayList<PhotoEntity>>


}

class PhotosRepository @Inject constructor(private val service: PhotosService) : BasePhotosRepository(service) {

    override suspend fun getPhotos(): ApiResponse<ArrayList<PhotoEntity>> {
        return handleRequest { service.getPhotos() }
    }


    override suspend fun getAlbumPhotos(albumId: Int): ApiResponse<ArrayList<PhotoEntity>> {
        return handleRequest { service.getAlbumPhotos(albumId) }
    }

}