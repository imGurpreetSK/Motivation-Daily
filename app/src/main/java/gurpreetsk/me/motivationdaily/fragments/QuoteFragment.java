package gurpreetsk.me.motivationdaily.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.activities.QuoteListActivity;
import gurpreetsk.me.motivationdaily.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {

    @BindView(R.id.quote_fragment_textview)
    TextView TV_quote;

    String quote;


    public QuoteFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quote, container, false);
        ButterKnife.bind(this, v);
        quote = getArguments().getString(Constants.QUOTE_KEY);
        if (quote != null)
            quote = quote.replace("{Quote=", "").replace(".,", ".").replace("Author=", "\n\n-").replace("}", "");
        TV_quote.setText(quote);
        return v;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_quote_list, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
