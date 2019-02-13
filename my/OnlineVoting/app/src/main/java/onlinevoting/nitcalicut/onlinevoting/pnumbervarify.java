package onlinevoting.nitcalicut.onlinevoting;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.firebase.client.FirebaseApp;
import com.firebase.client.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class pnumbervarify extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText e1, e2;
    Button btnotp,btnverify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pnumbervarify);
        e1 = (EditText)findViewById(R.id.etpn);
        e1 = (EditText)findViewById(R.id.etotp);

        btnotp=(Button)findViewById(R.id.buttonsms);
        btnverify=(Button)findViewById(R.id.btnverify);

}

    }
