package gurpreetsk.me.motivationdaily.fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.adapters.FavoritesAdapter;
import gurpreetsk.me.motivationdaily.data.QuotesTable;
import gurpreetsk.me.motivationdaily.utils.SimpleDividerItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.favorites_recyclerview)
    RecyclerView favoritesRecyclerView;
    @BindView(R.id.no_data_textview)
    TextView TV_noData;

    FavoritesAdapter favoritesAdapter;
    public static final String[] projections = {
            QuotesTable.FIELD_QUOTE
    };

    final static int LOADER_ID = 1;


    public FavoritesListFragment() {
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), QuotesTable.CONTENT_URI, null, null, null, null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        try {
            favoritesAdapter.swapCursor(data);
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrash.log(e.toString());
        }
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        try {
            favoritesAdapter.swapCursor(null);
        } catch (Exception e) {
            e.printStackTrace();
            FirebaseCrash.log(e.toString());
        }
    }


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

        if (favorites != null && !favorites.isEmpty()) {
            favoritesAdapter = new FavoritesAdapter(getContext(), favorites, null);
            favoritesRecyclerView.setAdapter(favoritesAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            favoritesRecyclerView.setLayoutManager(layoutManager);
            //        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
            TV_noData.setVisibility(View.GONE);
        } else {
            TV_noData.setVisibility(View.VISIBLE);
            favoritesRecyclerView.setVisibility(View.GONE);
        }

        return v;
    }

}
