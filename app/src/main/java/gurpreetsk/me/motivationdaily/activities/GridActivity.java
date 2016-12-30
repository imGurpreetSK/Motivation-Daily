package gurpreetsk.me.motivationdaily.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.fragments.GridFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;

public class GridActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.daily_quote_textview)
    TextView TV_DailyQuote;
    @BindView(R.id.daily_quote_author_textview)
    TextView TV_AuthorDailyQuote;

    private static final String TAG = "GridActivity";
    //    String[] data ;
    ArrayList<String> authorsList;
    ArrayList<String> dailyQuotesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        authorsList = getIntent().getStringArrayListExtra(Constants.AUTHORS_KEY);
        dailyQuotesList = getIntent().getStringArrayListExtra(Constants.DAILY_QUOTES);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Constants.AUTHORS_KEY, authorsList);
        GridFragment gridFragment = new GridFragment();
        gridFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_grid_fragment_container, gridFragment).commit();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.main_grid_fragment_container2, gridFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_favorites:
                startActivity(new Intent(this, FavoritesActivity.class));
        }
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putStringArray(Constants.DAILY_QUOTES, data);
//        outState.putStringArrayList(Constants.QUOTES_KEY, authorsList);
    }

    private class QuoteAsyncTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            String[] data = new String[2];
//            ArrayList<DailyQuotes> data = new ArrayList<>();
            try {
                Document doc = Jsoup.connect("https://www.brainyquote.com/quotes_of_the_day.html").get();
                data[0] = doc.select("span.bqQuoteLink a").first().html();
                data[1] = doc.select("div.bq-aut a").first().html();
//                data[2] = doc.select("span.bqQuoteLink a").html();
//                data[3] = doc.select("div.bq-aut a").first().html();
//                data[4] = doc.select("span.bqQuoteLink a").first().html();
//                data[5] = doc.select("div.bq-aut a").first().html();
                Log.i(TAG, "doInBackground: " + Arrays.toString(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

    }

}
