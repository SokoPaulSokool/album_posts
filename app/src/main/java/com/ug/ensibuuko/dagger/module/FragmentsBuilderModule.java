package com.ug.ensibuuko.dagger.module;



import com.ug.ensibuuko.ui.albums.PhotosFragment;
import com.ug.ensibuuko.ui.posts.CreatePostDialogFragment;
import com.ug.ensibuuko.ui.posts.PostDetailsFragment;
import com.ug.ensibuuko.ui.todos.TodosFragment;
import com.ug.ensibuuko.ui.albums.AlbumsFragment;
import com.ug.ensibuuko.ui.posts.PostsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentsBuilderModule {


   @ContributesAndroidInjector
    abstract PostsFragment contributeViewPostsFragment();

   @ContributesAndroidInjector
    abstract AlbumsFragment contributeViewAlbumsFragment();

   @ContributesAndroidInjector
    abstract TodosFragment contributeViewTodosFragment();

   @ContributesAndroidInjector
    abstract PostDetailsFragment contributeViewPostDetailsFragment();

   @ContributesAndroidInjector
    abstract CreatePostDialogFragment contributeViewCreatePostDialogFragment();

   @ContributesAndroidInjector
    abstract PhotosFragment contributeViewPhotosFragment();

}
