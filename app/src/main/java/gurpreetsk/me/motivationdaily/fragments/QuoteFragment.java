package gurpreetsk.me.motivationdaily.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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


    public QuoteFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quote, container, false);
        ButterKnife.bind(this, v);
        String quote = getArguments().getString(Constants.QUOTE_KEY);
        if (quote != null)
            quote = quote.replace("{Quote=", "").replace(".,", ".").replace("Author=", "\n\n-").replace("}", "");
        TV_quote.setText(quote);
        return v;

    }

}
