package gurpreetsk.me.motivationdaily.fragments;

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


public class GridFragment extends Fragment {

    @BindView(R.id.main_grid_recycler_view)
    RecyclerView recyclerView;

    ArrayList<String> authorNameList = new ArrayList<>();
    public static final int NO_OF_COLUMNS= 2;

    public GridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_grid, container, false);
        ButterKnife.bind(this, v);

        AuthorAdapter gridAdapter = new AuthorAdapter(getContext(), authorNameList);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), NO_OF_COLUMNS);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridAdapter);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
//        getAuthorsFromFirebase();
    }
}
