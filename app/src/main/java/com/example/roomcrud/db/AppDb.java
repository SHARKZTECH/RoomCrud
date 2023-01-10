package com.example.roomcrud.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class AppDb extends RoomDatabase {
    public abstract UserDao userDao();

    private static AppDb INSTANCE;

    public static AppDb getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDb.class,"USER_DB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
