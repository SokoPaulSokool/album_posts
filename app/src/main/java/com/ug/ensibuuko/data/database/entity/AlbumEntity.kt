package com.ug.ensibuuko.data.database.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
class AlbumEntity() : Parcelable {
    //    @PrimaryKey()
//    var id: Int? = null
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "userId")
    var userId: Int? = null

    @ColumnInfo(name = "title")
    var title: String? = null


    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        userId = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
    }

    constructor(userId: Int?, title: String?) : this() {
        this.userId = userId
        this.title = title
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeValue(userId)
        parcel.writeString(title)

    }

    override fun describeContents(): Int {
        return 0
    }

//    companion object CREATOR : Parcelable.Creator<AlbumEntity> {
//        override fun createFromParcel(parcel: Parcel): AlbumEntity {
//            return AlbumEntity(parcel)
//        }
//
//        override fun newArray(size: Int): Array<AlbumEntity?> {
//            return arrayOfNulls(size)
//        }
//    }
    companion object CREATOR : Parcelable.Creator<AlbumEntity> {
        override fun createFromParcel(parcel: Parcel): AlbumEntity {
            return AlbumEntity(parcel)
        }

        override fun newArray(size: Int): Array<AlbumEntity?> {
            return arrayOfNulls(size)
        }
    }
}