package com.example.roombasic;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by yang
 * date: 2020/6/18
 * Describe: 实体类
 */

@Entity
public class World {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo(name = "english_word")
    private String word;

    @ColumnInfo(name = "chinese_message")
    private String chineseMessage;

    @ColumnInfo(name = "foo_data")
    private boolean foo;

    @ColumnInfo(name = "flag_data")
    private boolean flag;

    @ColumnInfo(name = "chinese_hide")
    private boolean chineseHide;

    public boolean isChineseHide() {
        return chineseHide;
    }

    public void setChineseHide(boolean chineseHide) {
        this.chineseHide = chineseHide;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFoo() {
        return foo;
    }

    public void setFoo(boolean foo) {
        this.foo = foo;
    }

    public World(String word, String chineseMessage) {
        this.word = word;
        this.chineseMessage = chineseMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getChineseMessage() {
        return chineseMessage;
    }

    public void setChineseMessage(String chineseMessage) {
        this.chineseMessage = chineseMessage;
    }
}
