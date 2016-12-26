package gurpreetsk.me.motivationdaily.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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
import gurpreetsk.me.motivationdaily.fragments.GridFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;
import gurpreetsk.me.motivationdaily.utils.NetworkCheck;

import static java.security.AccessController.getContext;

public class SplashActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    ArrayList<String> authorNameList = new ArrayList<>();

    private static final String TAG = "SplashActivity";

//    String authors;

    @BindView(R.id.splash_textview)
    TextView TV_authorNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onStart() {
        super.onStart();
        if (NetworkCheck.isNetworkConnected(this))
            getAuthorsFromFirebase();     //TODO: 1
        else
            Toast.makeText(this, R.string.no_network, Toast.LENGTH_SHORT).show();
    }

    private void getAuthorsFromFirebase() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    authorNameList.add(child.getKey());
//                    Log.i(TAG, "getAuthorsFromFirebase: added " + child.getKey());
                }
//                for (String author : authorNameList) {
//                    StringBuilder sb = new StringBuilder();
//                    sb.append(author);
//                    authors += sb.toString() + "\n";
//                }
//                TV_authorNames.setText(authors);
                Intent sendAuthorsList = new Intent(SplashActivity.this, GridActivity.class);
                sendAuthorsList.putStringArrayListExtra(Constants.AUTHORS_KEY, authorNameList);
                startActivity(sendAuthorsList);
                Log.i(TAG, "onDataChange: Got author names, Started GridActivity");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: ", databaseError.toException());
            }
        };
        mDatabase.addValueEventListener(valueEventListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
