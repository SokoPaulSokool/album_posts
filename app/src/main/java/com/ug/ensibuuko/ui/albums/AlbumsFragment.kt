package com.ug.ensibuuko.ui.albums

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.ug.ensibuuko.R
import com.ug.ensibuuko.data.database.entity.AlbumEntity
import com.ug.ensibuuko.data.database.entity.PostEntity
import com.ug.ensibuuko.util.viewModel.CustomViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.albums_fragment.*
import kotlinx.android.synthetic.main.posts_fragment.*
import net.idik.lib.slimadapter.SlimAdapter
import net.idik.lib.slimadapter.SlimInjector
import net.idik.lib.slimadapter.viewinjector.IViewInjector
import javax.inject.Inject

class AlbumsFragment : DaggerFragment() {
    @Inject
    lateinit var applicationContext: Application

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory


    private lateinit var viewModel: AlbumsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.albums_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity as AppCompatActivity,viewModelFactory).get(AlbumsViewModel::class.java)
        viewModel.fetchAlbums();

        albumsRV.layoutManager = GridLayoutManager(context,2)

        var slimAdapter: SlimAdapter = SlimAdapter.create()
            .register(R.layout.item_album, SlimInjector<AlbumEntity>{ data: AlbumEntity, injector: IViewInjector<IViewInjector<*>> ->
                var albumTitleTV = injector.findViewById<TextView>(R.id.albumTitleTV)
                albumTitleTV.text = data.title
                var parentView = injector.findViewById<View>(R.id.parentView)
//
                parentView.setOnClickListener(View.OnClickListener { v ->
                    viewModel.setSelectedAlbum(data);
                    Navigation.findNavController(v).navigate(R.id.photosFragment)
                })

            }).attachTo(albumsRV)

        viewModel.getAlbums().observe(viewLifecycleOwner, Observer { albums  ->
            slimAdapter.updateData(albums)
        })

    }

}