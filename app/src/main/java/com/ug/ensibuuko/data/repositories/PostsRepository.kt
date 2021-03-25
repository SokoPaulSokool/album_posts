package com.ug.ensibuuko.data.repositories

import com.ug.ensibuuko.data.database.entity.PostEntity
import com.ug.ensibuuko.data.models.Post
import com.ug.ensibuuko.data.repository.base.IRepository
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.service.PostsService
import javax.inject.Inject



abstract class BasePostsRepository(service: PostsService) : IRepository {

    abstract suspend fun getPosts(): ApiResponse<ArrayList<PostEntity>>

    abstract suspend fun getUserPosts(userId: Int): ApiResponse<ArrayList<PostEntity>>

    abstract suspend fun addPost(postEntity: PostEntity): ApiResponse<PostEntity>


}

class PostsRepository @Inject constructor(private val service: PostsService) : BasePostsRepository(service) {
    override suspend fun getPosts(): ApiResponse<ArrayList<PostEntity>> {
        return handleRequest { service.getPosts() }
    }

    override suspend fun getUserPosts(userId: Int): ApiResponse<ArrayList<PostEntity>> {
        return handleRequest { service.getUserPosts(userId) }
    }

    override suspend fun addPost(postEntity: PostEntity): ApiResponse<PostEntity> {
        return handleRequest { service.addPost(postEntity) }
    }


}