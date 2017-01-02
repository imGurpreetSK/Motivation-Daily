package gurpreetsk.me.motivationdaily.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gurpreetsk.me.motivationdaily.fragments.intro.Fragment1;
import gurpreetsk.me.motivationdaily.fragments.intro.Fragment2;
import gurpreetsk.me.motivationdaily.fragments.intro.Fragment3;
import gurpreetsk.me.motivationdaily.fragments.intro.Fragment4;

/**
 * Created by gurpreet on 01/01/17.
 */

public class SplashScreenPagerAdapter extends FragmentStatePagerAdapter {

    public SplashScreenPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
            case 3:
                return new Fragment4();
        }
        return new Fragment4();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
