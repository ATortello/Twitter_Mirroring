package com.example.twitter_mirroring.view.adapter

import com.example.twitter_mirroring.model.TweetData

interface TweetListener {
    fun onTweetClicked(tweet: TweetData, position: Int)
}