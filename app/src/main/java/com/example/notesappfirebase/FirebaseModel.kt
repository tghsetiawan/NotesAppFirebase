package com.example.notesappfirebase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FirebaseModel(
        var title:String? = null,
        var content:String? = null
): Parcelable
