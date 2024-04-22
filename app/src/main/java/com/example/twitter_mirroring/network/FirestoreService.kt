package com.example.twitter_mirroring.network

import com.example.twitter_mirroring.model.TweetData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val DATA_COLLECTION_NAME = "tweetsData"
class FirestoreService {
    //Setting up direct connection to Firestore DataBase and getting initial instance
    val firebaseFirestore = FirebaseFirestore.getInstance()

    //Setting persistence of info. from DataBase to the app
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    //Assign persistence configuration to current instance
    init { firebaseFirestore.firestoreSettings = settings }

    //With this function the data is actually obtained from the uploaded collection in Firestore
    fun getTweetsData (callback: Callback<List<TweetData>>)
    {
        firebaseFirestore.collection(DATA_COLLECTION_NAME)
            .get()
            .addOnSuccessListener{ result ->
                for (doc in result)
                {
                    val list = result.toObjects(TweetData::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
}