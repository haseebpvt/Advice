package com.pensource.android.advice.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_advice")
public class Advice {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int id;

    @ColumnInfo(name = "_advice_id")
    public int advice_id;

    @ColumnInfo(name = "_advice")
    public String advice;

    @ColumnInfo(name = "_is_favorite")
    public int favorite;

    @ColumnInfo(name = "_seen")
    public int seen;

}
