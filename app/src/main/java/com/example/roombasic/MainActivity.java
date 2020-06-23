package com.example.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.List;

public class MainActivity extends AppCompatActivity {
//    WorldDao worldDao;
//    WorldDatabase worldDatabase;
    //TextView textView;
    Button buttonInsert,buttonClear;
    //定义allWorldLive
    //LiveData<List<World>> allWorldLive;

    WorldViewModel worldViewModel;

    RecyclerView recyclerView;
    MyAdapter myAdapter1,myAdapter2;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        aSwitch = findViewById(R.id.switchChineseHide);

        worldViewModel = new ViewModelProvider(this).get(WorldViewModel.class);
        myAdapter1 = new MyAdapter(false,worldViewModel);
        myAdapter2 = new MyAdapter(true,worldViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter1);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    recyclerView.setAdapter(myAdapter2);
                } else {
                    recyclerView.setAdapter(myAdapter1);
                }
            }
        });




//        worldDatabase = Room.databaseBuilder(this,WorldDatabase.class,"world_database").allowMainThreadQueries().build();
//        worldDao = worldDatabase.getWorldDao();

        //获取定义
       // allWorldLive = worldDao.getAllWorldLive();

       //textView = findViewById(R.id.textViewNumber);

        //实现观察 ---- 当观察数据发生改变时进行操作
        worldViewModel.getAllWorldLive().observe(this, new Observer<List<World>>() {
            @Override
            //观察当数据发生改变时的变化
            public void onChanged(List<World> worlds) {
//                StringBuilder text = new StringBuilder();
//                for (int i = 0; i<worlds.size(); i++){
//                    World world = worlds.get(i);
//                    text.append(world.getId()).append(":").append(world.getWord()).append("=").append(world.getChineseMessage()).append("\n");
//                }
//                textView.setText(text.toString());
                int item = myAdapter1.getItemCount();
                myAdapter1.setAllWorlds(worlds);
                myAdapter2.setAllWorlds(worlds);
                if (item!=worlds.size()){
                    myAdapter1.notifyDataSetChanged();
                    myAdapter2.notifyDataSetChanged();
                }

            }
        });

        buttonInsert = findViewById(R.id.buttonInsert);
        buttonClear = findViewById(R.id.buttonClear);
//        buttonUpdata = findViewById(R.id.buttonUpdata);
//        buttonDelete = findViewById(R.id.buttonDelete);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] word = {"Monday","zhiTuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
                String[] chinese = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};

                for (int i = 0 ;i<word.length;i++){
                    worldViewModel.insertAsyncTask(new World(word[i],chinese[i]));
                }
//                World world = new World("hello","你好");
//                World world1 = new World("world","世界");
//                //worldDao.insertWorlds(world,world1);
//                //new InsertAsyncTask(worldDao).execute(world,world1);
//                worldViewModel.insertAsyncTask(world,world1);
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //worldDao.deleteAllClear();
                //new ClearAsyncTask(worldDao).execute();
                worldViewModel.clearAsyncTask();
            }
        });

    }


}