package com.academy.app.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
class Currency(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String = "",
    var curID: Long = 0,
    var scale: Int = 0,
    var curCode: String = ""

    ) : Parcelable