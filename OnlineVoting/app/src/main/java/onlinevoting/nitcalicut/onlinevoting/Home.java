package onlinevoting.nitcalicut.onlinevoting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button BtnAdmin,BtnUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BtnAdmin=(Button)findViewById(R.id.btnAdmin);
        BtnUser=(Button)findViewById(R.id.admin_signin);


        BtnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move u to the admin login part
                startActivity(new Intent(Home.this,MainActivity.class));
            }
        });

        BtnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move u to the user login part
                startActivity(new Intent(Home.this,phonelogin.class));
            }
        });
    }
}
