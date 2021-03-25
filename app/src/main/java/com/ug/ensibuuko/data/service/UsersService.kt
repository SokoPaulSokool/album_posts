package com.ug.ensibuuko.data.service

import com.ug.ensibuuko.data.models.User
import retrofit2.Response
import retrofit2.http.GET

interface UsersService {
    @GET("/todos")
    suspend fun getUsers(): Response<ArrayList<User>>
}