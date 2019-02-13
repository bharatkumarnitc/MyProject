package online.nitcalicut.myproject.Hardware;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import online.nitcalicut.myproject.R;

public class G14_SendMail extends AppCompatActivity {
    EditText txtTo,txtSubject,txtMessage;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g14__send_mail);

        txtTo=(EditText)findViewById(R.id.G14_txt_To);
        txtSubject=(EditText)findViewById(R.id.G14_txt_Subject);
        txtMessage=(EditText)findViewById(R.id.G14_txt_Message);

        send=(Button)findViewById(R.id.btn_SendMsg);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {

                ProgressDialog pd = new ProgressDialog(G14_SendMail.this);
                pd.setMessage("Sending ......");
                pd.show();

                String to=txtTo.getText().toString();
                String subject=txtSubject.getText().toString();
                String message=txtMessage.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
                pd.dismiss();
            }
        });
    }
}
