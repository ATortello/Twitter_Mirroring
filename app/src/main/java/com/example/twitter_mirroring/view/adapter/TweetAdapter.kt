package com.example.twitter_mirroring.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.twitter_mirroring.R
import com.example.twitter_mirroring.model.TweetData
import java.text.SimpleDateFormat
import java.util.Calendar

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
        var ivProfilePicture_Feed = itemView.findViewById<ImageView>(R.id.ivProfilePicture_Feed)
        var ivVerifiedLogo_Feed = itemView.findViewById<ImageView>(R.id.ivVerifiedLogo_Feed)
        var photo1 = itemView.findViewById<ImageView>(R.id.ivTopLeftImage_Feed)
        var photo2 = itemView.findViewById<ImageView>(R.id.ivTopRightImage_Feed)
        var photo3 = itemView.findViewById<ImageView>(R.id.ivBottomLeftImage_Feed)
        var photo4 = itemView.findViewById<ImageView>(R.id.ivBottomRightImage_Feed)
        var tvCantLikes_Feed = itemView.findViewById<TextView>(R.id.tvCantLikes_Feed)
        var tvCantReplies_Feed = itemView.findViewById<TextView>(R.id.tvCantReplies_Feed)
        var tvCantRetweets_Feed = itemView.findViewById<TextView>(R.id.tvCantRetweets_Feed)
        var tvCantViews_Feed = itemView.findViewById<TextView>(R.id.tvCantViews_Feed)
        var tvPublicName_Feed = itemView.findViewById<TextView>(R.id.tvPublicName_Feed)
        var tvRealUsername_Feed = itemView.findViewById<TextView>(R.id.tvRealUsername_Feed)
        var tvTimePosted_Feed = itemView.findViewById<TextView>(R.id.tvTimePosted_Feed)
        var tvTweetContent_Feed = itemView.findViewById<TextView>(R.id.tvTweetContent_Feed)
        var cvMediaContent = itemView.findViewById<CardView>(R.id.cvMediaFeed)
        var llMediaFeed = itemView.findViewById<LinearLayout>(R.id.llMediaFeed)
    }

    //Here the data obtained from the source is matched with the frontend components
    override fun onBindViewHolder(p0: TweetAdapter.ViewHolder, p1: Int) {
        val tweet = listOfTweets[p1]

        //Setting images: Profile, verified logo (if applies) and media content
        Glide
            .with(p0.itemView.context)
            .load(tweet.profilePhoto)
            .apply(RequestOptions.circleCropTransform())
            .into(p0.ivProfilePicture_Feed)

        if(tweet.verifiedLogo) { p0.ivVerifiedLogo_Feed.visibility = View.VISIBLE }
        else { p0.ivVerifiedLogo_Feed.visibility = View.GONE }

        //Managing CardView grid to show images
        when(tweet.mediaCount) {
            1 -> { p0.cvMediaContent.visibility = View.VISIBLE
                Glide.with(p0.itemView.context).load(tweet.photoUrl1).into(p0.photo1) }

            2 -> { p0.cvMediaContent.visibility = View.VISIBLE
                p0.llMediaFeed.visibility = View.VISIBLE
                Glide.with(p0.itemView.context).load(tweet.photoUrl1).into(p0.photo1)
                Glide.with(p0.itemView.context).load(tweet.photoUrl2).into(p0.photo2) }

            3 -> { p0.cvMediaContent.visibility = View.VISIBLE
                p0.llMediaFeed.visibility = View.VISIBLE
                p0.photo4.visibility = View.VISIBLE
                Glide.with(p0.itemView.context).load(tweet.photoUrl1).into(p0.photo1)
                Glide.with(p0.itemView.context).load(tweet.photoUrl2).into(p0.photo2)
                Glide.with(p0.itemView.context).load(tweet.photoUrl3).into(p0.photo3) }

            4 -> { p0.cvMediaContent.visibility = View.VISIBLE
                p0.llMediaFeed.visibility = View.VISIBLE
                p0.photo3.visibility = View.VISIBLE
                p0.photo4.visibility = View.VISIBLE
                Glide.with(p0.itemView.context).load(tweet.photoUrl1).into(p0.photo1)
                Glide.with(p0.itemView.context).load(tweet.photoUrl2).into(p0.photo2)
                Glide.with(p0.itemView.context).load(tweet.photoUrl3).into(p0.photo3)
                Glide.with(p0.itemView.context).load(tweet.photoUrl4).into(p0.photo4)
            }
            else -> p0.cvMediaContent.visibility = View.GONE
        }

        //Setting texts:
        p0.tvCantLikes_Feed.text = tweet.cantLikes.toString()
        p0.tvCantReplies_Feed.text = tweet.cantReplies.toString()
        p0.tvCantRetweets_Feed.text = tweet.cantRetweetsAndReposts.toString()
        p0.tvCantViews_Feed.text = tweet.cantViews.toString()
        p0.tvPublicName_Feed.text = tweet.publicName
        p0.tvRealUsername_Feed.text = tweet.realUsername
        p0.tvTweetContent_Feed.text = tweet.tweetContent

        //Calculating time between system's current date and date get from Firestore
        val currentDateAndTime = Calendar.getInstance().time
        val diffInSeconds = (currentDateAndTime.time - tweet.hourAndDate.time)/1000

        //Setting date formats according to how long the tweets were posted
        val simpleDateFormat = when(diffInSeconds) {
            in 0..59 -> SimpleDateFormat("s")
            in 60..3599 -> SimpleDateFormat("m")
            in 3600..86399 -> SimpleDateFormat("h")
            in 86400..604799 -> SimpleDateFormat("d")
            in 604800..2678399 -> SimpleDateFormat("d MMM")
            else -> SimpleDateFormat("d MMM yy")
        }

        val cal = Calendar.getInstance()
        cal.time = tweet.hourAndDate

        //Setting texts to show depending the date's format previously configured
        val timeToDisplay = when(diffInSeconds) {
            in 0..59 -> "${simpleDateFormat.format(cal.time)}s"
            in 60..3599 -> "${simpleDateFormat.format(cal.time)}m"
            in 3600..86399 -> "${simpleDateFormat.format(cal.time)}h"
            in 86400..604799 -> "${simpleDateFormat.format(cal.time)}d"
            in 604800..2678399 -> (simpleDateFormat.format(cal.time)).toString()
            else -> (simpleDateFormat.format(cal.time)).toString()
        }

        p0.tvTimePosted_Feed.text = timeToDisplay
        //Format for detailed tweet page
        //val simpleDateFormatDetailed = SimpleDateFormat("hh:mm a '·' MMM d, yyyy")

        //On Click event
        p0.itemView.setOnClickListener { tweetListener.onTweetClicked(tweet, p1) }
    }

    fun updateData(data: List<TweetData>)
    {
        listOfTweets.clear()
        listOfTweets.addAll(data)
        notifyDataSetChanged()
    }
}