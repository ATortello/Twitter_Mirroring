package com.example.twitter_mirroring.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitter_mirroring.model.TweetData
import com.example.twitter_mirroring.network.Callback
import com.example.twitter_mirroring.network.FirestoreService
import java.lang.Exception

class TweetDataViewModel : ViewModel() {
    //Create and instance of FirestoreService
    val firestoreService = FirestoreService()
    var listTweets: MutableLiveData<List<TweetData>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    //Get data from Firestore Database
    fun refresh() { getTweetsDataFromFirebase() }

    //Call to Firestore service functions
    fun getTweetsDataFromFirebase()
    {
        firestoreService.getTweetsData(object: Callback<List<TweetData>>
        {
            override fun onSuccess(result: List<TweetData>?)
            {
                listTweets.postValue(result!!)
                processFinished()
            }

            override fun onFailed(exception: Exception) { processFinished() }
        })
    }

    //This function will be used to know if the data loading process on getTweetsDataFromFirebase function ends no matter if successes or fails
    fun processFinished() { isLoading.value = true }
}