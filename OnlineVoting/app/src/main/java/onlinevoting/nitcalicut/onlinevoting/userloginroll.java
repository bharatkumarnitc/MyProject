package onlinevoting.nitcalicut.onlinevoting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class userloginroll extends AppCompatActivity {

    private EditText rollnumber;
    private Button  btnsubmit;
    Firebase url;
    private String Roll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userloginroll);
        Firebase.setAndroidContext(this);

        url=new Firebase("https://online-voting-496d6.firebaseio.com/UserRegrestration");
        rollnumber=(EditText)findViewById(R.id.rollnumber);
        btnsubmit=(Button)findViewById(R.id.submituserroll);
        Roll=rollnumber.getText().toString();





    }
}
