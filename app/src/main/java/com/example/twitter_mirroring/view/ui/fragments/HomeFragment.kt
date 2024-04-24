package com.example.twitter_mirroring.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.twitter_mirroring.R
import com.example.twitter_mirroring.databinding.FragmentHomeBinding
import com.example.twitter_mirroring.model.TweetData
import com.example.twitter_mirroring.view.adapter.TweetAdapter
import com.example.twitter_mirroring.view.adapter.TweetListener
import com.example.twitter_mirroring.viewmodel.TweetDataViewModel
import java.util.Calendar

class HomeFragment : Fragment(), TweetListener {

    //Implementing ViewBinding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //Variables to access data from View Model and adapter to use
    private lateinit var viewModel: TweetDataViewModel
    private lateinit var tweetAdapter: TweetAdapter

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Rounding Profile photo
        Glide.with(view.context)
            .load("https://pbs.twimg.com/profile_images/3103894633/e0d179fc5739a20308331b432e4f3a51_400x400.jpeg")
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivProfilePictureHome)

        binding.ivProfilePictureHome.setOnClickListener { Toast.makeText(activity, "Profile picture in Home screen!", Toast.LENGTH_SHORT).show() }
        binding.ivTwitterWhiteLogo.setOnClickListener {
            val currentDateAndTime = Calendar.getInstance().time
            Toast.makeText(activity, currentDateAndTime.toString(), Toast.LENGTH_LONG).show()
        }

        //Instance of TweetDataViewModel so through the refresh method the info. from Internet can be obtained
        viewModel = ViewModelProviders.of(this).get(TweetDataViewModel::class.java)
        viewModel.refresh()

        //Setting adapter for RecyclerView
        tweetAdapter = TweetAdapter(this)
        binding.rvFragmentHome.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = tweetAdapter
        }

        //Observe data on RecyclerView
        observeViewModel()
    }

    //Observe data events on RecyclerView
    fun observeViewModel ()
    {
        //If the information changes, it updates the RecyclerView
        viewModel.listTweets.observe(viewLifecycleOwner, Observer<List<TweetData>> { tweet -> tweetAdapter.updateData(tweet) })

        //Here the "loading" screen is managed before showing the information on screen
        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> { if (it != null) binding.rlFeedTimeline.visibility = View.INVISIBLE })
    }

    //Setting navigation from Feed to Tweet detail page
    override fun onTweetClicked(tweet: TweetData, position: Int) {
        val bundle = bundleOf("tweet" to tweet)
        findNavController().navigate(R.id.tweetDetailDialogFragment, bundle)
    }
}