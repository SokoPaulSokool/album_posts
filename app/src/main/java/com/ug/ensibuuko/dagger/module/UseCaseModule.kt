package com.tranzkargo.mobile.dagger.module

import com.ug.ensibuuko.data.database.dao.PostDAO
import com.ug.ensibuuko.data.database.entity.AlbumEntity
import com.ug.ensibuuko.data.database.entity.CommentEntity
import com.ug.ensibuuko.data.database.entity.PhotoEntity
import com.ug.ensibuuko.data.database.entity.PostEntity
import com.ug.ensibuuko.data.mapper.GeneralMapper
import com.ug.ensibuuko.data.models.Album
import com.ug.ensibuuko.data.models.Post
import com.ug.ensibuuko.data.models.Todo
import com.ug.ensibuuko.data.repositories.*
import com.ug.ensibuuko.data.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideOrdersUseCase(
        repository: PostsRepository,
        listMapper: GeneralMapper<ArrayList<PostEntity>>,
        mapper: GeneralMapper<PostEntity>,
        postDAO: PostDAO
    ): PostsUseCase {
        return PostsUseCase(repository, mapper,listMapper, postDAO)
    }

    @Provides
    @Singleton
    fun provideAlbumsUseCase(
        repository: AlbumsRepository,
        mapper: GeneralMapper<AlbumEntity>,
        listMapper: GeneralMapper<ArrayList<AlbumEntity>>
    ): AlbumsUseCase {
        return AlbumsUseCase(repository, mapper, listMapper )
    }

    @Provides
    @Singleton
    fun provideTodosUseCase(
        repository: TodosRepository,
        mapper: GeneralMapper<ArrayList<Todo>>
    ): TodosUseCase {
        return TodosUseCase(repository, mapper)
    }

    @Provides
    @Singleton
    fun provideCommentsUseCase(
        repository: CommentsRepository,
        mapper: GeneralMapper<CommentEntity>,
        listMapper: GeneralMapper<ArrayList<CommentEntity>>
    ): CommentsUseCase {
        return CommentsUseCase(repository, mapper, listMapper)
    }
  @Provides
    @Singleton
    fun providePhotosUseCase(
        repository: PhotosRepository,
        mapper: GeneralMapper<PhotoEntity>,
        listMapper: GeneralMapper<ArrayList<PhotoEntity>>
    ): PhotosUseCase {
        return PhotosUseCase(repository, mapper, listMapper)
    }


}