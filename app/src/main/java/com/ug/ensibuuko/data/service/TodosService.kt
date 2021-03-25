package com.ug.ensibuuko.data.service

import com.ug.ensibuuko.data.models.Todo
import retrofit2.Response
import retrofit2.http.GET

interface TodosService {
    @GET("/todos")
    suspend fun getTodos(): Response<ArrayList<Todo>>
}