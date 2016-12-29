package gurpreetsk.me.motivationdaily.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.data.Database;
import gurpreetsk.me.motivationdaily.data.QuotesTable;
import gurpreetsk.me.motivationdaily.fragments.FavoritesListFragment;
import gurpreetsk.me.motivationdaily.fragments.QuoteFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;

public class FavoritesActivity extends AppCompatActivity implements FavoritesListFragment.FavoritesCallback {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    public static boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);
        ButterKnife.bind(this);

        mTwoPane = findViewById(R.id.favorites_two_pane_view) != null;

//        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.favorites));

        FavoritesListFragment favoritesListFragment = new FavoritesListFragment();
        Bundle bundle = getAllFavorites();
        favoritesListFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.favorites_fragment_container, favoritesListFragment)
                .commit();

    }


    private Bundle getAllFavorites() {
        Bundle bundle = new Bundle();
        Cursor c = getContentResolver().query(QuotesTable.CONTENT_URI, null, null, null, null);
        List<Database> list = QuotesTable.getRows(c, true);     // all rows of database
        ArrayList<String> idList = new ArrayList<>();
        for (Database element : list) {
            idList.add(element.quote);      // add all quotes in arraylist and return
        }
        bundle.putStringArrayList("ArrayList", idList);
        return bundle;
    }


    @Override
    public void OnFavoriteItemSelected(ArrayList<String> favorites, int position) {

        if (mTwoPane) {

            QuoteFragment quoteFragment = new QuoteFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.QUOTE_KEY, favorites.get(position));
            quoteFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.favorites_quote_fragment_container, quoteFragment)
                    .commit();

        } else {

            Intent intent = new Intent(this, QuoteViewActivity.class);
            intent.putStringArrayListExtra(Constants.QUOTES_KEY, favorites);
            intent.putExtra(Constants.QUOTE_NUMBER_KEY, position);
            startActivity(intent);

        }
    }

}
