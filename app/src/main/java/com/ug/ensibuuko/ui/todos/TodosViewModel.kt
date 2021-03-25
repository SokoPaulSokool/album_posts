package com.ug.ensibuuko.ui.todos

import androidx.lifecycle.ViewModel
import com.ug.ensibuuko.data.usecase.PostsUseCase
import javax.inject.Inject

class TodosViewModel @Inject constructor(private val postsUseCase: PostsUseCase)  : ViewModel() {
    // TODO: Implement the ViewModel
}