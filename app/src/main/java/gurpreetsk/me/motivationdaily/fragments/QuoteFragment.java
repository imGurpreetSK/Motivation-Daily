package gurpreetsk.me.motivationdaily.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.activities.QuoteListActivity;
import gurpreetsk.me.motivationdaily.data.Database;
import gurpreetsk.me.motivationdaily.data.QuotesTable;
import gurpreetsk.me.motivationdaily.data.TableStructure;
import gurpreetsk.me.motivationdaily.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {

    @BindView(R.id.quote_fragment_textview)
    TextView TV_quote;
    @BindView(R.id.share_action)
    FloatingActionButton fab;

    String quote;
    private static final String TAG = "QuoteFragment";


    public QuoteFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quote, container, false);
        ButterKnife.bind(this, v);
        quote = getArguments().getString(Constants.QUOTE_KEY);
        if (quote != null)
            quote = quote.replace("{Quote=", "").replace(".,", ".").replace("Author=", "\n\n-").replace("}", "");
        TV_quote.setText(quote);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, quote + "\nSee more on Motivation Daily \n\"LINK\"");
                startActivity(Intent.createChooser(intent, getString(R.string.share_via)));
            }
        });
        final ArrayList<String> favList = queryFavourites();
        if(favList.contains(quote))
            TV_quote.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));

        TV_quote.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Database databaseInstance = new Database();
//                databaseInstance.authorName = author;
                databaseInstance.quote = quote;
                try {
                    if (!favList.contains(quote)) {
                        getContext().getContentResolver().insert(QuotesTable.CONTENT_URI, QuotesTable.getContentValues(databaseInstance, false));
                        TV_quote.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                        favList.add(quote);
                        Toast.makeText(getContext(), "Added to favorites!", Toast.LENGTH_SHORT).show();
                    } else{
                        getContext().getContentResolver().delete(QuotesTable.CONTENT_URI, TableStructure.COLUMN_QUOTE + " = ?", new String[]{quote});
                        TV_quote.setTextColor(getContext().getResources().getColor(R.color.secondaryText));
                        favList.remove(quote);
                        Toast.makeText(getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    FirebaseCrash.log("Couldn't insert in database");
                }
                return true;
            }
        });
        return v;

    }


    private ArrayList<String> queryFavourites() {
        Cursor c = getContext().getContentResolver().query(QuotesTable.CONTENT_URI, null, null, null, null);
        List<Database> list = QuotesTable.getRows(c, true);     // all rows of database
        ArrayList<String> idList = new ArrayList<>();
        for (Database element : list) {
            idList.add(element.quote);      // add all quotes in arraylist and return
        }
        return idList;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_quote_list, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
