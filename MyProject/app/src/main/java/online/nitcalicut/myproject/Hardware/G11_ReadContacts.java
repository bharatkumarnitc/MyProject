package online.nitcalicut.myproject.Hardware;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import online.nitcalicut.myproject.R;

public class G11_ReadContacts extends AppCompatActivity {
    ListView lvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g11__read_contacts);

        lvContacts = (ListView)findViewById(R.id.G11_lst);

        ContentResolver cr = getContentResolver();

        // Read Contacts
        Cursor c = cr.query(ContactsContract.Contacts.CONTENT_URI,
                new String[] {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME }, null, null, null);

        // Attached with cursor with Adapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_g11__read_contacts_row, c,
                new String[] { ContactsContract.Contacts.DISPLAY_NAME },
                new int[] { R.id.G11_lblName });

        // Display data in listview
        lvContacts.setAdapter(adapter);

        // On Click of each row of contact display next screen with contact
        // number
        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                Cursor c = (Cursor) adapter.getItemAtPosition(position);
                String cid = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                // Explicit Intent Example
                Intent i = new Intent(getApplicationContext(), G11_ContactDetails.class);
                i.putExtra("id", cid);
                startActivity(i);
            }
        });
    }
}


