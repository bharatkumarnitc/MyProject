package onlinevoting.nitcalicut.onlinevoting;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import onlinevoting.nitcalicut.onlinevoting.helper.FBDatabaseHelper;
import onlinevoting.nitcalicut.onlinevoting.model.Candidate;
import onlinevoting.nitcalicut.onlinevoting.model.Post;

public class Votingpage extends AppCompatActivity {

    private Spinner postion_spinner;
    private FBDatabaseHelper db;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List<Post> postsList;
    private List<String> postNameLists;
    private Button btn_submit,logout;
    private String postName;
    private List<Candidate> candidateList;
    private RadioButton rb_candidate[],rb;
    private String Student_rollno;
    private int current_count=0;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votingpage);

        db = new FBDatabaseHelper(this);
        postion_spinner = findViewById(R.id.position_spinner);
        radioGroup = findViewById(R.id.rg_candidate);

        Student_rollno = getIntent().getStringExtra("Student_rollno").toString();
        database = FirebaseDatabase.getInstance();

        postsList = new ArrayList<>();
        postNameLists = new ArrayList<>();
        candidateList = new ArrayList<>();
        postName="";
        rb_candidate = new RadioButton[3];
        rb_candidate[0] = findViewById(R.id.rb_candidate1);
        rb_candidate[1] = findViewById(R.id.rb_candidate2);
        rb_candidate[2] = findViewById(R.id.rb_candidate3);
        logout=findViewById(R.id.btn_log);

        db.getAllPosts(new DBCallback() {
            @Override
            public void onSuccess(List<Post> posts)
            {
                postsList = posts;
                for(int i=0;i<postsList.size();i++){
                    postNameLists.add(postsList.get(i).getPost_name());
                }
                ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item,postNameLists);
                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                postion_spinner.setAdapter(adp1);
            }

            @Override
            public void onSuccess(int count) {

            }

            @Override
            public void onSuccess(Post post) {

            }

            @Override
            public void onError(String msg) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Votingpage.this,Home.class));
            }
        });


        postion_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                postName = postNameLists.get(position).toString();
                getDataRadioButton();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readVoteUserinfo();
            }
        });
    }

    void toogleVisibility(){
        for(int i=0;i<3;i++){
            rb_candidate[i].setVisibility(View.VISIBLE);
        }
    }

    void getDataRadioButton(){
        myRef = database.getReference("candidate_info").child(postName);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    candidateList.clear();
                    if(dataSnapshot!=null){
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            candidateList.add(ds.getValue(Candidate.class));
                        }
                        current_count = candidateList.size();
                        int i=0;
                        for(i=0;i<current_count;i++){
                            rb_candidate[i].setText(candidateList.get(i).getName());
                        }
                        toogleVisibility();

                        while(i<3){
                            rb_candidate[i].setVisibility(View.GONE);
                            i++;
                        }
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void readVoteUserinfo()
    {
        myRef = database.getReference("vote_user_info").child(Student_rollno);
        rb = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cd_name = rb.getText().toString();
                String flag;
                if(dataSnapshot==null)
                {
                    insertData(postName,cd_name,"true");
                }else{
                    flag = dataSnapshot.child(postName).child("flag").getValue(String.class);
                    if(flag!=null && flag.equals("false"))
                    {

                        insertData(postName,cd_name,"true");
                    }else if(flag==null){
                        insertData(postName,cd_name,"true");
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Already vote!!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void insertData(String postName,String candidate_name,String flag){
        myRef = database.getReference("vote_user_info").
                child(Student_rollno).child(postName);
        HashMap<String,String> map = new HashMap<>();
        map.put("candidate_name",candidate_name);
        map.put("flag",flag);
        myRef.setValue(map);

        int cd_id=0,vote_count=0;
        for(int i=0;i<candidateList.size();i++){
            if(candidateList.get(i).getName().equals(candidate_name)){
                cd_id = candidateList.get(i).getCandidate_id();
                vote_count = candidateList.get(i).getVote_count();
            }
        }
        myRef = database.getReference("candidate_info").child(postName).child(Integer.toString(cd_id)).child("vote_count");
        myRef.setValue(vote_count+1);
    }

}
