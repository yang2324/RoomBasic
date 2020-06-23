package com.example.roombasic;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created by yang
 * date: 2020/6/19
 * Describe: 读取数据
 */
public class WorldViewModel extends AndroidViewModel {

    //定义
    private WorldRepository worldRepository;

    public WorldViewModel(@NonNull Application application) {
        super(application);

        worldRepository = new WorldRepository(application);
    }

    public LiveData<List<World>> getAllWorldLive() {
        return worldRepository.getAllWorldLive();
    }

    //调用接口
    void insertAsyncTask(World... worlds){
        //new InsertAsyncTask(worldDao).execute(worlds);
        worldRepository.insertAsyncTask(worlds);
    }
    void updateAsyncTask(World... worlds){
        //new UpdateAsyncTask(worldDao).execute(worlds);
        worldRepository.updateAsyncTask(worlds);
    }
    void deleteAsyncTask(World... worlds){
        //new DeleteAsyncTask(worldDao).execute(worlds);
        worldRepository.deleteAsyncTask(worlds);
    }
    void clearAsyncTask(){
        //new ClearAsyncTask(worldDao).execute();
        worldRepository.clearAsyncTask();
    }

    //

}
