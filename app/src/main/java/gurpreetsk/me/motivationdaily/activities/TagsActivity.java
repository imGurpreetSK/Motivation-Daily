package gurpreetsk.me.motivationdaily.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.adapters.AuthorAdapter;
import gurpreetsk.me.motivationdaily.adapters.TagsAdapter;
import gurpreetsk.me.motivationdaily.utils.Constants;

public class TagsActivity extends AppCompatActivity {

    @BindView(R.id.all_tags_grid_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    ArrayList<String> tagsList;
    RecyclerView.LayoutManager gridLayoutManager;
    //For phones
    public static final int PORTRAIT_LESS_NO_OF_COLUMNS = 3;
    public static final int LANDSCAPE_LESS_NO_OF_COLUMNS = 2;
    //For tablets
    public static final int PORTRAIT_MORE_NO_OF_COLUMNS = 4;    //TODO: set these
    public static final int LANDSCAPE_MORE_NO_OF_COLUMNS = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.all_tags));

        tagsList = getIntent().getStringArrayListExtra(Constants.TAGS_CATEGORIES);

        TagsAdapter gridAdapter = new TagsAdapter(this, tagsList);

        int orientation = getResources().getConfiguration().orientation;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        if (orientation == Configuration.ORIENTATION_PORTRAIT && dpWidth < 600)
            gridLayoutManager = new GridLayoutManager(this, PORTRAIT_LESS_NO_OF_COLUMNS, GridLayoutManager.HORIZONTAL, false);
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE && dpHeight < 600)
            gridLayoutManager = new GridLayoutManager(this, LANDSCAPE_LESS_NO_OF_COLUMNS, GridLayoutManager.HORIZONTAL, false);
        else if (orientation == Configuration.ORIENTATION_PORTRAIT && dpWidth >= 600)
            gridLayoutManager = new GridLayoutManager(this, PORTRAIT_MORE_NO_OF_COLUMNS, GridLayoutManager.HORIZONTAL, false);
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE && dpHeight >= 600)
            gridLayoutManager = new GridLayoutManager(this, LANDSCAPE_MORE_NO_OF_COLUMNS, GridLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
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
