package com.ug.ensibuuko.data.service

import com.ug.ensibuuko.data.database.entity.CommentEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommentsService {

    @GET("/comments")
    suspend fun getComments(): Response<ArrayList<CommentEntity>>

    @GET("/comments/")
    suspend fun getPostComments(@Query("postId") postId: Int): Response<ArrayList<CommentEntity>>

    @POST("/comments")
    suspend fun addComment(@Body comment: CommentEntity): Response<CommentEntity>
}