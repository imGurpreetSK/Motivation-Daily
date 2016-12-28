package gurpreetsk.me.motivationdaily.activities;

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

public class FavoritesActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);
        ButterKnife.bind(this);

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

}
