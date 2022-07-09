package com.example.twitter_mirroring.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.twitter_mirroring.R
import kotlinx.android.synthetic.main.fragment_home.*

class TweetWithFourImagesDetailDialogFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_tweet_with_four_images_detail_dialog, container,false )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

//        Glide.with(view.context).load("https://pbs.twimg.com/profile_images/1541524005349367811/Tt23gJUo_400x400.jpg").apply(
//            RequestOptions.circleCropTransform()).into(ivProfilePicture_Tweet_4)
    }
}