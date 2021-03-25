package com.ug.ensibuuko.data.database.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "posts")
class PostEntity() : Parcelable {
    //    @PrimaryKey()
//    var id: Int? = null
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "userId")
    var userId: Int? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "body")
    var body: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        userId = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        body = parcel.readString()
    }

    constructor(userId: Int?, title: String?, body: String?) : this() {

        this.userId = userId
        this.title = title
        this.body = body
    }
//
//    constructor(parcel: Parcel) : this() {
//        id = (parcel.readValue(Int::class.java.classLoader) as? Int)!!
//        userId = parcel.readValue(Int::class.java.classLoader) as? Int
//        title = parcel.readString()
//        body = parcel.readString()
//    }
//
//    // for DiffUtil class
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other !is PostEntity) return false
//        val post = other as? PostEntity
//        return id == post?.id
//                && userId == post?.userId
//                && title == post?.title
//                && body == post?.body
//    }
//
//
//
//    // parcelable stuff
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeValue(id)
//        parcel.writeValue(userId)
//        parcel.writeString(title)
//        parcel.writeString(body)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<PostEntity> {
//        override fun createFromParcel(parcel: Parcel): PostEntity {
//            return PostEntity(parcel)
//        }
//
//        override fun newArray(size: Int): Array<PostEntity?> {
//            return arrayOfNulls(size)
//        }
//    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeValue(userId)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostEntity> {
        override fun createFromParcel(parcel: Parcel): PostEntity {
            return PostEntity(parcel)
        }

        override fun newArray(size: Int): Array<PostEntity?> {
            return arrayOfNulls(size)
        }
    }
}