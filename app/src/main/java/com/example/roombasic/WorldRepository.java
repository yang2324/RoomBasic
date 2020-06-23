package com.example.roombasic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created by yang
 * date: 2020/6/19
 * Describe:
 */
public class WorldRepository {

    private LiveData<List<World>> allWorldLive;
    WorldDao worldDao;

    public WorldRepository(Context context) {
        WorldDatabase worldDatabase = WorldDatabase.getDatabase(context.getApplicationContext());
        worldDao = worldDatabase.getWorldDao();
        allWorldLive = worldDao.getAllWorldLive();
    }

    //对应的操作方法实现调用方法
    void insertAsyncTask(World... worlds){
        new InsertAsyncTask(worldDao).execute(worlds);
    }
    void updateAsyncTask(World... worlds){
        new UpdateAsyncTask(worldDao).execute(worlds);
    }
    void deleteAsyncTask(World... worlds){
        new DeleteAsyncTask(worldDao).execute(worlds);
    }
    void clearAsyncTask(){
        new ClearAsyncTask(worldDao).execute();
    }

    public LiveData<List<World>> getAllWorldLive() {
        return allWorldLive;
    }

    static class InsertAsyncTask extends AsyncTask<World,Void,Void> {
        private WorldDao worldDao;

        public InsertAsyncTask(WorldDao worldDao) {
            this.worldDao = worldDao;
        }

        @Override
        protected Void doInBackground(World... worlds) {
            worldDao.insertWorlds(worlds);
            return null;
        }
    }
    static class UpdateAsyncTask extends AsyncTask<World,Void,Void>{
        private WorldDao worldDao;

        public UpdateAsyncTask(WorldDao worldDao) {
            this.worldDao = worldDao;
        }

        @Override
        protected Void doInBackground(World... worlds) {
            worldDao.upDataWorlds(worlds);
            return null;
        }
    }
    static class DeleteAsyncTask extends AsyncTask<World,Void,Void>{
        private WorldDao worldDao;

        public DeleteAsyncTask(WorldDao worldDao) {
            this.worldDao = worldDao;
        }

        @Override
        protected Void doInBackground(World... worlds) {
            worldDao.deleteWorlds(worlds);
            return null;
        }
    }
    static class ClearAsyncTask extends AsyncTask<Void,Void,Void>{
        private WorldDao worldDao;

        public ClearAsyncTask(WorldDao worldDao) {
            this.worldDao = worldDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            worldDao.deleteAllClear();
            return null;
        }
    }
}
