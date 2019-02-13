package online.nitcalicut.myproject.Advance;

import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import online.nitcalicut.myproject.R;


public class H2_NDBaseActivity extends AppCompatActivity {
    ImageView ProfileImage;
    LayoutInflater layoutInflater;
    ActionMenuView actionMenuView;
    String ProfileName;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void setBaseContentView(int id) {
        layoutInflater = getLayoutInflater();

        View contentView = layoutInflater.inflate(id, null);  //child
        View baseView = layoutInflater.inflate(R.layout.activity_h2__ndbase, null);  //parent

        FrameLayout layout = (FrameLayout) baseView.findViewById(R.id.cantainer);
        layout.addView(contentView);
        setContentView(baseView);

        actionMenuView = (ActionMenuView) findViewById(R.id.setting_menu_view);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.h2_setting_menu, actionMenuView.getMenu());

        actionMenuView.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.h2_mnu_AdvanceSearch:
                        //startActivity(new Intent(getBaseContext(), A14_AdvanceSearch.class));
                        break;
                    case R.id.h2_mnu_ProfilePicture:
                        //startActivity(new Intent(getBaseContext(), A15_ProfilePhoto.class));
                        break;
                    case R.id.h2_mnu_category:
                        //startActivity(new Intent(getBaseContext(), A4_ShowCategory.class));
                        break;
                    case R.id.h2_mnu_edit_profile:
                        //startActivity(new Intent(getBaseContext(), A11_EditProfile.class));
                        break;
                    case R.id.h2_mnu_logout:
//                        SharedPreferences pref = getApplicationContext().getSharedPreferences("GTel", 0); // 0 - for private mode
//                        SharedPreferences.Editor editor = pref.edit();
//                        editor.putString("Name", ""); // Storing boolean - true/false
//                        editor.putString("Mobile", ""); // Storing boolean - true/false
//                        editor.putString("StateId", ""); // Storing boolean - true/false
//                        editor.putString("PhotoUrl", ""); // Storing boolean - true/false
//                        editor.putBoolean("IsRegConfirm", false); // Storing boolean - true/false
//                        editor.commit(); // commit changes

                        //startActivity(new Intent(getBaseContext(), A5_Login.class));
                        finish();
                        break;
                }
                return false;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        TextView txt = (TextView) drawerLayout.findViewById(R.id.profile_name);
        txt.setText(ProfileName);

        ProfileImage = (ImageView) drawerLayout.findViewById(R.id.profile_image);
        ProfileImage.setImageResource(R.drawable.math);

        ActionBarDrawerToggle mDrawerToggle;

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {

                public void onDrawerClosed(View view) {
                    supportInvalidateOptionsMenu();
                    //drawerOpened = false;
                }

                public void onDrawerOpened(View drawerView) {
                    supportInvalidateOptionsMenu();
                    //drawerOpened = true;
                }
            };
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            drawerLayout.setDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        }
   }
}

