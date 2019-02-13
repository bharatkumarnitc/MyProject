package onlinevoting.nitcalicut.onlinevoting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import onlinevoting.nitcalicut.onlinevoting.helper.SharedprefHelper;
import onlinevoting.nitcalicut.onlinevoting.model.Admin_user;

public class AdminNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedprefHelper sharedprefHelper;
    private TextView admin_name,admin_email;
    private ImageView admin_image;

    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedprefHelper = new SharedprefHelper(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        admin_name = navigationView.getHeaderView(0).findViewById(R.id.admin_name);
        admin_email = navigationView.getHeaderView(0).findViewById(R.id.admin_email);
        admin_image = navigationView.getHeaderView(0).findViewById(R.id.admin_image);

        Admin_user user = sharedprefHelper.getUser();
        admin_email.setText(user.getAdmin_email());
        admin_name.setText(user.getAdmin_name());

        Glide.with(this)
                .load("https://project.hubzilla.org/cloud/admin/Profile%20Photos/administrator-300px.png")
                .thumbnail(0.5f)
                .apply(RequestOptions.circleCropTransform())
                .into(admin_image);

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
            }
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.position_name) {
            startActivity(new Intent(AdminNavigation.this,Position_name.class));
        }
        else if (id == R.id.candidate_name) {
            startActivity(new Intent(AdminNavigation.this,Candidateadd.class));
        }
       else if(id == R.id.user_registration){
            startActivity(new Intent(AdminNavigation.this,UserRegistration.class));
        }
        else if (id == R.id.admin_logout) {
            sharedprefHelper.logout();
            startActivity(new Intent(AdminNavigation.this,MainActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
