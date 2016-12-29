package gurpreetsk.me.motivationdaily.activities;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.adapters.ScreenSlidePagerAdapter;
import gurpreetsk.me.motivationdaily.utils.Constants;
import gurpreetsk.me.motivationdaily.utils.DepthPageTransformer;

public class QuoteViewActivity extends AppCompatActivity {

    @BindView(R.id.quotePager)
    ViewPager quotePager;

    ArrayList<String> quotes;
    int quoteNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_view);
        ButterKnife.bind(this);

//        setSupportActionBar(toolbar);
        //TODO:NPE because of this

        quotes = getIntent().getStringArrayListExtra(Constants.QUOTES_KEY);
        quoteNumber = getIntent().getIntExtra(Constants.QUOTE_NUMBER_KEY, 0);

        PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), quotes);
        quotePager.setAdapter(pagerAdapter);
        quotePager.setPageTransformer(true, new DepthPageTransformer());
        quotePager.setCurrentItem(quoteNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quote_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
