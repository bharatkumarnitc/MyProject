package onlinevoting.nitcalicut.onlinevoting.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import onlinevoting.nitcalicut.onlinevoting.DBCallback;
import onlinevoting.nitcalicut.onlinevoting.model.Candidate;
import onlinevoting.nitcalicut.onlinevoting.model.Post;

public class FBDatabaseHelper {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    public Post post;
    private Context context;

    public FBDatabaseHelper(Context context){
        database = FirebaseDatabase.getInstance();
        this.context = context;
    }

    //get all position name from database
    public void getAllPosts(final DBCallback callback){
        myRef = database.getReference("position_name");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Post> posts = new ArrayList<>();
                if(dataSnapshot!=null){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Post _post = ds.getValue(Post.class);
                        Post post = new Post(_post.getPost_id(),_post.getPost_name()
                                ,_post.getDate());
                        posts.add(post);
                    }
                    callback.onSuccess(posts);
                }else{
                    callback.onError("No data found!!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError("problem in fetching data from database!!");
            }
        });
    }


    public void getPostsCount(final DBCallback callback){
        myRef = database.getReference("position_name");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count=0;
                if(dataSnapshot!=null){
                    count = (int)dataSnapshot.getChildrenCount();
                    callback.onSuccess(count);
                }else{
                    callback.onError("No data found!!");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError("problem in fetching data from database!!");
            }
        });
    }

    public int insertPost(final int post_id,final String postName,final String date){
        myRef = database.getReference("position_name");
        Post post = new Post(post_id,postName,date);
        myRef.child(Integer.toString(post_id)).setValue(post);
        return post_id;
    }



    public Post getPost(int post_id){
        myRef = database.getReference("position_name").child(Integer.toString(post_id));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null) {
                    Post p = dataSnapshot.getValue(Post.class);
                    post = p;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FBDatabaseHepler","problem in fetching data from database!!");
            }
        });
        return post;
    }

    public int updatePost(Post post) {
        myRef = database.getReference("position_name").
                child(Integer.toString(post.getPost_id()));
        myRef.setValue(post);
        return 1;
    }

    public void deletePost(Post post) {
        myRef = database.getReference("position_name").
                child(Integer.toString(post.getPost_id()));
        myRef.removeValue();
    }

    public void insertCandidates(List<Candidate> candidates,String postName){
        myRef = database.getReference("candidate_info").child(postName);
        for(int i=0;i<candidates.size();i++){
            int candidate_id = candidates.get(i).getCandidate_id();
            myRef.child(Integer.toString(candidate_id)).setValue(candidates.get(i));
        }
    }
}
