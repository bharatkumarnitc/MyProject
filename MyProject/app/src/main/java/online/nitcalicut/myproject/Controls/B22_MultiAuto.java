package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B22_MultiAuto extends AppCompatActivity {
    String arrCity[] = {"Ammm", "aaaa", "Bbbbb", "bbbbbb", "Cccccc", "Ccccc"};
    MultiAutoCompleteTextView multiAutoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b22__multi_auto);
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.b22_MultiAuto);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrCity);
        multiAutoCompleteTextView.setAdapter(adapter);

        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


    }

    public void b22_funShow(View view) {
        String s = multiAutoCompleteTextView.getText().toString();
        s = s.substring(0, s.length() - 2);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
