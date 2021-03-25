package com.ug.ensibuuko.dagger.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.ug.ensibuuko.ui.albums.AlbumsViewModel;
import com.ug.ensibuuko.ui.posts.PostsViewModel;
import com.ug.ensibuuko.ui.todos.TodosViewModel;
import com.ug.ensibuuko.util.viewModel.CustomViewModelFactory;
import com.ug.ensibuuko.util.viewModel.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindsCustomViewModelFactory(CustomViewModelFactory factory);


    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    abstract ViewModel bindsPostsViewModel(PostsViewModel postsViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel.class)
    abstract ViewModel bindsAlbumsViewModel(AlbumsViewModel albumsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TodosViewModel.class)
    abstract ViewModel bindsTodosViewModel(TodosViewModel todosViewModel);


}
