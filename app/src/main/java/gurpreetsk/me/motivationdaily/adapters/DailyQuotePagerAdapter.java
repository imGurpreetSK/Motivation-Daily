package gurpreetsk.me.motivationdaily.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import gurpreetsk.me.motivationdaily.fragments.DailyQuoteFragment;
import gurpreetsk.me.motivationdaily.fragments.QuoteFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;

/**
 * Created by Gurpreet on 30/12/16.
 */

public class DailyQuotePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> dailyQuotes;


    public DailyQuotePagerAdapter(FragmentManager fm, ArrayList<String> dailyQuotes) {
        super(fm);
        this.dailyQuotes = dailyQuotes;
    }


    @Override
    public Fragment getItem(int position) {
//        DailyQuoteFragment quoteFragment;
//        if (position == 5) {
//            quoteFragment = new DailyQuoteFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString(Constants.QUOTE_KEY, "Read more");
//            quoteFragment.setArguments(bundle);
//        } else {
        DailyQuoteFragment quoteFragment = new DailyQuoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.QUOTE_KEY, dailyQuotes.get(position));
        quoteFragment.setArguments(bundle);
//        }
        return quoteFragment;
    }


    @Override
    public int getCount() {
        return dailyQuotes.size() ;
    }


}
