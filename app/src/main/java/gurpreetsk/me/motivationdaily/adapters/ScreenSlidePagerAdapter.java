package gurpreetsk.me.motivationdaily.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import gurpreetsk.me.motivationdaily.fragments.QuoteFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;

/**
 * Created by Gurpreet on 27/12/16.
 */

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> quotes;


    public ScreenSlidePagerAdapter(FragmentManager fm, ArrayList<String> quotes) {
        super(fm);
        this.quotes = quotes;
    }


    @Override
    public Fragment getItem(int position) {
        QuoteFragment quoteFragment = new QuoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.QUOTE_KEY, quotes.get(position));
        quoteFragment.setArguments(bundle);
        return quoteFragment;
    }


    @Override
    public int getCount() {
        return quotes.size();
    }

}