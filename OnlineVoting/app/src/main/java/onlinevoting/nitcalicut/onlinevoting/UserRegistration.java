package onlinevoting.nitcalicut.onlinevoting;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
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

public class UserRegistration extends AppCompatActivity
{
    private static final String TAG = "UserRegistration";
    private EditText editText_username,editText_email,editText_rollno,editText_dept;
    private Button btn_submit;
    private String Name,Email,RollNo,Department;
    private DatabaseReference admin_db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        progressDialog = new ProgressDialog(this);

        //Firebase database instance
        admin_db = FirebaseDatabase.getInstance().getReference().child("register_user");

        //getting the data from ui to variables
        userdetails();

        //Click listener for submit button
        btn_submit = (Button) findViewById(R.id.btnsubmitr);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Registering....");
                progressDialog.show();
                if(validate()){
                    register_user();
                    progressDialog.dismiss();
                }
                else{
                    progressDialog.dismiss();
                }

            }
        });
    }

    private void userdetails() {
        editText_username = (EditText) findViewById(R.id.usernamer);
        editText_email = (EditText) findViewById(R.id.mailr);
        editText_dept = (EditText) findViewById(R.id.deptr);
        editText_rollno = (EditText) findViewById(R.id.rollnorr);
    }

    private boolean validate() {
        boolean all_ok = true;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        Name = editText_username.getText().toString();
        Email = editText_email.getText().toString();
        RollNo = editText_rollno.getText().toString();
        Department = editText_dept.getText().toString();

        if (Name.isEmpty() || Email.isEmpty() || RollNo.isEmpty() || Department.isEmpty()) {
            Toast.makeText(UserRegistration.this, "Please fill the details", Toast.LENGTH_SHORT).show();
            all_ok = false;
        }else if (!Email.matches(emailPattern)) {
            Toast.makeText(UserRegistration.this, "Please fill the valid email", Toast.LENGTH_SHORT).show();
            all_ok = false;
        }
        else
        {

         if(RollNo.length()==9)
         {

             all_ok=true;
         }

         else
         {
             Toast.makeText(UserRegistration.this, "Please fill the valid Roll Number", Toast.LENGTH_SHORT).show();
             all_ok=false;
         }

        }
        return all_ok;
    }

    public void register_user()
    {
        admin_db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean exists = false;
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String rollno = ds.child("rollno").getValue(String.class);
                    if(rollno.equals(RollNo))
                    {
                        exists = true;
                        break;
                    }
                }

                if(!exists)
                {
                    int new_id = (int)dataSnapshot.getChildrenCount()+1;
                    Vote_User vote_user = new Vote_User(new_id,Name,RollNo,Department,Email);
                    admin_db.child(Integer.toString(new_id)).setValue(vote_user);
                    Toast.makeText(UserRegistration.this, "User is register successfully!!",Toast.LENGTH_SHORT).show();
                    //clearContent();
                }

                else
                    {
                    Toast.makeText(UserRegistration.this,"User already registered!!",Toast.LENGTH_SHORT).show();
                    clearContent();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG,"Error : "+databaseError);
            }
        });
    }
    public void clearContent(){
        editText_username.getText().clear();
        editText_dept.getText().clear();
        editText_rollno.getText().clear();
        editText_email.getText().clear();
    }
}