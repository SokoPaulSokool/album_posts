package com.ug.ensibuuko.ui.todos

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.ug.ensibuuko.R
import com.ug.ensibuuko.util.viewModel.CustomViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TodosFragment : DaggerFragment() {
    @Inject
    lateinit var applicationContext: Application

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    companion object {
        fun newInstance() = TodosFragment()
    }

    private lateinit var viewModel: TodosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.todos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity as AppCompatActivity,viewModelFactory).get(
            TodosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}