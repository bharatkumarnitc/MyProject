package online.nitcalicut.myproject.Controls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import online.nitcalicut.myproject.R;

public class B21_AutoTextComplete extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    String arrCity[] = {"Ammm","aaaa","Bbbbb","bbbbbb","Cccccc","Ccccc"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b21__auto_text_complete);
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.b21_AutoComplete);

        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrCity);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(3);

    }
}
