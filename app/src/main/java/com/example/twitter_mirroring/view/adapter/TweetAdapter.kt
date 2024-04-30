package com.example.twitter_mirroring.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.noTransformation
import com.example.twitter_mirroring.R
import com.example.twitter_mirroring.model.TweetData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class TweetAdapter(val tweetListener: TweetListener) : RecyclerView.Adapter<TweetAdapter.ViewHolder>() {

    //Define the array list of tweet which will be shown on screen
    var listOfTweets = ArrayList<TweetData>()

    //Size of list of tweets to be displayed
    override fun getItemCount() = listOfTweets.size

    //Here the re-usable component for the data list which will be shown to the user is defined
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ViewHolder(
        LayoutInflater
            .from(p0.context)
            .inflate(R.layout.item_general_tweet, p0, false))

    //With the this class the frontend and the logic are connected
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val ivProfilePictureFeed: ImageView? = itemView.findViewById(R.id.ivProfilePictureFeed)
        val ivVerifiedLogoFeed: ImageView? = itemView.findViewById(R.id.ivVerifiedLogoFeed)
        val photo1: ImageView? = itemView.findViewById(R.id.ivTopLeftImageFeed)
        val photo2: ImageView? = itemView.findViewById(R.id.ivTopRightImageFeed)
        val photo3: ImageView? = itemView.findViewById(R.id.ivBottomLeftImageFeed)
        val photo4: ImageView? = itemView.findViewById(R.id.ivBottomRightImageFeed)
        val video1: VideoView? = itemView.findViewById(R.id.ivTopLeftVideoFeed)
        val video2: VideoView? = itemView.findViewById(R.id.ivTopRightVideoFeed)
        val video3: VideoView? = itemView.findViewById(R.id.ivBottomLeftVideoFeed)
        val video4: VideoView? = itemView.findViewById(R.id.ivBottomRightVideoFeed)
        val tvCantLikesFeed: TextView? = itemView.findViewById(R.id.tvCantLikesFeed)
        val tvCantRepliesFeed: TextView? = itemView.findViewById(R.id.tvCantRepliesFeed)
        val tvCantRetweetsFeed: TextView? = itemView.findViewById(R.id.tvCantRetweetsFeed)
        val tvCantViewsFeed: TextView? = itemView.findViewById(R.id.tvCantViewsFeed)
        val tvPublicNameFeed: TextView? = itemView.findViewById(R.id.tvPublicNameFeed)
        val tvRealUsernameFeed: TextView? = itemView.findViewById(R.id.tvRealUsernameFeed)
        val tvTimePostedFeed: TextView? = itemView.findViewById(R.id.tvTimePostedFeed)
        val tvTweetContentFeed: TextView? = itemView.findViewById(R.id.tvTweetContentFeed)
        val cvMediaContent: CardView? = itemView.findViewById(R.id.cvMediaFeed)
        val llMediaFeed: LinearLayout? = itemView.findViewById(R.id.llMediaFeed)

        init {
            cvMediaContent!!.visibility = View.GONE
            llMediaFeed!!.visibility = View.GONE
            photo1!!.visibility = View.GONE
            photo2!!.visibility = View.GONE
            photo3!!.visibility = View.GONE
            photo4!!.visibility = View.GONE
            video1!!.visibility = View.GONE
            video2!!.visibility = View.GONE
            video3!!.visibility = View.GONE
            video4!!.visibility = View.GONE
        }
    }

    //Here the data obtained from the source is matched with the frontend components
    override fun onBindViewHolder(p0: TweetAdapter.ViewHolder, p1: Int) {
        val tweet = listOfTweets[p1]
        val visibility = View.VISIBLE
        val context = p0.itemView.context
        val circleTransformation = RequestOptions.circleCropTransform()
        val noTransformation = noTransformation()
        val fileType : Array<String> = arrayOf("","","","")
        val message = "ARTD - No image/gif or video link. Please check!"

        /*Setting images: Profile, verified logo (if applies) and media content*/
        showImage(context, tweet.profilePhoto, circleTransformation, p0.ivProfilePictureFeed)

        //Showing logo or not
        if(tweet.verifiedLogo) p0.ivVerifiedLogoFeed!!.visibility = View.VISIBLE
        else p0.ivVerifiedLogoFeed!!.visibility = View.GONE

        //Managing CardView elements to show images get from Firestore
        if (!tweet.hasMedia) p0.cvMediaContent!!.visibility = View.GONE
        else {  p0.cvMediaContent!!.visibility = visibility
            fileType[0] = mediaSelector(tweet.photoUrl1)
            fileType[1] = mediaSelector(tweet.photoUrl2)
            fileType[2] = mediaSelector(tweet.photoUrl3)
            fileType[3] = mediaSelector(tweet.photoUrl4)

            when (fileType[0]) {
                "img/gif" -> p0.photo1!!.visibility = visibility
                "video" -> p0.video1!!.visibility = visibility
                else -> print(message) }

            //Managing CardView grid to show images
            when(tweet.mediaCount) {
                1 -> when (fileType[0]) {
                    "img/gif" -> showImage(context, tweet.photoUrl1, noTransformation, p0.photo1)
                    "video" -> showVideo(tweet.photoUrl1, p0.video1)
                    else -> print(message) }

                2 -> { p0.llMediaFeed!!.visibility = visibility
                    when (fileType[0]) {
                        "img/gif" -> showImage(context, tweet.photoUrl1, noTransformation, p0.photo1)
                        "video" -> showVideo(tweet.photoUrl1, p0.video1)
                        else -> print(message) }

                    when (fileType[1]) {
                        "img/gif" -> { p0.photo2!!.visibility = visibility
                            showImage(context, tweet.photoUrl2, noTransformation, p0.photo2) }
                        "video" -> { p0.video2!!.visibility = visibility
                            showVideo(tweet.photoUrl2, p0.video2) }
                        else -> print(message) } }

                3 -> { p0.llMediaFeed!!.visibility = visibility
                    when (fileType[0]) {
                        "img/gif" -> showImage(context, tweet.photoUrl1, noTransformation, p0.photo1)
                        "video" -> showVideo(tweet.photoUrl1, p0.video1)
                        else -> print(message) }

                    when (fileType[1]) {
                        "img/gif" -> { p0.photo2!!.visibility = visibility
                            showImage(context, tweet.photoUrl2, noTransformation, p0.photo2) }
                        "video" -> { p0.video2!!.visibility = visibility
                            showVideo(tweet.photoUrl2, p0.video2) }
                        else -> print(message) }

                    when (fileType[2]) {
                        "img/gif" -> { p0.photo4!!.visibility = visibility
                            showImage(context, tweet.photoUrl3, noTransformation, p0.photo4) }
                        "video" -> { p0.video4!!.visibility = visibility
                            showVideo(tweet.photoUrl3, p0.video4) }
                        else -> print(message) } }

                4 -> { p0.llMediaFeed!!.visibility = visibility
                    when (fileType[0]) {
                        "img/gif" -> showImage(context, tweet.photoUrl1, noTransformation, p0.photo1)
                        "video" -> showVideo(tweet.photoUrl1, p0.video1)
                        else -> print(message) }

                    when (fileType[1]) {
                        "img/gif" -> { p0.photo2!!.visibility = visibility
                            showImage(context, tweet.photoUrl2, noTransformation, p0.photo2) }
                        "video" -> { p0.video2!!.visibility = visibility
                            showVideo(tweet.photoUrl2, p0.video2) }
                        else -> print(message) }

                    when (fileType[2]) {
                        "img/gif" -> { p0.photo3!!.visibility = visibility
                            showImage(context, tweet.photoUrl3, noTransformation, p0.photo3) }
                        "video" -> { p0.video3!!.visibility = visibility
                            showVideo(tweet.photoUrl3, p0.video3) }
                        else -> print(message) }

                    when (fileType[3]) {
                        "img/gif" -> { p0.photo4!!.visibility = visibility
                            showImage(context, tweet.photoUrl4, noTransformation, p0.photo4) }
                        "video" -> { p0.video4!!.visibility = visibility
                            showVideo(tweet.photoUrl4, p0.video4) }
                        else -> print(message) } }

                else -> p0.cvMediaContent.visibility = View.GONE
            }
        }

        /*Setting texts:*/
        p0.tvCantLikesFeed!!.text = tweet.cantLikes.toString()
        p0.tvCantRepliesFeed!!.text = tweet.cantReplies.toString()
        p0.tvCantRetweetsFeed!!.text = tweet.cantRetweetsAndReposts.toString()
        p0.tvCantViewsFeed!!.text = tweet.cantViews.toString()
        p0.tvPublicNameFeed!!.text = tweet.publicName
        p0.tvRealUsernameFeed!!.text = "@${tweet.realUsername}"
        p0.tvTweetContentFeed!!.text = tweet.tweetContent

        /*Calculating time between system's current date and post's dates from Firestore:*/
        val diffInSeconds = (System.currentTimeMillis() - tweet.hourAndDate.time)/1000
        val cal = Calendar.getInstance()
        cal.time = tweet.hourAndDate

            //Setting date formats according to how long the tweets were posted
            val pattern = when(diffInSeconds) {
                in 0..59 -> "s"
                in 60..3599 -> "m"
                in 3600..86399 -> "h"
                in 86400..604799 -> "d"
                in 604800..2678399 -> "d MMM"
                else -> "d MMM yy"
            }

            //Setting texts to show depending the date's format previously configured
            val simpleDateFormat = SimpleDateFormat(pattern)
            val timeToDisplay = when(pattern) {
                "s" -> "${ timeGap(cal.time, simpleDateFormat) }s"
                "m" -> "${ timeGap(cal.time, simpleDateFormat) }m"
                "h" -> "${ timeGap(cal.time, simpleDateFormat) }h"
                "d" -> "${ timeGap(cal.time, simpleDateFormat) }d"
                "d MMM" -> simpleDateFormat.format(cal.time)
                else -> simpleDateFormat.format(cal.time)
            }
            p0.tvTimePostedFeed!!.setText(" · $timeToDisplay")

        //On Click event
        p0.itemView.setOnClickListener { tweetListener.onTweetClicked(tweet, p1) }
    }

    fun mediaSelector(mediaURL: String) : String {
        return if (mediaURL != "") {
            if (!mediaURL.contains("mp4")) "img/gif" else "video"
        } else ""
    }

    fun showVideo(url: String?, videoView: VideoView?) {
        videoView!!.setVideoPath(url)
        videoView.start()
    }

    fun showImage(context: Context, url: String, requestOptions: RequestOptions = noTransformation(), imageView: ImageView?) {
        Glide.with(context)
             .load(url)
             .apply(requestOptions)
             .into(imageView!!)
    }

    //Difference between the current date and the post's dates from Firestore
    fun timeGap(firestoreDate: Date, simpleDateFormat: SimpleDateFormat) : Int =
        (simpleDateFormat.format(System.currentTimeMillis()).toInt()) - (simpleDateFormat.format(firestoreDate).toInt())

    fun updateData(data: List<TweetData>) {
        listOfTweets.clear()
        listOfTweets.addAll(data)
        notifyDataSetChanged()
    }
}