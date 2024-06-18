package com.example.alias.data

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var wordId: Long = 0L,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "is_guess")
    var isGuess: Boolean = false
): Parcelable{
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readBoolean()
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(wordId)
        parcel.writeString(name)
        parcel.writeBoolean(isGuess)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Word> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Word {
            return Word(parcel)
        }

        override fun newArray(size: Int): Array<Word?> {
            return arrayOfNulls(size)
        }
    }
}