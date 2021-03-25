package com.ug.ensibuuko.data.database.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
class PhotoEntity() : Parcelable {
    //    @PrimaryKey()
//    var id: Int? = null
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "albumId")
    var albumId: Int? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "url")
    var url: String? = null

    @ColumnInfo(name = "thumbnailUrl")
    var thumbnailUrl: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        albumId = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        url = parcel.readString()
        thumbnailUrl = parcel.readString()
    }

    constructor(albumId: Int?, title: String?,url: String?, thumbnailUrl: String?) : this() {
        this.albumId = albumId
        this.title = title
        this.url = url
        this.thumbnailUrl = thumbnailUrl
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeValue(albumId)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(thumbnailUrl)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PhotoEntity> {
        override fun createFromParcel(parcel: Parcel): PhotoEntity {
            return PhotoEntity(parcel)
        }

        override fun newArray(size: Int): Array<PhotoEntity?> {
            return arrayOfNulls(size)
        }
    }

}