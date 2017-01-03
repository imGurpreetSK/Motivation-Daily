package gurpreetsk.me.motivationdaily.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.data.QuotesTable;
import gurpreetsk.me.motivationdaily.data.TableStructure;
import gurpreetsk.me.motivationdaily.fragments.FavoritesListFragment;

/**
 * Created by Gurpreet on 28/12/16.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {

    private ArrayList<String> favorites;
    private Context context;
    private Cursor cursor;

    private static final String TAG = "FavoritesAdapter";


    public FavoritesAdapter(Context context, ArrayList<String> favorites, Cursor cursor) {
        this.favorites = favorites;
        this.context = context;
        this.cursor = cursor;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_element, parent, false);
        return new FavoritesAdapter.MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        String quote = favorites.get(position);
        quote = quote.replace("{Quote=", "").replace(".,", ".").replace("Author=", "\n-").replace("}", "");
        holder.TV_quote.setText(quote);
        holder.LL_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FavoritesListFragment.FavoritesCallback) context).OnFavoriteItemSelected(favorites, holder.getAdapterPosition());
            }
        });
        holder.Bookmark_quote.setLiked(true);
        holder.Bookmark_quote.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
            }

            @Override
            public void unLiked(LikeButton likeButton) {
//                Toast.makeText(context, cursor.getString(cursor.getColumnIndex(QuotesTable.FIELD_QUOTE)), Toast.LENGTH_SHORT).show();
                context.getContentResolver().delete(QuotesTable.CONTENT_URI,
                        TableStructure.COLUMN_QUOTE + " = ?",
                        new String[]{favorites.get(holder.getAdapterPosition())});  // TODO: set this
                likeButton.setLiked(false);
            }
        });
    }


    public Cursor swapCursor(Cursor cursor) {
        if (this.cursor == cursor) {
            return null;
        }
        Cursor newCursor = this.cursor;
        this.cursor = cursor;
        if (cursor != null)
            this.notifyDataSetChanged();
        return newCursor;
    }


    @Override
    public int getItemCount() {
        return (cursor == null) ? 0 : cursor.getCount();
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
