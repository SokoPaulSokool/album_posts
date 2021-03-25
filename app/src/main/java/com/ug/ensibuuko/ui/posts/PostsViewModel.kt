package com.ug.ensibuuko.ui.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tranzkargo.mobile.util.liveevent.SingleLiveEvent
import com.ug.ensibuuko.data.database.DBExecutor
import com.ug.ensibuuko.data.database.dao.CommentDAO
import com.ug.ensibuuko.data.database.dao.PostDAO
import com.ug.ensibuuko.data.database.entity.CommentEntity
import com.ug.ensibuuko.data.database.entity.PostEntity
import com.ug.ensibuuko.data.models.Post
import com.ug.ensibuuko.data.response.ErrorCode
import com.ug.ensibuuko.data.usecase.CommentsUseCase
import com.ug.ensibuuko.data.usecase.PostsUseCase
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val postsUseCase: PostsUseCase,
    private val commentsUseCase: CommentsUseCase,
    private val postDAO: PostDAO,
    private val commentDAO: CommentDAO
) : ViewModel() {
    private var allPosts = MediatorLiveData<ArrayList<PostEntity>>()
    private var allPostsDb = MediatorLiveData<List<PostEntity>>()
    private var allCommentsDb = MediatorLiveData<List<CommentEntity>>()
    var fetchPostsFailureEvent = SingleLiveEvent<String>()
    var selectedPost = MediatorLiveData<PostEntity>()

    fun fetchPosts() {


        viewModelScope.launch {
            val response = postsUseCase.getUserPosts(1)
            if (response.isSuccessful) {
                allPosts.value = response.data!!
                DBExecutor.IOThread(Runnable { postDAO.insertAllPosts(response.data!!) })
                allPostsDb.addSource(postDAO.getUserPosts(1),
                    { productEntities ->
                        allPostsDb.value = productEntities
                    })
            } else {
                // notify error event
                var message = "Failed to fetch posts"
                if (response.errorResponse?.code == ErrorCode.UNKNOWN) {
                    fetchPostsFailureEvent.value = message
                } else {
                    message = response.errorResponse.toString().toLowerCase(Locale.ROOT)
                    fetchPostsFailureEvent.value = message
                }
            }
        }
    }

    fun fetchComments(postId: Int) {

        viewModelScope.launch {
            val response = commentsUseCase.getPostComments(postId)
            if (response.isSuccessful) {
                DBExecutor.IOThread(Runnable { commentDAO.insertAllComments(response.data!!) })
                allCommentsDb.addSource(commentDAO.getPostComments(postId),
                    { productEntities ->
                        Log.e("", productEntities.toString())
                        allCommentsDb.value = productEntities
                    })
            } else {
                // notify error event
                var message = "Failed to fetch posts"
                if (response.errorResponse?.code == ErrorCode.UNKNOWN) {
                    fetchPostsFailureEvent.value = message
                } else {
                    message = response.errorResponse.toString().toLowerCase(Locale.ROOT)
                    fetchPostsFailureEvent.value = message
                }
            }
        }
    }

    fun addComment(comment: String, postId: Int) {

        viewModelScope.launch {
            val response = commentsUseCase.addComment(comment, postId)
            if (response.isSuccessful) {
                DBExecutor.IOThread(Runnable { commentDAO.addComment(response.data!!) })
                fetchComments(postId)
            } else {
                var message = "Failed to fetch posts"
                if (response.errorResponse?.code == ErrorCode.UNKNOWN) {
                    fetchPostsFailureEvent.value = message
                } else {
                    message = response.errorResponse.toString().toLowerCase(Locale.ROOT)
                    fetchPostsFailureEvent.value = message
                }
            }
        }
    }
    fun addPost(title: String, body: String) {

        viewModelScope.launch {
            val response = postsUseCase.addPost(PostEntity(1,title,body ))
            if (response.isSuccessful) {
                DBExecutor.IOThread(Runnable { postDAO.addPost(response.data!!) })
                fetchPosts()
            } else {
                var message = "Failed to fetch posts"
                if (response.errorResponse?.code == ErrorCode.UNKNOWN) {
                    fetchPostsFailureEvent.value = message
                } else {
                    message = response.errorResponse.toString().toLowerCase(Locale.ROOT)
                    fetchPostsFailureEvent.value = message
                }
            }
        }
    }

    fun getPosts(): LiveData<ArrayList<PostEntity>> {
        return allPosts
    }


    fun getDBPosts(): LiveData<List<PostEntity>> {
        return allPostsDb
    }

    fun getDBComments(): LiveData<List<CommentEntity>> {
        return allCommentsDb
    }

    fun setSelectedPost(postEntity: PostEntity) {
        selectedPost.value = postEntity
    }

    fun getSelectedPost(): LiveData<PostEntity> {
        return selectedPost
    }


}