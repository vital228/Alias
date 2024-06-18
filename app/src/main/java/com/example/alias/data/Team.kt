package com.example.alias.data

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

data class Team(
    var name: String,
    var color: Int, // Use Android Color Int representation
    var picture: Int, // This could be a URI or resource ID
    var score: Int = 0,
    var active: Boolean = false
): Parcelable{
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readBoolean()
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(color)
        parcel.writeInt(picture)
        parcel.writeInt(score)
        parcel.writeBoolean(active)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Team> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Team {
            return Team(parcel)
        }

        override fun newArray(size: Int): Array<Team?> {
            return arrayOfNulls(size)
        }
    }
}
fun findActive(teams: Array<Team>):Int{
    for (team in teams.withIndex()){
        if (team.value.active){
            return team.index
        }
    }
    return 0
}
