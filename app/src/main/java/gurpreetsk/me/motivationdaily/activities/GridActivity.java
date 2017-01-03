package gurpreetsk.me.motivationdaily.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.transition.Fade;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.adapters.DailyQuotePagerAdapter;
import gurpreetsk.me.motivationdaily.fragments.GridFragment;
import gurpreetsk.me.motivationdaily.fragments.TagsFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;

public class GridActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.daily_quote_viewpager)
    ViewPager dailyQuoteViewPager;
    @BindView(R.id.authors_list_textview)
    TextView TV_AllAuthors;
    @BindView(R.id.view_all_textview)
    TextView TV_ViewAllAuthors;
    @BindView(R.id.tags_list_textview)
    TextView TV_AllTags;
    @BindView(R.id.view_all_tags_textview)
    TextView TV_ViewAllTags;


    private static final String TAG = "GridActivity";
    private FirebaseAnalytics mFirebaseAnalytics;

    ArrayList<String> authorsList;
    ArrayList<String> tagsList;
    ArrayList<String> dailyQuotesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        authorsList = getIntent().getStringArrayListExtra(Constants.AUTHORS_KEY);
        dailyQuotesList = getIntent().getStringArrayListExtra(Constants.DAILY_QUOTES);
        tagsList = getIntent().getStringArrayListExtra(Constants.TAGS_CATEGORIES);

        TV_AllAuthors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridActivity.this, AuthorsActivity.class);
                intent.putStringArrayListExtra(Constants.AUTHORS_KEY, authorsList);
                startActivity(intent);
            }
        });
        TV_ViewAllAuthors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridActivity.this, AuthorsActivity.class);
                intent.putStringArrayListExtra(Constants.AUTHORS_KEY, authorsList);
                startActivity(intent);
            }
        });
        TV_AllTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridActivity.this, TagsActivity.class);
                intent.putStringArrayListExtra(Constants.TAGS_CATEGORIES, tagsList);
                startActivity(intent);
            }
        });
        TV_ViewAllTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GridActivity.this, TagsActivity.class);
                intent.putStringArrayListExtra(Constants.TAGS_CATEGORIES, tagsList);
                startActivity(intent);
            }
        });

        PagerAdapter pagerAdapter = new DailyQuotePagerAdapter(getSupportFragmentManager(), dailyQuotesList);
        dailyQuoteViewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(dailyQuoteViewPager, true);

        Bundle authorBundle = new Bundle();
        authorBundle.putStringArrayList(Constants.AUTHORS_KEY, authorsList);
        GridFragment gridFragment = new GridFragment();
        gridFragment.setArguments(authorBundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_grid_fragment_container, gridFragment).commit();

        Bundle tagsBundle = new Bundle();
        tagsBundle.putStringArrayList(Constants.TAGS_CATEGORIES, tagsList);
        TagsFragment tagsFragment = new TagsFragment();
        tagsFragment.setArguments(tagsBundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.tags_main_grid_fragment_container, tagsFragment).commit();


//        dailyQuoteViewPager.setPageTransformer(true, new DepthPageTransformer());
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.main_grid_fragment_container2, gridFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_favorites:
                startActivity(new Intent(this, FavoritesActivity.class));
                break;
            case R.id.share_action:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app_intent));
                startActivity(Intent.createChooser(intent, getString(R.string.share_via)));
        }
        return true;
    }

}
