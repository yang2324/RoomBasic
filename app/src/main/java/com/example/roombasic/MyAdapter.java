package com.example.roombasic;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang
 * date: 2020/6/22
 * Describe:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewAdapter> {
    List<World> allWorlds = new ArrayList<>();
    boolean userCardView;
    WorldViewModel worldViewModel;

    public MyAdapter(boolean userCardView,WorldViewModel worldViewModel) {
        this.userCardView = userCardView;
        this.worldViewModel = worldViewModel;
    }

    public void setAllWorlds(List<World> allWorlds) {
        this.allWorlds = allWorlds;
    }

    @NonNull
    @Override   //创建viewHolder 使用
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView ;
        if (userCardView){
            itemView = layoutInflater.inflate(R.layout.cell_card2,parent,false);
        } else {
            itemView = layoutInflater.inflate(R.layout.cell_noraml2,parent,false);
        }
        return new MyViewAdapter(itemView);
    }

    @Override       //绑定时候使用
    public void onBindViewHolder(@NonNull final MyViewAdapter holder, int position) {
        final World world = allWorlds.get(position);
        holder.textViewNumber.setText(String.valueOf(position+1));
        holder.textViewWord.setText(world.getWord());
        holder.textViewChinese.setText(world.getChineseMessage());

        holder.aSwitch.setOnCheckedChangeListener(null);
        if (world.isChineseHide()){
            holder.textViewChinese.setVisibility(View.GONE);
            holder.aSwitch.setChecked(true);
        } else {
            holder.textViewChinese.setVisibility(View.VISIBLE);
            holder.aSwitch.setChecked(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://m.youdao.com/dict?le=eng&q=" + holder.textViewWord.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    holder.textViewChinese.setVisibility(View.GONE);
                    world.setChineseHide(true);
                    worldViewModel.updateAsyncTask(world);
                } else {
                    holder.textViewChinese.setVisibility(View.VISIBLE);
                    world.setChineseHide(false);
                    worldViewModel.updateAsyncTask(world);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return allWorlds.size();
    }

    static class MyViewAdapter extends RecyclerView.ViewHolder{
        TextView textViewNumber,textViewWord,textViewChinese;
        Switch aSwitch;
        public MyViewAdapter(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewWord = itemView.findViewById(R.id.textViewWord);
            textViewChinese = itemView.findViewById(R.id.textViewChinese);
            aSwitch = itemView.findViewById(R.id.switchChineseHide);
        }
    }
}
