package com.ug.ensibuuko.ui.albums

import android.app.Application
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ug.ensibuuko.R
import com.ug.ensibuuko.data.database.entity.AlbumEntity
import com.ug.ensibuuko.data.database.entity.PhotoEntity
import com.ug.ensibuuko.util.viewModel.CustomViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.albums_fragment.*
import kotlinx.android.synthetic.main.fragment_photos.*
import net.idik.lib.slimadapter.SlimAdapter
import net.idik.lib.slimadapter.SlimInjector
import net.idik.lib.slimadapter.viewinjector.IViewInjector
import javax.inject.Inject


class PhotosFragment : DaggerFragment() {

    @Inject
    lateinit var applicationContext: Application

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory


    private lateinit var viewModel: AlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity as AppCompatActivity,viewModelFactory).get(AlbumsViewModel::class.java)
        // TODO: Use the ViewModel
        var selectedAlbum = viewModel.getSelectedAlbum().value
        viewModel.fetchPhotos(selectedAlbum!!.id);

        photosRV.layoutManager = GridLayoutManager(context,2)

        var slimAdapter: SlimAdapter = SlimAdapter.create()
            .register(R.layout.item_photo, SlimInjector<PhotoEntity>{ data: PhotoEntity, injector: IViewInjector<IViewInjector<*>> ->
                var photoTitleTV = injector.findViewById<TextView>(R.id.photoTitleTV)
                var photoIV = injector.findViewById<ImageView>(R.id.photoIV)
                photoTitleTV.text = data.title
                var parentView = injector.findViewById<View>(R.id.parentView)

                Glide.with(requireActivity())
                    .load(data.url)
                    .error(R.drawable.ic_baseline_photo_album)
                    .centerCrop()
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.e("","")
                            return  false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.e("","")
                            return  false
                        }

                    })
                    .into(photoIV)


                parentView.setOnClickListener(View.OnClickListener { v ->
//                    viewModel.setSelectedAlbum(data);
//                    Navigation.findNavController(v).navigate(R.id.photosFragment)
                })

            }).attachTo(photosRV)

        viewModel.getPhotos().observe(viewLifecycleOwner, Observer { photos  ->
            slimAdapter.updateData(photos)
        })
    }

}