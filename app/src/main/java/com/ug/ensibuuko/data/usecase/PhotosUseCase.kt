package com.ug.ensibuuko.data.usecase

import com.ug.ensibuuko.data.database.entity.PhotoEntity
import com.ug.ensibuuko.data.mapper.GeneralMapper
import com.ug.ensibuuko.data.repositories.PhotosRepository
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.usecase.base.BaseUseCase
import javax.inject.Inject


class PhotosUseCase @Inject constructor(private val repository: PhotosRepository, private val mapper: GeneralMapper<PhotoEntity>, private val listMapper: GeneralMapper<ArrayList<PhotoEntity>>) : BaseUseCase(repository) {

    suspend fun getPhotos(): ApiResponse<ArrayList<PhotoEntity>> {
        val response = repository.getPhotos()
        return listMapper.map(response)!!
    }

    suspend fun getAlbumPhotos(albumId: Int): ApiResponse<ArrayList<PhotoEntity>> {
        val response = repository.getAlbumPhotos(albumId)
        return listMapper.map(response)!!
    }

}
