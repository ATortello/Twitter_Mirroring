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
        var ivProfilePictureFeed: ImageView? = itemView.findViewById(R.id.ivProfilePictureFeed)
        var ivVerifiedLogoFeed: ImageView? = itemView.findViewById(R.id.ivVerifiedLogoFeed)
        var photo1: ImageView? = itemView.findViewById(R.id.ivTopLeftImageFeed)
        var photo2: ImageView? = itemView.findViewById(R.id.ivTopRightImageFeed)
        var photo3: ImageView? = itemView.findViewById(R.id.ivBottomLeftImageFeed)
        var photo4: ImageView? = itemView.findViewById(R.id.ivBottomRightImageFeed)
        var tvCantLikesFeed: TextView? = itemView.findViewById(R.id.tvCantLikesFeed)
        var tvCantRepliesFeed: TextView? = itemView.findViewById(R.id.tvCantRepliesFeed)
        var tvCantRetweetsFeed: TextView? = itemView.findViewById(R.id.tvCantRetweetsFeed)
        var tvCantViewsFeed: TextView? = itemView.findViewById(R.id.tvCantViewsFeed)
        var tvPublicNameFeed: TextView? = itemView.findViewById(R.id.tvPublicNameFeed)
        var tvRealUsernameFeed: TextView? = itemView.findViewById(R.id.tvRealUsernameFeed)
        var tvTimePostedFeed: TextView? = itemView.findViewById(R.id.tvTimePostedFeed)
        var tvTweetContentFeed: TextView? = itemView.findViewById(R.id.tvTweetContentFeed)
        var cvMediaContent: CardView? = itemView.findViewById(R.id.cvMediaFeed)
        var llMediaFeed: LinearLayout? = itemView.findViewById(R.id.llMediaFeed)
    }

    //Here the data obtained from the source is matched with the frontend components
    override fun onBindViewHolder(p0: TweetAdapter.ViewHolder, p1: Int) {
        val tweet = listOfTweets[p1]

        /*Setting images: Profile, verified logo (if applies) and media content*/
        Glide
            .with(p0.itemView.context)
            .load(tweet.profilePhoto)
            .apply(RequestOptions.circleCropTransform())
            .into(p0.ivProfilePictureFeed!!)

        if(tweet.verifiedLogo) { p0.ivVerifiedLogoFeed!!.visibility = View.VISIBLE }
        else { p0.ivVerifiedLogoFeed!!.visibility = View.GONE }

        //Managing CardView grid to show images
        when(tweet.mediaCount) {
            1 -> { p0.cvMediaContent!!.visibility = View.VISIBLE
                Glide.with(p0.itemView.context).load(tweet.photoUrl1).into(p0.photo1!!) }

            2 -> { p0.cvMediaContent!!.visibility = View.VISIBLE
                p0.llMediaFeed!!.visibility = View.VISIBLE
                Glide.with(p0.itemView.context).load(tweet.photoUrl1).into(p0.photo1!!)
                Glide.with(p0.itemView.context).load(tweet.photoUrl2).into(p0.photo2!!) }

            3 -> { p0.cvMediaContent!!.visibility = View.VISIBLE
                p0.llMediaFeed!!.visibility = View.VISIBLE
                p0.photo4!!.visibility = View.VISIBLE
                Glide.with(p0.itemView.context).load(tweet.photoUrl1).into(p0.photo1!!)
                Glide.with(p0.itemView.context).load(tweet.photoUrl2).into(p0.photo2!!)
                Glide.with(p0.itemView.context).load(tweet.photoUrl3).into(p0.photo3!!) }

            4 -> { p0.cvMediaContent!!.visibility = View.VISIBLE
                p0.llMediaFeed!!.visibility = View.VISIBLE
                p0.photo3!!.visibility = View.VISIBLE
                p0.photo4!!.visibility = View.VISIBLE
                Glide.with(p0.itemView.context).load(tweet.photoUrl1).into(p0.photo1!!)
                Glide.with(p0.itemView.context).load(tweet.photoUrl2).into(p0.photo2!!)
                Glide.with(p0.itemView.context).load(tweet.photoUrl3).into(p0.photo3!!)
                Glide.with(p0.itemView.context).load(tweet.photoUrl4).into(p0.photo4!!)
            }
            else -> p0.cvMediaContent!!.visibility = View.GONE
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

        /*var diffInDays = (diffInSeconds / 86400).toInt()
        println("Log ARTD - Difference: ${diffInDays} days")*/
        //Setting date formats according to how long the tweets were posted
        val pattern = when(diffInSeconds) {
            in 0..59 -> "s"
            in 60..3599 -> "m"
            in 3600..86399 -> "h"
            in 86400..604799 -> "d"
            in 604800..2678399 -> "d MMM"
            else -> "d MMM yy"
        }
        /*println("Log ARTD - Pattern selected: $pattern")*/

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
        /*println("Log ARTD - Time to display: $timeToDisplay")*/

        p0.tvTimePostedFeed!!.setText(" · $timeToDisplay")
        //Format for detailed tweet page
        //val simpleDateFormatDetailed = SimpleDateFormat("hh:mm a '·' MMM d, yyyy")

        //On Click event
        p0.itemView.setOnClickListener { tweetListener.onTweetClicked(tweet, p1) }

    }

    //Difference between the current date and the post's dates from Firestore
    fun timeGap(firestoreDate: Date, simpleDateFormat: SimpleDateFormat) : Int =
        (simpleDateFormat.format(System.currentTimeMillis()).toInt()) - (simpleDateFormat.format(firestoreDate).toInt())

    fun updateData(data: List<TweetData>)
    {
        listOfTweets.clear()
        listOfTweets.addAll(data)
        notifyDataSetChanged()
    }
}