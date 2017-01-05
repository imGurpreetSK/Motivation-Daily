package gurpreetsk.me.motivationdaily.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.activities.QuoteListActivity;
import gurpreetsk.me.motivationdaily.adapters.QuoteAdapter;
import gurpreetsk.me.motivationdaily.utils.Constants;
import gurpreetsk.me.motivationdaily.utils.SimpleDividerItemDecoration;


public class QuoteListFragment extends Fragment {

    @BindView(R.id.quote_list_recycler_view)
    RecyclerView recyclerView;

    ArrayList<String> quotes = new ArrayList<>();
    String authorName;
    private static final String TAG = "QuoteListFragment";


    public QuoteListFragment() {}


    public interface Callback {
        void onItemSelected(ArrayList<String> quotes, String authorName, int position);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quote_list, container, false);
        ButterKnife.bind(this, v);

        quotes = getArguments().getStringArrayList(Constants.QUOTES_KEY);
        authorName = getArguments().getString(Constants.AUTHOR_NAME_KEY);
        int color = getArguments().getInt(Constants.MUTED_COLOR);

        QuoteAdapter quoteAdapter = new QuoteAdapter(getContext(), quotes, authorName);
        recyclerView.setAdapter(quoteAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

        return v;
    }


}
