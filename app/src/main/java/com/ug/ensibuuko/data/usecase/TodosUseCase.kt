package com.ug.ensibuuko.data.usecase

import com.ug.ensibuuko.data.mapper.GeneralMapper
import com.ug.ensibuuko.data.models.Todo
import com.ug.ensibuuko.data.repositories.TodosRepository
import com.ug.ensibuuko.data.response.ApiResponse
import com.ug.ensibuuko.data.usecase.base.BaseUseCase
import javax.inject.Inject


class TodosUseCase @Inject constructor(private val repository: TodosRepository, private val generalMapper: GeneralMapper<ArrayList<Todo>>) : BaseUseCase(repository) {

    suspend fun getTodos(): ApiResponse<ArrayList<Todo>> {
        val response = repository.getTodos()
        return generalMapper.map(response)!!
    }

}
