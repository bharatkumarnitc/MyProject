package online.nitcalicut.myproject.Control2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import online.nitcalicut.myproject.R;

public class E36_PopupMenu extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e36__popup_menu);

        btn=(Button)findViewById(R.id.E36_btn_Show);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pmneu=new PopupMenu(getApplicationContext(),v);
                pmneu.setOnMenuItemClickListener(E36_PopupMenu.this);
                pmneu.inflate(R.menu.menu_e36__popup_menu);
                pmneu.show();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if(item.getItemId()==R.id.E36_mnu_Blue)
            Toast.makeText(this,"Blue",Toast.LENGTH_LONG).show();
        else if(item.getItemId()==R.id.E36_mnu_Red)
            Toast.makeText(this,"Red",Toast.LENGTH_LONG).show();
        else if(item.getItemId()==R.id.E36_mnu_Green)
            Toast.makeText(this,"Green",Toast.LENGTH_LONG).show();
        else if(item.getItemId()==R.id.E36_mnu_Orange)
            Toast.makeText(this,"Orange",Toast.LENGTH_LONG).show();

        return false;
    }
}

