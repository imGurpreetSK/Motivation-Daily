package gurpreetsk.me.motivationdaily.activities;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.adapters.ScreenSlidePagerAdapter;
import gurpreetsk.me.motivationdaily.fragments.QuoteFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;

public class QuoteViewActivity extends AppCompatActivity {

    @BindView(R.id.quotePager)
    ViewPager quotePager;
    private PagerAdapter pagerAdapter;

    ArrayList<String> quotes;
    int quoteNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_view);
        ButterKnife.bind(this);

        quotes = getIntent().getStringArrayListExtra(Constants.QUOTES_KEY);
        quoteNumber = getIntent().getIntExtra(Constants.QUOTE_NUMBER_KEY, 0);

        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), quotes);
        quotePager.setAdapter(pagerAdapter);
        quotePager.setCurrentItem(quoteNumber);

    }
}
