package com.pensource.android.advice.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.pensource.android.advice.database.model.Advice;

import java.util.List;

@Dao
public interface AdviceDao {

    @Insert
    void insertAdvice(Advice advice);

    @Insert
    void insertAllAdvice(List<Advice> advice);

    @Query("SELECT * FROM table_advice WHERE _advice_id=:adviceId LIMIT 1")
    Advice getAdviceBy();

    @Query("SELECT * FROM table_advice WHERE _seen=0 LIMIT 1")
    Advice getNextAdvice();

    @Query("SELECT * FROM table_advice WHERE _advice_id=:adviceId AND _is_favorite=1 LIMIT 1")
    Advice getFavoriteAdviceById(int adviceId);

    @Query("SELECT * FROM table_advice")
    LiveData<List<Advice>> getAllAdvice();

    @Query("SELECT * FROM table_advice WHERE _is_favorite=1")
    LiveData<List<Advice>> getAllFavoriteAdvice();

    @Query("UPDATE table_advice SET _seen=1 WHERE _id=:id")
    void updateSeenAdvice(int id);

    @Query("DELETE FROM table_advice WHERE _advice_id=:adviceId")
    void deleteAdviceById(int adviceId);

    @Query("DELETE FROM table_advice")
    void deleteAllAdvice();

    @Query("DELETE FROM table_advice WHERE _is_favorite=1")
    void deleteAllFavorites();

    @Query("SELECT COUNT(_seen) FROM table_advice WHERE _seen=0")
    int getNonReadCount();

}
