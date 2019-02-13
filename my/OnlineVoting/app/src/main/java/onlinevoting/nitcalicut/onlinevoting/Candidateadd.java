package onlinevoting.nitcalicut.onlinevoting;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import onlinevoting.nitcalicut.onlinevoting.helper.FBDatabaseHelper;
import onlinevoting.nitcalicut.onlinevoting.model.Candidate;
import onlinevoting.nitcalicut.onlinevoting.model.Post;

public class Candidateadd extends AppCompatActivity {

    private List<Post> postsList;
    private FBDatabaseHelper db;
    private Spinner postion_spinner,no_spinner;
    private EditText editText_name[];
    private Button btn_submit;
    private String postName="";
    private List<String> postNameLists;
    private List<Candidate> candidates;
    private int current_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidateadd);

        postsList = new ArrayList<>();
        postNameLists = new ArrayList<>();
        candidates = new ArrayList<>();
        editText_name = new EditText[3];

        editText_name[0] = findViewById(R.id.editText_name1);
        editText_name[1] = findViewById(R.id.editText_name2);
        editText_name[2] = findViewById(R.id.editText_name3);

        db = new FBDatabaseHelper(this);
        postion_spinner = findViewById(R.id.position_spinner);
        no_spinner = findViewById(R.id.no_spinner);

        db.getAllPosts(new DBCallback() {
            @Override
            public void onSuccess(List<Post> posts) {
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

        final Integer[] no_candidates = {1,2,3};
        ArrayAdapter<Integer> adp2 = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item,no_candidates);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        no_spinner.setAdapter(adp2);


        no_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                int i=position+1;
                toogleVisibility();
                while(i<3){
                    editText_name[i].setVisibility(View.GONE);
                    i++;
                }

                i=0;
                while(i<position){
                    editText_name[i].setText("");
                    i++;
                };

                current_no = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        postion_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                postName = postNameLists.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag=1;
                for(int i=0;i<current_no;i++){
                    if(TextUtils.isEmpty(editText_name[i].getText().toString())){
                        Toast.makeText(Candidateadd.this,
                                "Fill the name!!",Toast.LENGTH_SHORT).show();
                        flag=0;
                    }
                }
                if(flag==1){
                    for(int i=0;i<current_no;i++){
                        Candidate candidate = new Candidate(i+1,editText_name[i].getText().toString(),0);
                        candidates.add(candidate);
                    }
                    Toast.makeText(Candidateadd.this,postName,Toast.LENGTH_SHORT).show();
                    db.insertCandidates(candidates,postName);
                }

            }
        });

    }

    void toogleVisibility(){
        for(int i=0;i<3;i++){
            editText_name[i].setVisibility(View.VISIBLE);
        }
    }
}
