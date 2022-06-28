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

class HomeFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view.context).load("https://pbs.twimg.com/profile_images/3103894633/e0d179fc5739a20308331b432e4f3a51_400x400.jpeg").apply(RequestOptions.circleCropTransform()).into(ivProfilePicture_Tweet_1)
        Glide.with(view.context).load("https://pbs.twimg.com/profile_images/1501699435343974400/SiBbZLJV_400x400.jpg").apply(RequestOptions.circleCropTransform()).into(ivProfilePicture_Tweet_2)
        Glide.with(view.context).load("https://pbs.twimg.com/profile_images/1535378872065302528/cRRZT-pX_400x400.jpg").apply(RequestOptions.circleCropTransform()).into(ivProfilePicture_Tweet_3)
        Glide.with(view.context).load("https://pbs.twimg.com/profile_images/1541524005349367811/Tt23gJUo_400x400.jpg").apply(RequestOptions.circleCropTransform()).into(ivProfilePicture_Tweet_4)

        //Accessing to detail on
        llFeedTweet_1.setOnClickListener { findNavController().navigate(R.id.action_navHomeFragment_to_tweetOnlyTextDetailDialogFragment) }

//        llFeedTweet_2.setOnClickListener { findNavController().navigate(R.id.action_navHomeFragment_to_tweetWithGifDetailDialogFragment) }
//
//        llFeedTweet_3.setOnClickListener { findNavController().navigate(R.id.action_navHomeFragment_to_tweetWithOneImageDetailDialogFragment) }
//
//        llFeedTweet_4.setOnClickListener { findNavController().navigate(R.id.action_navHomeFragment_to_tweetWithFourImagesDetailDialogFragment) }
    }
}