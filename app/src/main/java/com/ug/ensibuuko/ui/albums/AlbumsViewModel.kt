package com.ug.ensibuuko.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ug.ensibuuko.data.database.entity.AlbumEntity
import com.ug.ensibuuko.data.database.entity.PhotoEntity
import com.ug.ensibuuko.data.database.entity.PostEntity
import com.ug.ensibuuko.data.response.ErrorCode
import com.ug.ensibuuko.data.usecase.AlbumsUseCase
import com.ug.ensibuuko.data.usecase.PhotosUseCase
import com.ug.ensibuuko.data.usecase.PostsUseCase
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(private val albumsUseCase: AlbumsUseCase, private val photosUseCase: PhotosUseCase) : ViewModel() {
    private var allAlbumsDb = MediatorLiveData<List<AlbumEntity>>()
    private var allPhotosDb = MediatorLiveData<List<PhotoEntity>>()

    private var selectedAlbum =MediatorLiveData<AlbumEntity>()

    fun fetchAlbums() {
        viewModelScope.launch {
            val response = albumsUseCase.getAlbums()
            if (response.isSuccessful) {
                allAlbumsDb.value = response.data!!
            } else {
                // notify error event
//                var message = "Failed to fetch posts"
//                if (response.errorResponse?.code == ErrorCode.UNKNOWN) {
//                    fetchPostsFailureEvent.value = message
//                } else {
//                    message = response.errorResponse.toString().toLowerCase(Locale.ROOT)
//                    fetchPostsFailureEvent.value = message
//                }
            }
        }
    }
    fun fetchPhotos(albumId: Int) {
        viewModelScope.launch {
            val response = photosUseCase.getAlbumPhotos(albumId)
            if (response.isSuccessful) {
                allPhotosDb.value = response.data!!
            } else {
                // notify error event
//                var message = "Failed to fetch posts"
//                if (response.errorResponse?.code == ErrorCode.UNKNOWN) {
//                    fetchPostsFailureEvent.value = message
//                } else {
//                    message = response.errorResponse.toString().toLowerCase(Locale.ROOT)
//                    fetchPostsFailureEvent.value = message
//                }
            }
        }
    }

    fun getAlbums(): LiveData<List<AlbumEntity>> {
        return allAlbumsDb
    }

    fun getPhotos(): LiveData<List<PhotoEntity>> {
        return allPhotosDb
    }
    fun setSelectedAlbum(album: AlbumEntity) {
         selectedAlbum.value = album
    }

    fun getSelectedAlbum(): LiveData<AlbumEntity> {
        return selectedAlbum
    }
}