package gurpreetsk.me.motivationdaily.activities;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.adapters.AuthorAdapter;
import gurpreetsk.me.motivationdaily.utils.Constants;

public class AuthorsActivity extends AppCompatActivity {

    @BindView(R.id.all_authors_grid_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    ArrayList<String> authorNameList;
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
        setContentView(R.layout.activity_authors);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        authorNameList = getIntent().getStringArrayListExtra(Constants.AUTHORS_KEY);

        AuthorAdapter gridAdapter = new AuthorAdapter(this, authorNameList);

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
}
