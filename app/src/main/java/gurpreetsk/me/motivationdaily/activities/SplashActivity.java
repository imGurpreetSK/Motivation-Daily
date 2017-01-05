package gurpreetsk.me.motivationdaily.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
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
    PagerAdapter pagerAdapter;

    ArrayList<String> authorNameList = new ArrayList<>();
    ArrayList<String> dailyQuotesList = new ArrayList<>();
    ArrayList<String> tagsNameList = new ArrayList<>();

    private static final String TAG = "SplashActivity";

    SharedPreferences preferences = null;
    public static final String FirstRun = "FirstRun";

    @BindView(R.id.data_loading_textview)
    TextView TV_data_loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        preferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        if (preferences.getBoolean(FirstRun, true)) {
            if (NetworkCheck.isNetworkConnected(this)) {
                Log.i(TAG, "onCreate: Started at " + DateFormat.getTimeInstance().format(new Date()));
                TV_data_loading.setVisibility(View.VISIBLE);
                database = FirebaseDatabase.getInstance();
                try {
                    database.setPersistenceEnabled(true);   //TODO: Crash here
                } catch (Exception e) {
                    FirebaseCrash.report(e);
                }
                databaseReference = FirebaseDatabase.getInstance().getReference();
                AuthorDatabaseReference = databaseReference.child("authors");
                AuthorDatabaseReference.keepSynced(true);
                DailyQuotesDatabaseReference = databaseReference.child("daily_quotes");
                DailyQuotesDatabaseReference.keepSynced(true);
                TagsDatabaseReference = databaseReference.child("tags");
                TagsDatabaseReference.keepSynced(true);
                new FetchAsyncTask().execute();
            } else {
                if (!preferences.getBoolean(FirstRun, false))
                    TV_data_loading.setVisibility(View.GONE);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.internet_needed))
                        .setMessage(getString(R.string.firstRunInternetNeeded))
                        .setNeutralButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                preferences.edit().putBoolean(FirstRun, false).apply();
                                dialog.dismiss();
                                onPause();
                            }
                        })
                        .setIcon(android.R.drawable.stat_notify_error);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        } else {
            Log.i(TAG, "onCreate: Started at " + DateFormat.getTimeInstance().format(new Date()));
            if (!preferences.getBoolean(FirstRun, false))
                TV_data_loading.setVisibility(View.GONE);
            database = FirebaseDatabase.getInstance();
            try {
                database.setPersistenceEnabled(true);   //TODO: Crash here
            } catch (Exception e) {
                FirebaseCrash.report(e);
            }
            databaseReference = FirebaseDatabase.getInstance().getReference();
            AuthorDatabaseReference = databaseReference.child("authors");
            AuthorDatabaseReference.keepSynced(true);
            DailyQuotesDatabaseReference = databaseReference.child("daily_quotes");
            DailyQuotesDatabaseReference.keepSynced(true);
            TagsDatabaseReference = databaseReference.child("tags");
            TagsDatabaseReference.keepSynced(true);
            getDailyQuotes();
            getTagsFromFirebase();
            getAuthorsFromFirebase();
        }

    }


    @Override
    public void onStart() {
        super.onStart();
    }


    private void getTagsFromFirebase() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren())
                    tagsNameList.add(child.getKey());
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
                for (DataSnapshot child : dataSnapshot.getChildren())
                    authorNameList.add(child.getKey());

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


    private class FetchAsyncTask extends AsyncTask <Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            getDailyQuotes();
            getTagsFromFirebase();
            getAuthorsFromFirebase();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            preferences.edit().putBoolean(FirstRun, false).apply();
        }

    }


}
