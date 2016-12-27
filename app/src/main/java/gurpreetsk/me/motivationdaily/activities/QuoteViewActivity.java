package gurpreetsk.me.motivationdaily.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.fragments.QuoteFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;

public class QuoteViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_view);

        String quote = getIntent().getStringExtra(Constants.QUOTE_KEY);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.QUOTE_KEY, quote);
        QuoteFragment quoteFragment = new QuoteFragment();
        quoteFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.quote_fragment_container, quoteFragment)
                .commit();

    }
}
