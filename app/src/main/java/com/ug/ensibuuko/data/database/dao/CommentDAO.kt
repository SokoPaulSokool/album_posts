package com.ug.ensibuuko.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.FtsOptions.Order
import com.ug.ensibuuko.data.database.entity.CommentEntity


@Dao
interface CommentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addComment(comment: CommentEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllComments(order: List<CommentEntity?>?)

    @Update
    fun updateComment(comment: CommentEntity)

    @Delete
    fun deleteComment(comment: CommentEntity?)

    @Query("SELECT * FROM comments")
    fun getAllComments() : LiveData<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE postId = :postId")
    fun getPostComments(postId: Int) : LiveData<List<CommentEntity>>
}