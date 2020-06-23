package com.example.roombasic;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Created by yang
 * date: 2020/6/18
 * Describe: singleton  单例模式
 */
@Database(entities = {World.class},version = 4,exportSchema = false)
public abstract class WorldDatabase extends RoomDatabase {

    private static WorldDatabase INSTANCE;
    static synchronized WorldDatabase getDatabase(Context context){
        if (INSTANCE == null){
            //获取database实列
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WorldDatabase.class,"world_database")
                    //.fallbackToDestructiveMigration()
                    .addMigrations(MIGRATIONS_3_4)
                    .build();
        }
        return INSTANCE;
    }

    public abstract WorldDao getWorldDao();

    static final Migration MIGRATIONS_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE world ADD COLUMN flag_data INTEGER NOT NULL DEFAULT 1");
        }
    };
    static final Migration MIGRATIONS_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE world ADD COLUMN chinese_hide INTEGER NOT NULL DEFAULT 0");
        }
    };
}
