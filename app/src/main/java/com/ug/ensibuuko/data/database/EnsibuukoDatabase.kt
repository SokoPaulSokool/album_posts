package com.ug.ensibuuko.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ug.ensibuuko.data.database.dao.AlbumDAO
import com.ug.ensibuuko.data.database.dao.CommentDAO
import com.ug.ensibuuko.data.database.dao.PhotoDAO
import com.ug.ensibuuko.data.database.dao.PostDAO
import com.ug.ensibuuko.data.database.entity.AlbumEntity
import com.ug.ensibuuko.data.database.entity.CommentEntity
import com.ug.ensibuuko.data.database.entity.PhotoEntity
import com.ug.ensibuuko.data.database.entity.PostEntity


@Database(entities = [PostEntity::class, CommentEntity::class, AlbumEntity::class, PhotoEntity::class], version = 3)
abstract class EnsibuukoDatabase : RoomDatabase() {
    abstract fun postDAO(): PostDAO
//
    abstract fun commentDAO(): CommentDAO

    abstract fun albumDAO(): AlbumDAO

    abstract fun photoDAO(): PhotoDAO

}