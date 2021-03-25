package com.ug.ensibuuko.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ug.ensibuuko.data.database.entity.PhotoEntity


@Dao
interface PhotoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPhoto(photo: PhotoEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPhoto(photo: List<PhotoEntity?>?)

    @Update
    fun updatePhoto(photo: PhotoEntity)

    @Delete
    fun deletePhoto(photo: PhotoEntity?)

    @Query("SELECT * FROM photos")
    fun getAllPhoto() : LiveData<List<PhotoEntity>>

    @Query("SELECT * FROM photos WHERE albumId = :albumId")
    fun getAlbumPhotos(albumId: Int) : LiveData<List<PhotoEntity>>

}