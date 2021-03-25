package com.ug.ensibuuko.data.repositories

import com.ug.ensibuuko.data.models.Todo
import com.ug.ensibuuko.data.repository.base.IRepository
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.service.TodosService
import javax.inject.Inject

abstract class BaseTodosRepository(service: TodosService) : IRepository {

    abstract suspend fun getTodos(): ApiResponse<ArrayList<Todo>>

}

class TodosRepository @Inject constructor(private val service: TodosService) :
    BaseTodosRepository(service) {

    override suspend fun getTodos(): ApiResponse<ArrayList<Todo>> {
        return handleRequest { service.getTodos() }
    }


}