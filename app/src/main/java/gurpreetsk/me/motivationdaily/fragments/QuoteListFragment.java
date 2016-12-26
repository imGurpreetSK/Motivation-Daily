package gurpreetsk.me.motivationdaily.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.adapters.QuoteAdapter;
import gurpreetsk.me.motivationdaily.utils.Constants;
import gurpreetsk.me.motivationdaily.utils.SimpleDividerItemDecoration;


public class QuoteListFragment extends Fragment {

    @BindView(R.id.quote_list_recycler_view)
    RecyclerView recyclerView;

    ArrayList<String> quotes = new ArrayList<>();
    private static final String TAG = "QuoteListFragment";


    public QuoteListFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quote_list, container, false);
        ButterKnife.bind(this, v);

        quotes = getArguments().getStringArrayList(Constants.QUOTES_KEY);
        if (quotes != null) {
            Log.i(TAG, "onCreateView: Got " + quotes.size() + " quotes.");
            Log.i(TAG, "onCreateView: First quote is " + quotes.get(0));
        } else
            Log.e(TAG, "onCreateView: QuotesArrayList is null.");

        QuoteAdapter quoteAdapter = new QuoteAdapter(getContext(), quotes);
        recyclerView.setAdapter(quoteAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

        return v;
    }


}
