package gurpreetsk.me.motivationdaily.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.utils.Constants;
import gurpreetsk.me.motivationdaily.utils.NetworkCheck;

public class SplashActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    ArrayList<String> authorNameList = new ArrayList<>();
    Boolean isFirstRun;
    SharedPreferences preferences;

    private static final String TAG = "SplashActivity";

//    String authors;

    @BindView(R.id.splash_textview)
    TextView TV_authorNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Log.i(TAG, "onCreate: Started at " + DateFormat.getTimeInstance().format(new Date()));

        // set first run shared
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(getString(R.string.isFirstRun), true);
        editor.commit();

        database = FirebaseDatabase.getInstance();
        try {
            database.setPersistenceEnabled(true);   //TODO: Crash here
        }catch (Exception e){
            FirebaseCrash.report(e);
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);

        //TODO: CHECK FOR INTERNET CONNECTIVITY ON FIRST RUN

    }

    @Override
    public void onStart() {
        super.onStart();
//        isFirstRun = preferences.getBoolean(getString(R.string.isFirstRun), true);
//        if (isFirstRun && !NetworkCheck.isNetworkConnected(this))
//            Snackbar.make(findViewById(R.id.activity_splash), getString(R.string.firstRunInternetNeeded), Snackbar.LENGTH_INDEFINITE);
//            Toast.makeText(this, getString(R.string.firstRunInternetNeeded), Toast.LENGTH_SHORT).show();
//        else {
            getAuthorsFromFirebase();
//            preferences.edit().putBoolean(getString(R.string.isFirstRun), false).apply();
//        }
    }

    void getAuthorsFromFirebase() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren())
                    authorNameList.add(child.getKey());

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
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: Ended at " + DateFormat.getTimeInstance().format(new Date()));
        finish();
    }
}
