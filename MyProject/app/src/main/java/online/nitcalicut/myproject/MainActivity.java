package online.nitcalicut.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import online.nitcalicut.myproject.ASPApi.ASP1_InsertData;
import online.nitcalicut.myproject.ASPApi.ASP2_ListView;
import online.nitcalicut.myproject.Advance.H1_TabLayout;
import online.nitcalicut.myproject.Advance.H2_NavigationDrawer;
import online.nitcalicut.myproject.Advance.H4_GPSLocation;
import online.nitcalicut.myproject.Advance.H4_GPSTracker;
import online.nitcalicut.myproject.Control2.E31_GridViewGallery;
import online.nitcalicut.myproject.Control2.E32_GridViewGalleryText;
import online.nitcalicut.myproject.Control2.E35_OptionMenu;
import online.nitcalicut.myproject.Control2.E36_PopupMenu;
import online.nitcalicut.myproject.Control2.E37_ContextMenu;
import online.nitcalicut.myproject.Controls.B29_ZoomControl;
import online.nitcalicut.myproject.Controls.B6_Checkbox;
import online.nitcalicut.myproject.PHP.Php1_InsertData;
import online.nitcalicut.myproject.PHP.Php2_ListView;
import online.nitcalicut.myproject.PHP.Php3_Spinner;
import online.nitcalicut.myproject.PHP.Php4_AutoCompelete;
import online.nitcalicut.myproject.PHP.Php5_MultiAutoComplete;
import online.nitcalicut.myproject.PHP.PhpM13_Album;
import online.nitcalicut.myproject.PHP.PhpM1_Register;
import online.nitcalicut.myproject.PHP.PhpM2_Login;
import online.nitcalicut.myproject.PHP.PhpM3_Home;
import online.nitcalicut.myproject.PHP.PhpM4_EditProfile;
import online.nitcalicut.myproject.PHP.PhpM5_ChangePassword;
import online.nitcalicut.myproject.PHP.PhpM7_Products;
import online.nitcalicut.myproject.SqLite.Sq10_Search;
import online.nitcalicut.myproject.SqLite.Sq11_AdvanceSearch;
import online.nitcalicut.myproject.SqLite.Sq12_Register;
import online.nitcalicut.myproject.SqLite.Sq13_Login;
import online.nitcalicut.myproject.SqLite.Sq1_InsertSingle;
import online.nitcalicut.myproject.SqLite.Sq2_ReadSingle;
import online.nitcalicut.myproject.SqLite.Sq8_MultipleInsert;
import online.nitcalicut.myproject.SqLite.Sq9_ReadMultiple;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insert(View view) {
        startActivity(new Intent(this,H1_TabLayout.class));
    }

    public void search(View view) {
        startActivity(new Intent(this, E37_ContextMenu.class)); }
}
