package com.ug.ensibuuko.ui.posts

import android.app.Application
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ug.ensibuuko.R
import com.ug.ensibuuko.data.database.dao.PostDAO
import com.ug.ensibuuko.data.database.entity.CommentEntity
import com.ug.ensibuuko.data.database.entity.PostEntity
import com.ug.ensibuuko.util.viewModel.CustomViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_post_details.*
import kotlinx.android.synthetic.main.item_post.*
import kotlinx.android.synthetic.main.item_post.body
import kotlinx.android.synthetic.main.item_post.title
import kotlinx.android.synthetic.main.posts_fragment.*
import net.idik.lib.slimadapter.SlimAdapter
import net.idik.lib.slimadapter.SlimInjector
import net.idik.lib.slimadapter.viewinjector.IViewInjector
import javax.inject.Inject


class PostDetailsFragment : DaggerFragment() {
    @Inject
    lateinit var applicationContext: Application

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var postsViewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        postsViewModel = ViewModelProvider(activity as AppCompatActivity,viewModelFactory).get(PostsViewModel::class.java)
        var selectedPost: PostEntity? = postsViewModel.getSelectedPost().value
        postsViewModel.fetchComments(selectedPost?.id!!);

        title.text = selectedPost?.title ?: ""
        body.text = selectedPost?.body ?: ""

        sendBTN.setOnClickListener(View.OnClickListener { v ->
           var comment =  commentET.text.toString().trim();
            if(!TextUtils.isEmpty(comment)){
                postsViewModel.addComment(comment,selectedPost?.id!!)
                commentET.setText("")
            }
        })

        commentsRecyclerView.layoutManager = LinearLayoutManager(context)

        var slimAdapter: SlimAdapter = SlimAdapter.create()
            .register(R.layout.item_comment, SlimInjector<CommentEntity>{ data: CommentEntity, injector: IViewInjector<IViewInjector<*>> ->
                var nameTV = injector.findViewById<TextView>(R.id.nameTV)
                var emailTV = injector.findViewById<TextView>(R.id.emailTV)
                var bodyTV = injector.findViewById<TextView>(R.id.bodyTV)
                var parentView = injector.findViewById<View>(R.id.parentView)
                nameTV.text = data.name
                emailTV.text = data.email
                bodyTV.text = data.body

            }).attachTo(commentsRecyclerView)


        postsViewModel.getDBComments().observe(viewLifecycleOwner, Observer { comments: List<CommentEntity> ->
            slimAdapter.updateData(comments);
            commentsRecyclerView.scrollToPosition(comments.size-1)
        })

    }


}