package com.example.twitter_mirroring.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.twitter_mirroring.R
import kotlinx.android.synthetic.main.fragment_home.*

class TweetWithGifDetailDialogFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tweet_with_gif_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view.context).
        load("https://pbs.twimg.com/profile_images/1501699435343974400/SiBbZLJV_400x400.jpg").apply(RequestOptions.circleCropTransform()).into(ivProfilePicture_Tweet_2)
    }
}