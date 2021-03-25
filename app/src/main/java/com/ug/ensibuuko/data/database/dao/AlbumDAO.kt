package com.ug.ensibuuko.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ug.ensibuuko.data.database.entity.AlbumEntity


@Dao
interface AlbumDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAlbum(album: AlbumEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlbum(album: List<AlbumEntity?>?)

    @Update
    fun updateAlbum(album: AlbumEntity)

    @Delete
    fun deleteAlbum(album: AlbumEntity?)

    @Query("SELECT * FROM albums")
    fun getAllAlbum() : LiveData<List<AlbumEntity>>

    @Query("SELECT * FROM albums WHERE userId = :userId")
    fun getUserAlbums(userId: Int) : LiveData<List<AlbumEntity>>

}