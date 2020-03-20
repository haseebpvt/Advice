package com.pensource.android.advice.database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pensource.android.advice.database.dao.AdviceDao;
import com.pensource.android.advice.database.model.Advice;

@Database(entities = {Advice.class}, version = 1)
public abstract class AdviceDatabase extends RoomDatabase {

    private static volatile AdviceDatabase INSTANCE;

    public abstract AdviceDao adviceDao();

    public static AdviceDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AdviceDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AdviceDatabase.class,
                            "advice_database"
                    )
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

}
