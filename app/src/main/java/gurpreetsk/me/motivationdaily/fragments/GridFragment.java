package gurpreetsk.me.motivationdaily.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.adapters.AuthorAdapter;
import gurpreetsk.me.motivationdaily.utils.Constants;


public class GridFragment extends Fragment {

    @BindView(R.id.main_grid_recycler_view)
    RecyclerView recyclerView;

    private static final String TAG = "GridFragment";
    ArrayList<String> authorNameList;
    RecyclerView.LayoutManager gridLayoutManager;
    public static final int PORTRAIT_NO_OF_COLUMNS = 3;
    public static final int LANDSCAPE_NO_OF_COLUMNS = 4;


    public GridFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_grid, container, false);
        authorNameList = getArguments().getStringArrayList(Constants.AUTHORS_KEY);
        ButterKnife.bind(this, v);

        AuthorAdapter gridAdapter = new AuthorAdapter(getContext(), authorNameList);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            gridLayoutManager = new GridLayoutManager(getContext(), PORTRAIT_NO_OF_COLUMNS);
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            gridLayoutManager = new GridLayoutManager(getContext(), LANDSCAPE_NO_OF_COLUMNS);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridAdapter);

        return v;
    }

}
