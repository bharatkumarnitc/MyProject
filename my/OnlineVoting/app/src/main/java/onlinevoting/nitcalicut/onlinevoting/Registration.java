package onlinevoting.nitcalicut.onlinevoting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import onlinevoting.nitcalicut.onlinevoting.model.Admin_user;

public class Registration extends AppCompatActivity {

    private EditText username,useremail,userpassword;
    private Button btn_signup;
    private TextView txt_signin;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference DR;
    private ProgressDialog pd;
    private String Email,Password,Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();
        pd=new ProgressDialog(this);

        firebaseAuth=FirebaseAuth.getInstance();
        DR=FirebaseDatabase.getInstance().getReference().child("register_admin");
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate())
                {
                    //database
                    pd.setMessage("Signing Up.. ");
                    pd.show();
                    final String Email=useremail.getText().toString().trim();
                    final String Password=userpassword.getText().toString().trim();
                    final String Name = username.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Registration.this,"Registration successfull",Toast.LENGTH_SHORT).show();
                                String userid=firebaseAuth.getCurrentUser().getUid();
                                DatabaseReference current_user=DR.child(userid);
                                Admin_user admin_user = new Admin_user(userid,Name,
                                        Email,Password);
                                current_user.setValue(admin_user);
                                startActivity(new Intent(Registration.this,MainActivity.class));
                                pd.dismiss();
                            }
                            else
                            {
                                Toast.makeText(Registration.this,"Regrestration Failed",Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }

                        }
                    });
                }

            }
        });


        txt_signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //move back to admin login page
                startActivity(new Intent(Registration.this,MainActivity.class));
            }
        });

    }

    private void setupUIViews()
    {
        username=(EditText)findViewById(R.id.admin_username);
        useremail=(EditText)findViewById(R.id.admin_email);
        userpassword=(EditText)findViewById(R.id.admin_password);
        btn_signup=(Button)findViewById((R.id.admin_signup));
        txt_signin=(TextView)findViewById(R.id.admin_signin);
    }

    private Boolean validate()
    {
        Boolean result=false;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        Name=username.getText().toString();
        Email=useremail.getText().toString();
        Password=userpassword.getText().toString();

        if(Name.isEmpty() || Email.isEmpty() || Password.isEmpty())
          Toast.makeText(this,"Please Enter Details",Toast.LENGTH_SHORT).show();

        else if (!Email.matches(emailPattern)) {
            Toast.makeText(Registration.this,"Please fill the valid email", Toast.LENGTH_SHORT).show();
            result = false;
        }
        else
            result=true;
        return result;
    }

}
