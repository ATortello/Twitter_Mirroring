package com.example.twitter_mirroring.model

import java.util.Date

class TweetData {
    var cantBookmarks: Long = 0
    var cantLikes: Long = 0
    var cantReplies: Long = 0
    var cantRetweetsAndReposts: Long = 0
    var cantViews: Long = 0
    var device: String = ""
    var hasMedia: Boolean = false
    lateinit var hourAndDate: Date
    var mediaCount: Int = 0
    lateinit var photoUrl1: String
    lateinit var photoUrl2: String
    lateinit var photoUrl3: String
    lateinit var photoUrl4: String
    lateinit var profilePhoto: String
    lateinit var publicName: String
    lateinit var realUsername: String
    lateinit var tweetContent: String
    var verifiedLogo: Boolean = false
}