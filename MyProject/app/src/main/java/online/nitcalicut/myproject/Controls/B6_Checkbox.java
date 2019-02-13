package online.nitcalicut.myproject.Controls;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class B6_Checkbox extends AppCompatActivity {
    CheckBox box1,box2,box3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b6__checkbox);

        box1=(CheckBox)findViewById(R.id.b6_a);
        box2=(CheckBox)findViewById(R.id.b6_b);
        box3=(CheckBox)findViewById(R.id.b6_c);

    }

    public void B7_show(View view) {

        int totalamount=0;
        StringBuilder result=new StringBuilder();
        result.append("Selected Items:");
        if(box1.isChecked())
        {
            result.append("\nPizza 100Rs");
            totalamount+=100;
        }
        if(box2.isChecked()){
            result.append("\nburger 50Rs");
            totalamount+=50;
        }
        if(box3.isChecked()){
            result.append("\nmomoz 120Rs");
            totalamount+=120;
        }
        result.append("\nTotal: "+totalamount+"Rs");
        //Displaying the message on the toast
        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();





    }
}
