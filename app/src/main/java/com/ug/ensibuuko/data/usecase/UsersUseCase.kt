package com.ug.ensibuuko.data.usecase

import com.ug.ensibuuko.data.mapper.GeneralMapper
import com.ug.ensibuuko.data.models.User
import com.ug.ensibuuko.data.repositories.UsersRepository
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.usecase.base.BaseUseCase
import javax.inject.Inject


class UsersUseCase @Inject constructor(private val repository: UsersRepository, private val generalMapper: GeneralMapper<ArrayList<User>>) : BaseUseCase(repository) {

    suspend fun getUsers(): ApiResponse<ArrayList<User>> {
        val response = repository.getUsers()
        return generalMapper.map(response)!!
    }

}
