package com.ug.ensibuuko.data.usecase

import com.ug.ensibuuko.data.database.DBExecutor
import com.ug.ensibuuko.data.database.dao.PostDAO
import com.ug.ensibuuko.data.database.entity.PostEntity
import com.ug.ensibuuko.data.mapper.GeneralMapper
import com.ug.ensibuuko.data.models.Post
import com.ug.ensibuuko.data.repositories.PostsRepository
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.usecase.base.BaseUseCase
import javax.inject.Inject


class PostsUseCase @Inject constructor(private val repository: PostsRepository, private val mapper: GeneralMapper<PostEntity>, private val listMapper: GeneralMapper<ArrayList<PostEntity>>, private  val postDAO: PostDAO) : BaseUseCase(repository) {

    suspend fun getPosts(): ApiResponse<ArrayList<PostEntity>> {
        val response = repository.getPosts()

//        DBExecutor.IOThread({ postDAO.addPost(PostEntity(2,"title","my body"))  })

        return listMapper.map(response)!!
    }

    suspend fun addPost(postEntity: PostEntity): ApiResponse<PostEntity> {
        val response = repository.addPost(postEntity)
        return mapper.map(response)!!
    }

    suspend fun getUserPosts(userId: Int): ApiResponse<ArrayList<PostEntity>> {
        val response = repository.getUserPosts(userId)
        return listMapper.map(response)!!
    }

}
