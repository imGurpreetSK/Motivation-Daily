package gurpreetsk.me.motivationdaily.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.data.Database;
import gurpreetsk.me.motivationdaily.data.QuotesTable;
import gurpreetsk.me.motivationdaily.data.TableStructure;
import gurpreetsk.me.motivationdaily.fragments.QuoteListFragment;

/**
 * Created by Gurpreet on 26/12/16.
 */

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.MyViewHolder> {

    private ArrayList<String> quotes;
    private Context context;
    private String author;

    private static final String TAG = "QuoteAdapter";


    public QuoteAdapter(Context context, ArrayList<String> quotes, String author) {
        this.quotes = quotes;
        this.context = context;
        this.author = author;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_element, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        String quote = quotes.get(position);

        quote = quote.replace("{Quote=", "").replace(".,", ".").replace("Author=", "\n-").replace("}", "");

        holder.TV_quote.setText(quote);
        //TODO: save in DB and create view for viewing bookmarked quotes
        ArrayList<String> idList = queryFavourites();
        if (idList.contains(quotes.get(holder.getAdapterPosition())))
            holder.Bookmark_quote.setLiked(true);

        holder.Bookmark_quote.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Database databaseInstance = new Database();
                databaseInstance.authorName = author;
                databaseInstance.quote = quotes.get(holder.getAdapterPosition());
                try {
                    context.getContentResolver().insert(QuotesTable.CONTENT_URI, QuotesTable.getContentValues(databaseInstance, false));
                    likeButton.setLiked(true);
                } catch (Exception e) {
                    FirebaseCrash.log("Couldn't insert in database");
                }
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                try {
                    context.getContentResolver().delete(QuotesTable.CONTENT_URI, TableStructure.COLUMN_QUOTE + " = ?", new String[]{quotes.get(holder.getAdapterPosition())});
                    likeButton.setLiked(false);
                } catch (Exception e) {
                    Log.e(TAG, "unLiked: ", e);
                    FirebaseCrash.log("Couldn't delete from database");
                }
            }
        });
        holder.LL_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Handles data transfer on different devices
                ((QuoteListFragment.Callback) context).OnItemSelected(quotes, author, holder.getAdapterPosition());
            }
        });
    }

    private ArrayList<String> queryFavourites() {
        Cursor c = context.getContentResolver().query(QuotesTable.CONTENT_URI, null, null, null, null);
        List<Database> list = QuotesTable.getRows(c, true);     // all rows of database
        ArrayList<String> idList = new ArrayList<>();
        for (Database element : list) {
            idList.add(element.quote);      // add all quotes in arraylist and return
        }
        return idList;
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.quote_list_text_view)
        TextView TV_quote;
        @BindView(R.id.quote_list_bookmark_button)
        LikeButton Bookmark_quote;
        @BindView(R.id.quote_list_linear_layout)
        LinearLayout LL_quote;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
