package com.ug.ensibuuko.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ug.ensibuuko.data.database.entity.CommentEntity
import com.ug.ensibuuko.data.database.entity.PostEntity

@Dao
interface PostDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPost(post: PostEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(post: List<PostEntity?>?)

    @Update
    fun updatePost(post: PostEntity)

    @Delete
    fun deletePost(post: PostEntity?)

    @Query("SELECT * FROM posts")
    fun getAllPosts() : LiveData<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE userId = :userId")
    fun getUserPosts(userId: Int) : LiveData<List<PostEntity>>

}