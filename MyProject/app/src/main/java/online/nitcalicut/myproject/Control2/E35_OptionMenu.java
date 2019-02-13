package online.nitcalicut.myproject.Control2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class E35_OptionMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e35__option_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_e35__option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.E35_mnu_File) {
            Toast.makeText(this,"File",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.E35_mnu_Save) {
            Toast.makeText(this,"Save",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.E35_mnu_File) {
            Toast.makeText(this,"File",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.E35_mnu_File) {
            Toast.makeText(this,"File",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.E35_mnu_File) {
            Toast.makeText(this,"File",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

