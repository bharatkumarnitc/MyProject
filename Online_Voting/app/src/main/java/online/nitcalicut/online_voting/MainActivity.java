package online.nitcalicut.online_voting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import online.nitcalicut.online_voting.Admin.AdminLogin;
import online.nitcalicut.online_voting.Admin.adminregister;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void btnadmin(View view) {

        startActivity(new Intent(this,AdminLogin.class));

    }

    public void btnuser(View view) {
    }
}
