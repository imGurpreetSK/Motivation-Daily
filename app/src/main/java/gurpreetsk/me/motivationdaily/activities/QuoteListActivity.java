package gurpreetsk.me.motivationdaily.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.fragments.QuoteFragment;
import gurpreetsk.me.motivationdaily.fragments.QuoteListFragment;
import gurpreetsk.me.motivationdaily.utils.AuthorImageUrl;
import gurpreetsk.me.motivationdaily.utils.Constants;

public class QuoteListActivity extends AppCompatActivity implements QuoteListFragment.Callback {

    //    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    //    @BindView(R.id.detail_image_view)
    ImageView authorImage;
    Toolbar toolbar;
    LinearLayout twoPaneView;

    public static boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO:NPE because of this
//        if(getSupportActionBar()!=null)
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String sender = getIntent().getStringExtra(Constants.SENDER_KEY);

        if (sender.equals("Author")) {
            setContentView(R.layout.activity_quote_list);
            ButterKnife.bind(this);
        } else if (sender.equals("Tags")) {
            setContentView(R.layout.activity_tags_list);
            ButterKnife.bind(this);
        }

        mTwoPane = findViewById(R.id.two_pane_view) != null;
        String authorName = getIntent().getStringExtra(Constants.AUTHOR_NAME_KEY);
        String tag = getIntent().getStringExtra(Constants.TAG_CATEGORY);

        if (!mTwoPane && sender.equals("Author")) {
            collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
            authorImage = (ImageView) findViewById(R.id.detail_image_view);
            collapsingToolbarLayout.setTitle(authorName);
            Glide.with(this)
                    .load(AuthorImageUrl.getAuthorImage(authorName))
                    .into(authorImage);
        } else if (!mTwoPane && sender.equals("Tags")) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(tag);
        } else if (mTwoPane && sender.equals("Author")) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(authorName);
        }
        else if(mTwoPane && sender.equals("Tags")){
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(tag);
        }

        Bundle bundle = new Bundle();

        if (sender.equals("Author")) {
            bundle.putStringArrayList(Constants.QUOTES_KEY, getIntent().getStringArrayListExtra(Constants.QUOTES_KEY));
            bundle.putString(Constants.AUTHOR_NAME_KEY, authorName);
            QuoteListFragment quoteListFragment = new QuoteListFragment();
            quoteListFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.quote_list_container, quoteListFragment)
                    .commit();
        } else if (sender.equals("Tags")) {
            bundle.putStringArrayList(Constants.QUOTES_KEY, getIntent().getStringArrayListExtra(Constants.TAGS_QUOTES));
            bundle.putString(Constants.AUTHOR_NAME_KEY, tag);
            QuoteListFragment quoteListFragment = new QuoteListFragment();
            quoteListFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.tags_quote_list_container, quoteListFragment)
                    .commit();
        }

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!mTwoPane)
            supportFinishAfterTransition();
    }

    @Override
    public void OnItemSelected(ArrayList<String> quotes, String authorName, int position) {

        if (mTwoPane) {

            QuoteFragment quoteFragment = new QuoteFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.QUOTE_KEY, quotes.get(position));
            quoteFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.quote_fragment_container, quoteFragment)
                    .commit();

        } else {

            Intent intent = new Intent(this, QuoteViewActivity.class);
            intent.putStringArrayListExtra(Constants.QUOTES_KEY, quotes);
            intent.putExtra(Constants.QUOTE_NUMBER_KEY, position);
            startActivity(intent);

        }
    }
}
