package com.ug.ensibuuko.data.usecase

import com.ug.ensibuuko.data.database.entity.CommentEntity
import com.ug.ensibuuko.data.mapper.GeneralMapper
import com.ug.ensibuuko.data.repositories.CommentsRepository
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.usecase.base.BaseUseCase
import javax.inject.Inject


class CommentsUseCase @Inject constructor(private val repository: CommentsRepository, private val mapper: GeneralMapper<CommentEntity>, private val listMapper: GeneralMapper<ArrayList<CommentEntity>>) : BaseUseCase(repository) {

    suspend fun getComments(): ApiResponse<ArrayList<CommentEntity>> {
        val response = repository.getComments()
        return listMapper.map(response)!!
    }
   suspend fun getPostComments(postId: Int): ApiResponse<ArrayList<CommentEntity>> {
        val response = repository.getPostComments(postId)
        return listMapper.map(response)!!
    }

  suspend fun addComment(comment: String, postId: Int): ApiResponse<CommentEntity> {
        val response = repository.addComment(CommentEntity(postId,"Paul","paul@gmail.com",comment))
        return mapper.map(response)!!
    }

}