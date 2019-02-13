package online.nitcalicut.myproject.Control2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class E38_SharedPreferences extends AppCompatActivity {
    EditText txtName, txtMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e38__shared_preferences);

        txtMobile=(EditText)findViewById(R.id.E38_txt_Mobile);
        txtName=(EditText)findViewById(R.id.E38_txt_Name);

    }

    public void E38_fun_Save(View v) {
        SharedPreferences pref=getApplicationContext().getSharedPreferences("myPref",0);
        SharedPreferences.Editor edit=pref.edit();

        edit.putString("Name",txtName.getText().toString());
        edit.putString("Mobile",txtMobile.getText().toString());

        edit.commit();
    }

    public void E38_fun_Show(View v) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", 0);
        String name=pref.getString("Name","");
        Toast.makeText(this,name, Toast.LENGTH_LONG).show();

    }
}

