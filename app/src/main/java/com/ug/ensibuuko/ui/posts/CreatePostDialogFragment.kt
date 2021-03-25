package com.ug.ensibuuko.ui.posts

import android.app.Application
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.ug.ensibuuko.R
import com.ug.ensibuuko.util.viewModel.CustomViewModelFactory
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.fragment_create_post_dialog.*
import kotlinx.android.synthetic.main.fragment_create_post_dialog.view.*
import javax.inject.Inject


class CreatePostDialogFragment : DaggerDialogFragment() {
    @Inject
    lateinit var applicationContext: Application

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    private lateinit var postsViewModel: PostsViewModel

    lateinit var rootView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder? = activity?.let { AlertDialog.Builder(it) }

        rootView = activity?.layoutInflater?.inflate(R.layout.fragment_create_post_dialog, null)!!

        lateinit var dialog: Dialog
        if (builder != null) {
            builder.setView(rootView)
            dialog = builder.create()
        }
        var win: Window? = dialog.window
        var ww: WindowManager.LayoutParams? = win?.attributes
        if (ww != null) {
            ww.gravity = Gravity.CENTER
            ww.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND
            if (win != null) {
                win.attributes=ww
            }
        }

        return dialog
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        postsViewModel = ViewModelProvider(activity as AppCompatActivity,viewModelFactory).get(PostsViewModel::class.java)


         rootView.submitBTN.setOnClickListener(View.OnClickListener { v ->
             val title =  rootView.titleET.text.toString().trim()
             val body =  rootView.bodyET.text.toString().trim()
             if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body) ){
                 postsViewModel.addPost(title,body)
                 dismiss()
             }else{
                 Toast.makeText(context,"Some fields are missing",Toast.LENGTH_SHORT).show()
             }
         })
    }


}