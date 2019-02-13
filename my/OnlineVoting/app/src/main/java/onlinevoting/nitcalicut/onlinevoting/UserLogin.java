package onlinevoting.nitcalicut.onlinevoting;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import onlinevoting.nitcalicut.onlinevoting.model.Vote_User;

public class UserLogin extends AppCompatActivity {
    private EditText login_email,login_roll;
    private Button btn_login;
    private String Email,Rollno;
    private ProgressDialog pd;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        pd=new ProgressDialog(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        userdetails();

        btn_login=(Button)findViewById(R.id.stu_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation())
                {
                    //move u to voting page
                    startsign();
                }
            }
        });
    }

    private void userdetails()
    {
        login_email=(EditText) findViewById(R.id.stu_email);
        login_roll=(EditText) findViewById(R.id.stu_rollno);
    }

    private boolean validation()
    {
        boolean flag=true;
        Email=login_email.getText().toString().trim();
        Rollno=login_roll.getText().toString().trim();

        if(Email.isEmpty() || Rollno.isEmpty())
        {
            Toast.makeText(UserLogin.this,"Please Enter Details",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void startsign()
    {
        pd.setMessage("Sign in \n Please Wait...");
        pd.show();
        DatabaseReference query = mDatabase.child("register_user");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pd.dismiss();
                if (dataSnapshot.exists()) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            Vote_User user = ds.getValue(Vote_User.class);
                            if(Email.equals(user.getEmail())){
                                if (Rollno.equals(user.getRollno())) {
                                    Intent intent = new Intent(getApplicationContext(), Votingpage.class);
                                    intent.putExtra("Student_rollno",user.getRollno());
                                    intent.putExtra("Student_id",user.getVote_user_id());
                                    startActivity(intent);
                                    return;
                                } else {
                                    Toast.makeText(getApplicationContext(), "Rollno is wrong", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        }
                        Toast.makeText(getApplicationContext(),
                                "Student is not register yet!!",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "No data not found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("UserLogin","Error:"+databaseError);
            }
        });
    }
}
