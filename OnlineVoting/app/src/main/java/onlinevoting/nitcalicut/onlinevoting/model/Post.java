package onlinevoting.nitcalicut.onlinevoting.model;

import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    private int post_id;
    private String post_name;
    private String date;
    public Post(){

    }

    public Post(int post_id,String post_name,String date){
        this.post_id = post_id;
        this.post_name=post_name;
        this.date = date;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
