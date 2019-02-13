package online.nitcalicut.myproject.Control2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class E37_ContextMenu extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e37__context_menu);

        txt = (TextView) findViewById(R.id.E37_txt);
        registerForContextMenu(txt);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo info) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.setHeaderTitle("This is My Menu");
        menu.add("Red");
        menu.add("Yellow");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //noinspection SimplifiableIfStatement
        if (item.getTitle().equals("Red")) {
            Toast.makeText(this, "Red", Toast.LENGTH_LONG).show();
            return true;
        } else if (item.getTitle().equals("Yellow")) {
            Toast.makeText(this, "Yellow", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}