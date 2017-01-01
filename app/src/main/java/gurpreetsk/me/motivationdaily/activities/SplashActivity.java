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
    DatabaseReference DailyQuotesDatabaseReference;
    DatabaseReference AuthorDatabaseReference;
    DatabaseReference TagsDatabaseReference;

    ArrayList<String> authorNameList = new ArrayList<>();
    ArrayList<String> dailyQuotesList = new ArrayList<>();
    ArrayList<String> tagsNameList = new ArrayList<>();

    private static final String TAG = "SplashActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Log.i(TAG, "onCreate: Started at " + DateFormat.getTimeInstance().format(new Date()));

        // set first run shared
//        preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putBoolean(getString(R.string.isFirstRun), true);
//        editor.commit();

        database = FirebaseDatabase.getInstance();
        try {
            database.setPersistenceEnabled(true);   //TODO: Crash here
        } catch (Exception e) {
            FirebaseCrash.report(e);
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        AuthorDatabaseReference = databaseReference.child("authors");
//        AuthorDatabaseReference.startAt("Abraham Lincoln");
//        AuthorDatabaseReference.endAt("Benjamin Franklin");
        AuthorDatabaseReference.keepSynced(true);
        DailyQuotesDatabaseReference = databaseReference.child("daily_quotes");
        DailyQuotesDatabaseReference.keepSynced(true);
        TagsDatabaseReference = databaseReference.child("tags");
        TagsDatabaseReference.keepSynced(true);

        //TODO: CHECK FOR INTERNET CONNECTIVITY ON FIRST RUN

    }

    @Override
    public void onStart() {
        super.onStart();
        getDailyQuotes();
        getTagsFromFirebase();
        getAuthorsFromFirebase();
    }

    private void getTagsFromFirebase() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    tagsNameList.add(child.getKey());
//                    Log.i(TAG, "onTagsDataChange: " + child.getKey());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onTagsCancelled: ", databaseError.toException());
            }
        };
        TagsDatabaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

    private void getDailyQuotes() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren())
                    dailyQuotesList.add(child.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onDailyQuotesCancelled: ", databaseError.toException());
            }
        };
        DailyQuotesDatabaseReference.addValueEventListener(valueEventListener);
    }

    void getAuthorsFromFirebase() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    authorNameList.add(child.getKey());
//                    Log.i(TAG, "onAuthorsDataChange: " + child.getKey());
                }
                Log.i(TAG, "onAuthorsDataChange: No of authors fetched " + dataSnapshot.getChildrenCount());

                Intent sendLists = new Intent(SplashActivity.this, GridActivity.class);
                sendLists.putStringArrayListExtra(Constants.AUTHORS_KEY, authorNameList);
                sendLists.putStringArrayListExtra(Constants.DAILY_QUOTES, dailyQuotesList);
                sendLists.putStringArrayListExtra(Constants.TAGS_CATEGORIES, tagsNameList);
                startActivity(sendLists);
                Log.i(TAG, "onAuthorsDataChange: Got author names");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onAuthorsCancelled: ", databaseError.toException());
            }
        };
        AuthorDatabaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: Ended at " + DateFormat.getTimeInstance().format(new Date()));
        finish();
    }
}
