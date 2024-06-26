package com.example.twitter_mirroring.view.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.twitter_mirroring.R
import com.example.twitter_mirroring.databinding.FragmentTweetDetailDialogBinding
import com.example.twitter_mirroring.model.TweetData
import java.text.SimpleDateFormat
import java.util.Calendar

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

        /*Set icon for dialog fragment and action when closing Tweet's detailed information*/
        binding.toolbarTweetDetail.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_go_back)
        binding.toolbarTweetDetail.navigationIcon?.setTint(Color.WHITE)
        binding.toolbarTweetDetail.setNavigationOnClickListener { dismiss() }
        binding.toolbarTweetDetail.setTitle(R.string.toolbarTweetDetailTitle)
        binding.toolbarTweetDetail.setTitleTextColor(Color.WHITE)

        /*With the this the frontend and the logic are connected*/
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
        val noVisibility = View.GONE
        val context = requireContext()
        val circleTransformation = RequestOptions.circleCropTransform()
        val noTransformation = RequestOptions.noTransformation()
        val fileType : Array<String> = arrayOf("","","","")
        val message = "ARTD - No image/gif or video link. Please check!"

        /*Setting initial visibility of elements*/
        binding.cvMediaDetail.visibility = noVisibility
        binding.llMediaDetail.visibility = noVisibility
        photo1.visibility = noVisibility
        photo2.visibility = noVisibility
        photo3.visibility = noVisibility
        photo4.visibility = noVisibility
        video1.visibility = noVisibility
        video2.visibility = noVisibility
        video3.visibility = noVisibility
        video4.visibility = noVisibility

        /*Setting images: Profile, verified logo (if applies) and media content*/
        showImage(context, tweet.profilePhoto, circleTransformation, binding.ivProfilePictureDetail)

        /*Showing logo or not*/
        if(tweet.verifiedLogo) binding.ivVerifiedLogoDetail.visibility = visibility
        else binding.ivVerifiedLogoDetail.visibility = noVisibility

        /*Managing CardView elements to show images get from Firestore*/
        if (!tweet.hasMedia) binding.cvMediaDetail.visibility = noVisibility
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

                2 -> { binding.llMediaDetail.visibility = visibility
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

                3 -> { binding.llMediaDetail.visibility = visibility
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

                4 -> { binding.llMediaDetail.visibility = visibility
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
        binding.tvCantLikesDetail.text = castingMetrics(tweet.cantLikes)
        binding.tvCantRetweetsDetail.text = castingMetrics(tweet.cantRetweetsAndReposts)
        binding.tvViewsDetail.text = castingMetrics(tweet.cantViews)
        binding.tvPublicNameDetail.text = tweet.publicName
        binding.tvRealUsernameDetail.text = "@${tweet.realUsername}"
        highlightHashtagAndMentions(tweet.tweetContent, binding.tvTweetContentDetail)

        /*Setting and showing time for post's detailed page:*/
        val cal = Calendar.getInstance()
        cal.time = tweet.hourAndDate
        val timePostFormat = SimpleDateFormat("hh:mm a · dd MMM yy · ")
        val timeToDisplay = timePostFormat.format(cal.time)
        binding.tvTimeAndDatePostedDetail.setText(timeToDisplay)
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

    //Setting dialog screen size to match parent both width and height
    override fun onStart() {
        super.onStart()
        dialog
            ?.window
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }

    //Fixing Views count for Tweet's detailed page
    fun castingMetrics(quantity: Long) : String {
        var textToReturn : String

        val metricsFlag = when (quantity) {
            in 0..999 -> ""
            in 1_000..999_999 -> "K"
            in 1_000_000..999_999_999 -> "M"
            else -> "G"
        }

        val metrics = when (quantity) {
            in 0..999 -> "%.1f".format(quantity.toDouble() / 1)
            in 1_000..999_999 -> "%.1f".format(quantity.toDouble() / 1000)
            in 1_000_000..999_999_999 -> "%.1f".format(quantity.toDouble() / 1000000)
            else -> ""
        }
        textToReturn = metrics.replace(",",".")

        val modValue = when (metricsFlag) {
            "" -> 0
            "K"-> quantity % 1000
            "M"-> quantity % 1000000
            else -> quantity % 1000000000
        }

        if (modValue.toInt() != 0) {
            textToReturn += when (metricsFlag) {
                "" -> ""
                "K"-> "K "
                "M"-> "M "
                else -> "G "
            }
        }
        else { textToReturn = when (metricsFlag) {
                "" -> "$quantity"
                "K"-> "${quantity / 1_000}K "
                "M"-> "${quantity / 1_000_000}M "
                else -> "${quantity / 1_000_000_000}G "
            }
        }

        return textToReturn
    }

    /*Giving color to mentions or hashtags within the Tweet*/
    fun highlightHashtagAndMentions(content: String, tvContent: TextView) {
        val coordinates: Array<Array<Int>> = locateHashtagAndMentionsPositions(content)
        var startSubStringPos = 0

        /*Set text color for single hashtag or mention*/
        if (coordinates.size == 1) {
            if (coordinates[0][0] == 0 && coordinates[0][1] == 0)
                tvContent.text = content
            else {
                val spannableString = SpannableString(content)
                val textColor = ForegroundColorSpan(Color.parseColor("#1D9BF0"))
                spannableString.setSpan(
                    textColor,
                    coordinates[0][0],
                    coordinates[0][1],
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                tvContent.text = spannableString
            }
        }
        /*Set text color for multiple hashtags or mentions*/
        else {
            /*Step 1. Preparing data if there are more than one account mention or hashtag*/
            val spannableStringBuilder = SpannableStringBuilder()
            val stringsToEdit = Array(coordinates.size + 1) { "" }

            for (i in stringsToEdit.indices) {
                if (i <= coordinates.size - 1) {
                    stringsToEdit[i] = content.substring(startSubStringPos, coordinates[i][1] + 1)
                    startSubStringPos = coordinates[i][1] + 1
                }
                else stringsToEdit[i] = content.substring(startSubStringPos)
            }

            /*Step 2. Setting color for mentions or hashtags*/
            for (j in stringsToEdit.indices) {
                val textColor = ForegroundColorSpan(Color.parseColor("#1D9BF0"))
                spannableStringBuilder.append(stringsToEdit[j])

                if (j < stringsToEdit.size - 1) {
                    spannableStringBuilder.setSpan(
                        textColor,
                        coordinates[j][0],
                        coordinates[j][1],
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                }
            }
            tvContent.text = spannableStringBuilder
        }
    }

    /*Finding positions to later change the color of the mentioned accounts or hashtags*/
    fun locateHashtagAndMentionsPositions(content: String) : Array<Array<Int>> {
        val qty = content.count { it == '@' || it == '#' }
        val arrayStartChars = charArrayOf('@' , '#')
        val arrayStopChars = charArrayOf('.' , ' ')
        var locations = Array(qty) { Array(2) { 0 } }
        var startPos: Int
        var endPos = 0
        /*println("Initial values: ${locations.contentDeepToString()}")*/

        when (qty) {
            0 -> { locations = Array(1) { Array(2) { 0 } } }
            1 -> { startPos = content.indexOfAny(arrayStartChars, 0, false)
                endPos = content.indexOfAny(arrayStopChars, startPos, false)
                locations[0][0] = startPos; locations[0][1] = endPos }
            else -> { for (i in 0 until qty) {
                startPos = content.indexOfAny(arrayStartChars, endPos, false)
                endPos = content.indexOfAny(arrayStopChars, startPos, false)
                locations[i][0] = startPos
                locations[i][1] = endPos } }
        }
        /*println("Final values: ${locations.contentDeepToString()}")*/
        return locations
    }
}