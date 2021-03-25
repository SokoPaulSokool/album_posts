package com.ug.ensibuuko.dagger.module;

import android.app.Application;

import androidx.room.Room;


import com.ug.ensibuuko.data.database.EnsibuukoDatabase;
//import com.ug.ensibuuko.data.database.dao.CommentDAO;
import com.ug.ensibuuko.data.database.dao.AlbumDAO;
import com.ug.ensibuuko.data.database.dao.CommentDAO;
import com.ug.ensibuuko.data.database.dao.PhotoDAO;
import com.ug.ensibuuko.data.database.dao.PostDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    private EnsibuukoDatabase ensibuukoDB;

    public DatabaseModule(Application application, String databaseName) {
        this.ensibuukoDB = Room.databaseBuilder(application,
                EnsibuukoDatabase.class, databaseName).fallbackToDestructiveMigration().build();
    }

    @Provides
    @Singleton
    EnsibuukoDatabase providesEnsibuukoDB() {
        return ensibuukoDB;
    }

    @Provides
    @Singleton
    PostDAO providesPostDAO() {
        return ensibuukoDB.postDAO();
    }

    @Provides
    @Singleton
    CommentDAO providesCommentDAO() {
        return ensibuukoDB.commentDAO();
    }

    @Provides
    @Singleton
    AlbumDAO providesAlbumDAO() {
        return ensibuukoDB.albumDAO();
    }

    @Provides
    @Singleton
    PhotoDAO providesPhotoDAO() {
        return ensibuukoDB.photoDAO();
    }
}
