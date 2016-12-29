package gurpreetsk.me.motivationdaily.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.adapters.FavoritesAdapter;
import gurpreetsk.me.motivationdaily.utils.SimpleDividerItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesListFragment extends Fragment {

    @BindView(R.id.favorites_recyclerview)
    RecyclerView favoritesRecyclerView;


    public FavoritesListFragment() {}


    public interface FavoritesCallback {
        void OnFavoriteItemSelected(ArrayList<String> quotes, int position);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favorites_list, container, false);
        ButterKnife.bind(this, v);

        ArrayList<String> favorites = getArguments().getStringArrayList("ArrayList");
        FavoritesAdapter favoritesAdapter = new FavoritesAdapter(getContext(), favorites);
        favoritesRecyclerView.setAdapter(favoritesAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        favoritesRecyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

        return v;
    }

}
