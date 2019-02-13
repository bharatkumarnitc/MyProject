package online.nitcalicut.myproject.Advance;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import online.nitcalicut.myproject.R;


public class H1_TabLayout extends AppCompatActivity
{
    TabLayout tabLayout;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h1__tab_layout);

        tabLayout = (TabLayout) findViewById(R.id.H1_tabLayout);
        pager = (ViewPager) findViewById(R.id.H1_viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Tab1")); //set tab layout name
        tabLayout.addTab(tabLayout.newTab().setText("Tab2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        H1_PagerAdapter adapter = new H1_PagerAdapter (getSupportFragmentManager(),tabLayout.getTabCount());
        pager.setAdapter(adapter);  //set tablayout in screen

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));  //swipe with figure



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                pager.setCurrentItem(tab.getPosition());   //it is going to H1 pageadapter class
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}