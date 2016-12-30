package gurpreetsk.me.motivationdaily.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.activities.QuoteViewActivity;
import gurpreetsk.me.motivationdaily.data.QuotesTable;
import gurpreetsk.me.motivationdaily.data.TableStructure;
import gurpreetsk.me.motivationdaily.fragments.FavoritesListFragment;
import gurpreetsk.me.motivationdaily.fragments.QuoteListFragment;
import gurpreetsk.me.motivationdaily.utils.Constants;

/**
 * Created by Gurpreet on 28/12/16.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {

    private ArrayList<String> favorites;
    private Context context;


    public FavoritesAdapter(Context context, ArrayList<String> favorites) {
        this.favorites = favorites;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_element, parent, false);
        return new FavoritesAdapter.MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.TV_quote.setText(favorites.get(position));
        holder.LL_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FavoritesListFragment.FavoritesCallback)context).OnFavoriteItemSelected(favorites, holder.getAdapterPosition());
            }
        });
        holder.Bookmark_quote.setLiked(true);
        holder.Bookmark_quote.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                context.getContentResolver().delete(QuotesTable.CONTENT_URI, TableStructure.COLUMN_QUOTE + " = ?", new String[]{favorites.get(holder.getAdapterPosition())});
                likeButton.setLiked(false);
//                notifyDataSetChanged();
//                Toast.makeText(context, "Inserted quote: " + favorites.get(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return favorites.size();
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
