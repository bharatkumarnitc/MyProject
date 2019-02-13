package online.nitcalicut.myproject.Advance;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Lenovo on 07/08/2018.
 */

public class H1_PagerAdapter extends FragmentStatePagerAdapter   //inherit the class of fragmentstatepageradapter
{
    int mNumOfTabs;

    public H1_PagerAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }       //fragmentstatepageradapter have two function firs one is getitem and second is getcount
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                H1_tab1 tab1 = new H1_tab1();
                return tab1;
            case 1:
                H1_tab2 tab2 = new H1_tab2();
                return tab2;
            case 2:
                H1_tab3 tab3 = new H1_tab3();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }
}

