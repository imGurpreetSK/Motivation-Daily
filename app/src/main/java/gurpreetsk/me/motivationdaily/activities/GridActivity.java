package gurpreetsk.me.motivationdaily.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.fragments.GridFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;

public class GridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        ArrayList<String> authorsList = getIntent().getStringArrayListExtra(Constants.AUTHORS_KEY);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Constants.AUTHORS_KEY, authorsList);
        GridFragment gridFragment = new GridFragment();
        gridFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_grid_fragment_container ,gridFragment).commit();

    }
}
