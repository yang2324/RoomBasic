package com.example.roombasic;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by yang
 * date: 2020/6/18
 * Describe:
 */
@Dao
public interface WorldDao {
    @Insert
    void insertWorlds(World... worlds);

    @Update
    void upDataWorlds(World... worlds);

    @Delete
    void deleteWorlds(World... worlds);

    @Query("DELETE FROM  world")
    void deleteAllClear();

    @Query("SELECT * FROM WORLD ORDER BY id DESC")
    //List<World> getAllWorld();
    //room 本身就支持liveData  把数据存放在liveData里面
    LiveData<List<World>> getAllWorldLive();
}
