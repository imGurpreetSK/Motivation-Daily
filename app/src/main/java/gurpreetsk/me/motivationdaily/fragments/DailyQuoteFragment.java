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
import gurpreetsk.me.motivationdaily.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyQuoteFragment extends Fragment {

    @BindView(R.id.daily_quote)
    TextView TV_DailyQuote;


    public DailyQuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_daily_quote, container, false);
        ButterKnife.bind(this, v);
        String quote = getArguments().getString(Constants.QUOTE_KEY);
        TV_DailyQuote.setText(quote);
        return v;
    }

}
