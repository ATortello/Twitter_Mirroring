package com.example.twitter_mirroring.view.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.twitter_mirroring.R
import com.example.twitter_mirroring.databinding.FragmentTweetDetailDialogBinding
import com.example.twitter_mirroring.model.TweetData
import kotlinx.android.synthetic.main.fragment_tweet_detail_dialog.llMediaDetail
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class TweetDetailDialogFragment : DialogFragment() {

    //Implementing ViewBinding
    private var _binding: FragmentTweetDetailDialogBinding? = null
    private val binding get() = _binding!!

    //Setting screen style using FullScreenDialogStyle
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentTweetDetailDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Set icon for dialog fragment and action when closing Tweet's detailed information
        binding.toolbarTweetDetail.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_go_back)
        binding.toolbarTweetDetail.navigationIcon?.setTint(Color.WHITE)
        binding.toolbarTweetDetail.setNavigationOnClickListener { dismiss() }
        binding.toolbarTweetDetail.setTitle(R.string.toolbarTweetDetailTitle)
        binding.toolbarTweetDetail.setTitleTextColor(Color.WHITE)

        val tweet = arguments?.getSerializable("tweet") as TweetData
        val photo1: ImageView = binding.ivTopLeftImageDetail
        val photo2: ImageView = binding.ivTopRightImageDetail
        val photo3: ImageView = binding.ivBottomLeftImageDetail
        val photo4: ImageView = binding.ivBottomRightImageDetail
        val video1: VideoView = binding.ivTopLeftVideoDetail
        val video2: VideoView = binding.ivTopRightVideoDetail
        val video3: VideoView = binding.ivBottomLeftVideoDetail
        val video4: VideoView = binding.ivBottomRightVideoDetail
        val visibility = View.VISIBLE
        val context = requireContext()
        val circleTransformation = RequestOptions.circleCropTransform()
        val noTransformation = RequestOptions.noTransformation()
        val fileType : Array<String> = arrayOf("","","","")
        val message = "ARTD - No image/gif or video link. Please check!"

        /*Setting images: Profile, verified logo (if applies) and media content*/
        showImage(context, tweet.profilePhoto, circleTransformation, binding.ivProfilePictureDetail)

        //Showing logo or not
        if(tweet.verifiedLogo) binding.ivVerifiedLogoDetail.visibility = View.VISIBLE
        else binding.ivVerifiedLogoDetail.visibility = View.GONE

        //Managing CardView elements to show images get from Firestore
        if (!tweet.hasMedia) binding.cvMediaDetail.visibility = View.GONE
        else {  binding.cvMediaDetail.visibility = visibility
            fileType[0] = mediaSelector(tweet.photoUrl1)
            fileType[1] = mediaSelector(tweet.photoUrl2)
            fileType[2] = mediaSelector(tweet.photoUrl3)
            fileType[3] = mediaSelector(tweet.photoUrl4)

            when (fileType[0]) {
                "img/gif" -> photo1.visibility = visibility
                "video" -> video1.visibility = visibility
                else -> print(message) }

            //Managing CardView grid to show images
            when(tweet.mediaCount) {
                1 -> when (fileType[0]) {
                    "img/gif" -> showImage(context, tweet.photoUrl1, noTransformation, photo1)
                    "video" -> showVideo(tweet.photoUrl1, video1)
                    else -> print(message) }

                2 -> { llMediaDetail!!.visibility = visibility
                    when (fileType[0]) {
                        "img/gif" -> showImage(context, tweet.photoUrl1, noTransformation, photo1)
                        "video" -> showVideo(tweet.photoUrl1, video1)
                        else -> print(message) }

                    when (fileType[1]) {
                        "img/gif" -> { photo2.visibility = visibility
                            showImage(context, tweet.photoUrl2, noTransformation, photo2) }
                        "video" -> { video2.visibility = visibility
                            showVideo(tweet.photoUrl2, video2) }
                        else -> print(message) } }

                3 -> { llMediaDetail!!.visibility = visibility
                    when (fileType[0]) {
                        "img/gif" -> showImage(context, tweet.photoUrl1, noTransformation, photo1)
                        "video" -> showVideo(tweet.photoUrl1, video1)
                        else -> print(message) }

                    when (fileType[1]) {
                        "img/gif" -> { photo2.visibility = visibility
                            showImage(context, tweet.photoUrl2, noTransformation, photo2) }
                        "video" -> { video2.visibility = visibility
                            showVideo(tweet.photoUrl2, video2) }
                        else -> print(message) }

                    when (fileType[2]) {
                        "img/gif" -> { photo4.visibility = visibility
                            showImage(context, tweet.photoUrl3, noTransformation, photo4) }
                        "video" -> { video4.visibility = visibility
                            showVideo(tweet.photoUrl3, video4) }
                        else -> print(message) } }

                4 -> { llMediaDetail!!.visibility = visibility
                    when (fileType[0]) {
                        "img/gif" -> showImage(context, tweet.photoUrl1, noTransformation, photo1)
                        "video" -> showVideo(tweet.photoUrl1, video1)
                        else -> print(message) }

                    when (fileType[1]) {
                        "img/gif" -> { photo2.visibility = visibility
                            showImage(context, tweet.photoUrl2, noTransformation, photo2) }
                        "video" -> { video2.visibility = visibility
                            showVideo(tweet.photoUrl2, video2) }
                        else -> print(message) }

                    when (fileType[2]) {
                        "img/gif" -> { photo3.visibility = visibility
                            showImage(context, tweet.photoUrl3, noTransformation, photo3) }
                        "video" -> { video3.visibility = visibility
                            showVideo(tweet.photoUrl3, video3) }
                        else -> print(message) }

                    when (fileType[3]) {
                        "img/gif" -> { photo4.visibility = visibility
                            showImage(context, tweet.photoUrl4, noTransformation, photo4) }
                        "video" -> { video4.visibility = visibility
                            showVideo(tweet.photoUrl4, video4) }
                        else -> print(message) } }

                else -> binding.cvMediaDetail.visibility = View.GONE
            }
        }

        /*Setting texts:*/
        binding.tvCantLikesDetail.text = tweet.cantLikes.toString()
        binding.tvCantRetweetsDetail.text = tweet.cantRetweetsAndReposts.toString()
        binding.tvViewsDetail.text = tweet.cantViews.toString()
        binding.tvPublicNameDetail.text = tweet.publicName
        binding.tvRealUsernameDetail.text = "@${tweet.realUsername}"
        binding.tvTweetContentDetail.text = tweet.tweetContent

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
        binding.tvTimePostedDetail.setText(" · $timeToDisplay")
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

    fun showImage(context: Context, url: String, requestOptions: RequestOptions = RequestOptions.noTransformation(), imageView: ImageView?) {
        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(imageView!!)
    }

    //Difference between the current date and the post's dates from Firestore
    fun timeGap(firestoreDate: Date, simpleDateFormat: SimpleDateFormat) : Int =
        (simpleDateFormat.format(System.currentTimeMillis()).toInt()) - (simpleDateFormat.format(firestoreDate).toInt())

    //Setting dialog screen size to match parent both width and height
    override fun onStart() {
        super.onStart()
        dialog
            ?.window
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }
}