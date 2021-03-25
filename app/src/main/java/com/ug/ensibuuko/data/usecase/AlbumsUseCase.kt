package com.ug.ensibuuko.data.usecase

import com.ug.ensibuuko.data.database.entity.AlbumEntity
import com.ug.ensibuuko.data.mapper.GeneralMapper
import com.ug.ensibuuko.data.models.Album
import com.ug.ensibuuko.data.repositories.AlbumsRepository
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.usecase.base.BaseUseCase
import javax.inject.Inject



class AlbumsUseCase @Inject constructor(private val repository: AlbumsRepository, private val mapper: GeneralMapper<AlbumEntity>, private val listMapper: GeneralMapper<ArrayList<AlbumEntity>>) : BaseUseCase(repository) {

    suspend fun getAlbums(): ApiResponse<ArrayList<AlbumEntity>> {
        val response = repository.getAlbums()
        return listMapper.map(response)!!
    }

    suspend fun getUserAlbums(userId: Int): ApiResponse<ArrayList<AlbumEntity>> {
        val response = repository.getUserAlbums(userId)
        return listMapper.map(response)!!
    }

}
