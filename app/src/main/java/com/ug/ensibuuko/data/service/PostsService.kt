package com.ug.ensibuuko.data.service

import com.ug.ensibuuko.data.database.entity.PostEntity
import com.ug.ensibuuko.data.models.Post
import retrofit2.Response
import retrofit2.http.*

interface PostsService {

    @GET("/posts")
    suspend fun getPosts(): Response<ArrayList<PostEntity>>

    @GET("/posts")
    suspend fun getUserPosts(@Query("userId") userId: Int): Response<ArrayList<PostEntity>>

    @POST("/posts")
    suspend fun addPost(@Body postEntity: PostEntity): Response<PostEntity>
}