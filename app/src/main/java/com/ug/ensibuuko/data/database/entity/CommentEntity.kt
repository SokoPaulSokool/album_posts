package com.ug.ensibuuko.data.database.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "comments")
class CommentEntity() : Parcelable {
    //    @PrimaryKey()
//    var id: Int? = null
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "postId")
    var postId: Int? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "email")
    var email: String? = null

    @ColumnInfo(name = "body")
    var body: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        postId = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        email = parcel.readString()
        body = parcel.readString()
    }

    constructor(postId: Int?, name: String?,email: String?, body: String?) : this() {
        this.postId = postId
        this.name = name
        this.email = email
        this.body = body
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeValue(postId)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(body)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CommentEntity> {
        override fun createFromParcel(parcel: Parcel): CommentEntity {
            return CommentEntity(parcel)
        }

        override fun newArray(size: Int): Array<CommentEntity?> {
            return arrayOfNulls(size)
        }
    }
}