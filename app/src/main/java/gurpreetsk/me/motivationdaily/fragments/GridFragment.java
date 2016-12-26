package gurpreetsk.me.motivationdaily.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.adapters.AuthorAdapter;
import gurpreetsk.me.motivationdaily.data.Database;
import gurpreetsk.me.motivationdaily.utils.Constants;
import gurpreetsk.me.motivationdaily.utils.NetworkCheck;


public class GridFragment extends Fragment {

    @BindView(R.id.main_grid_recycler_view)
    RecyclerView recyclerView;

    private static final String TAG = "GridFragment";
    ArrayList<String> authorNameList = new ArrayList<>();
    public static final int NO_OF_COLUMNS = 3;


    public GridFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_grid, container, false);
        authorNameList = getArguments().getStringArrayList(Constants.AUTHORS_KEY);
        ButterKnife.bind(this, v);

        if (!NetworkCheck.isNetworkConnected(getContext())) {

        }

        AuthorAdapter gridAdapter = new AuthorAdapter(getContext(), authorNameList);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), NO_OF_COLUMNS);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridAdapter);

        return v;
    }

}
