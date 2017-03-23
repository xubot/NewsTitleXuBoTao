package com.example.administrator.newstitlexubotao.Bean;


/**
 * Created by Administrator on 2017/3/21.
 */
public class SQLData {
    private int id;
    private String title;
    private String img;
    private String source;
    private String time;

    @Override
    public String toString() {
        return "SQLData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", source='" + source + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public SQLData( String title, String img, String source, String time) {

        this.title = title;
        this.img = img;
        this.source = source;
        this.time = time;
    }

    //无参构造方法如果不添加的话 数据库表创建不成功
    public SQLData() {
    }


}
