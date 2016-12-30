package gurpreetsk.me.motivationdaily.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.adapters.AuthorAdapter;
import gurpreetsk.me.motivationdaily.adapters.DailyQuotePagerAdapter;
import gurpreetsk.me.motivationdaily.fragments.GridFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;
import gurpreetsk.me.motivationdaily.utils.DepthPageTransformer;

public class GridActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.daily_quote_viewpager)
    ViewPager dailyQuoteViewPager;
    @BindView(R.id.authors_list_textview)
    TextView TV_AllAuthors;
    @BindView(R.id.view_all_textview)
    TextView TV_ViewAll;


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

        TV_AllAuthors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridActivity.this, AuthorsActivity.class);
                intent.putStringArrayListExtra(Constants.AUTHORS_KEY, authorsList);
                startActivity(intent);
            }
        });
        TV_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridActivity.this, AuthorsActivity.class);
                intent.putStringArrayListExtra(Constants.AUTHORS_KEY, authorsList);
                startActivity(intent);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Constants.AUTHORS_KEY, authorsList);
        GridFragment gridFragment = new GridFragment();
        gridFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_grid_fragment_container, gridFragment).commit();

        PagerAdapter pagerAdapter = new DailyQuotePagerAdapter(getSupportFragmentManager(), dailyQuotesList);
        dailyQuoteViewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(dailyQuoteViewPager, true);
//        dailyQuoteViewPager.setPageTransformer(true, new DepthPageTransformer());
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

}
