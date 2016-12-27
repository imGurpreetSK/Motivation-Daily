package gurpreetsk.me.motivationdaily.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.fragments.GridFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;

public class GridActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ArrayList<String> authorsList = getIntent().getStringArrayListExtra(Constants.AUTHORS_KEY);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Constants.AUTHORS_KEY, authorsList);
        GridFragment gridFragment = new GridFragment();
        gridFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_grid_fragment_container, gridFragment).commit();
    }

}
