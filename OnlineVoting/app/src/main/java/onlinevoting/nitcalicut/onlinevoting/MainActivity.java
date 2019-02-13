package onlinevoting.nitcalicut.onlinevoting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import onlinevoting.nitcalicut.onlinevoting.helper.SharedprefHelper;
import onlinevoting.nitcalicut.onlinevoting.model.Admin_user;


public class MainActivity extends AppCompatActivity {

    private Button Login;
    private EditText Email,Password;
    private TextView admin_registration;
    FirebaseAuth mAuth;
    private DatabaseReference DR;
    ProgressDialog pd;
    SharedprefHelper sharedprefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        DR =DR= FirebaseDatabase.getInstance().getReference().child("register_admin");
        pd=new ProgressDialog(this);
        sharedprefHelper = new SharedprefHelper(this);
        admin_registration=(TextView)findViewById(R.id.tvSignUp);
        Login=(Button)findViewById(R.id.btnAdminLogin);
        Email=(EditText)findViewById(R.id.etAdminEmail);
        Password=(EditText)findViewById(R.id.etAdminPassword);

        if(sharedprefHelper.isLoggedIn()){
            startActivity(new Intent(MainActivity.this, AdminNavigation.class));
            return;
        }

        admin_registration.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //move admin to admin registration page
                startActivity(new Intent(MainActivity.this,Registration.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to main admin page
                if(validate())
                    startsign();

            }
        });

    }

    private void startsign() {

        pd.setMessage("Sign in \n Please Wait...");
        pd.show();

        String email, password;
        email = Email.getText().toString().trim();
        password = Password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pd.dismiss();
                        if (task.isSuccessful()) {
                            String userid = mAuth.getCurrentUser().getUid();
                            DR.child(userid).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Admin_user user = dataSnapshot.getValue(Admin_user.class);
                                    sharedprefHelper.userLogin(user);
                                    startActivity(new Intent(MainActivity.this, AdminNavigation.class));
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Log.e("MainActivity","Error:"+databaseError);
                                }
                            });

                        } else {
                            Toast.makeText(MainActivity.this, "Email id and Password incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Problem with network", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }

    private Boolean validate()
    {
        Boolean result=false;
        if(Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty())
            Toast.makeText(this,"Please Enter Details",Toast.LENGTH_SHORT).show();
        else
            result=true;

        return result;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
