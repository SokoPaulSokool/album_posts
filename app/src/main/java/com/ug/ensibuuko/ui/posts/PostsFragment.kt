package com.ug.ensibuuko.ui.posts

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ug.ensibuuko.R
import com.ug.ensibuuko.data.database.entity.PostEntity
import com.ug.ensibuuko.util.viewModel.CustomViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.posts_fragment.*
import net.idik.lib.slimadapter.SlimAdapter
import net.idik.lib.slimadapter.SlimInjector
import net.idik.lib.slimadapter.viewinjector.IViewInjector
import javax.inject.Inject

class PostsFragment : DaggerFragment() {
    @Inject
    lateinit var applicationContext: Application

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    companion object {
        fun newInstance() = PostsFragment()
    }

    private lateinit var postsViewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        postsViewModel = ViewModelProvider(activity as AppCompatActivity,viewModelFactory).get(PostsViewModel::class.java)
        postsViewModel.fetchPosts();

        addPostFAB.setOnClickListener(View.OnClickListener { v ->
            val fragmentTransaction =
                (activity as AppCompatActivity).getSupportFragmentManager().beginTransaction()
            val createPostDialogFragment = CreatePostDialogFragment()
            createPostDialogFragment.show(fragmentTransaction, "createPostDialogFragment")
        })


        postRecyclerView.layoutManager = LinearLayoutManager(context)
//        postRecyclerView.layoutManager = GridLayoutManager(context,2)
        var slimAdapter: SlimAdapter = SlimAdapter.create()
            .register(R.layout.item_post, SlimInjector<PostEntity>{ data: PostEntity, injector: IViewInjector<IViewInjector<*>> ->
               var title = injector.findViewById<TextView>(R.id.title)
               var body = injector.findViewById<TextView>(R.id.body)
               var parentView = injector.findViewById<View>(R.id.parentView)
                title.text = data.title
                body.text = data.body

                parentView.setOnClickListener(View.OnClickListener { v ->
                    postsViewModel.setSelectedPost(data);
                    Navigation.findNavController(v).navigate(R.id.postDetailsFragment)
                })

            }).attachTo(postRecyclerView)


        postsViewModel.getDBPosts().observe(viewLifecycleOwner, Observer { posts: List<PostEntity> ->
            slimAdapter.updateData(posts.reversed());
            postRecyclerView.scrollToPosition(0)
        })
    }

}