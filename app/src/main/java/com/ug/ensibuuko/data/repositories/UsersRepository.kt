package com.ug.ensibuuko.data.repositories

import com.ug.ensibuuko.data.models.User
import com.ug.ensibuuko.data.repository.base.IRepository
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.service.UsersService
import javax.inject.Inject

abstract class BaseUsersRepository(service: UsersService) : IRepository {

    abstract suspend fun getUsers(): ApiResponse<ArrayList<User>>

}

class UsersRepository @Inject constructor(private val service: UsersService) :
    BaseUsersRepository(service) {

    override suspend fun getUsers(): ApiResponse<ArrayList<User>> {
        return handleRequest { service.getUsers() }
    }

}