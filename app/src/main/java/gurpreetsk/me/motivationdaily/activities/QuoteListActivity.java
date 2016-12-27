package gurpreetsk.me.motivationdaily.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.fragments.QuoteListFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;

public class QuoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        setTitle(getIntent().getStringExtra(Constants.AUTHOR_NAME_KEY));

        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Constants.QUOTES_KEY, getIntent().getStringArrayListExtra(Constants.QUOTES_KEY));
        QuoteListFragment quoteListFragment = new QuoteListFragment();
        quoteListFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.quote_list_container, quoteListFragment)
                .commit();

    }
}
