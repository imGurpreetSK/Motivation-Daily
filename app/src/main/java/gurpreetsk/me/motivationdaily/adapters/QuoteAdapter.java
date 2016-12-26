package gurpreetsk.me.motivationdaily.adapters;

import android.content.Context;
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

/**
 * Created by Gurpreet on 26/12/16.
 */

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.MyViewHolder> {

    private ArrayList<String> quotes;
    Context context;


    public QuoteAdapter(Context context, ArrayList<String> quotes) {
        this.quotes = quotes;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_element, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.TV_quote.setText(quotes.get(position));
        //TODO: save in DB and create view for viewing bookmarked quotes
        holder.Bookmark_quote.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                likeButton.setLiked(true);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                likeButton.setLiked(false);
            }
        });
        holder.LL_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked: " + quotes.get(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.quote_list_text_view)
        TextView TV_quote;
        @BindView(R.id.quote_list_bookmark_button)
        LikeButton Bookmark_quote;
        @BindView(R.id.quote_list_linear_layout)
        LinearLayout LL_quote;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
