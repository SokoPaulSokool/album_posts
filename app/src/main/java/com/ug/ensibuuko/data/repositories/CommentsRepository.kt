package com.ug.ensibuuko.data.repositories

import com.ug.ensibuuko.data.database.entity.CommentEntity
import com.ug.ensibuuko.data.repository.base.IRepository
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.service.CommentsService
import javax.inject.Inject


abstract class BaseCommentsRepository(service: CommentsService) : IRepository {

    abstract suspend fun getComments(): ApiResponse<ArrayList<CommentEntity>>

    abstract suspend fun getPostComments( postId: Int): ApiResponse<ArrayList<CommentEntity>>

    abstract suspend fun addComment( comment: CommentEntity): ApiResponse<CommentEntity>


}

class CommentsRepository @Inject constructor(private val service: CommentsService) :
    BaseCommentsRepository(service) {

    override suspend fun getComments(): ApiResponse<ArrayList<CommentEntity>> {
        return handleRequest { service.getComments() }
    }

    override suspend fun getPostComments( postId: Int): ApiResponse<ArrayList<CommentEntity>> {
        return handleRequest { service.getPostComments(postId) }
    }

    override suspend fun addComment(comment: CommentEntity): ApiResponse<CommentEntity> {
        return handleRequest { service.addComment(comment) }
    }

}