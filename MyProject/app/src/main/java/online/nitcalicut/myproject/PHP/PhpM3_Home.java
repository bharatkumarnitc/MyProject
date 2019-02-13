package online.nitcalicut.myproject.PHP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import online.nitcalicut.myproject.R;

public class PhpM3_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_php_m3__home);
    }

    public void php_m3_Reigster(View view) {

        startActivity(new Intent(this, PhpM4_EditProfile.class));


    }



    public void php_m3_chngpass(View view) {

        startActivity(new Intent(this, PhpM5_ChangePassword.class));

    }

    public void php3_mlogout(View view) {

        startActivity(new Intent(this, PhpM6_Logout.class));
    }
}
