package gurpreetsk.me.motivationdaily.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.activities.AuthorsActivity;
import gurpreetsk.me.motivationdaily.activities.GridActivity;
import gurpreetsk.me.motivationdaily.activities.QuoteListActivity;
import gurpreetsk.me.motivationdaily.models.TagsQuotes;
import gurpreetsk.me.motivationdaily.utils.AuthorImageUrl;
import gurpreetsk.me.motivationdaily.utils.Constants;

/**
 * Created by Gurpreet on 30/12/16.
 */

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> tagsList;
    private int color;
    private static final String TAG = "TagsAdapter";
    private ArrayList<TagsQuotes> tagQuotes = new ArrayList<>();


    public TagsAdapter(Context context, ArrayList<String> tagsList) {
        this.context = context;
        this.tagsList = tagsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_element, parent, false);
        return new TagsAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.TV_authorName.setText(tagsList.get(position));

        Glide.with(context)
                .load(AuthorImageUrl.getAuthorImage(tagsList.get(position)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        Bitmap bitmap = ((GlideBitmapDrawable) resource.getCurrent()).getBitmap();
                        Palette palette = Palette.generate(bitmap);
                        int defaultColor = 0xFF333333;
                        color = palette.getMutedColor(defaultColor);
                        holder.TV_authorName.setBackgroundColor(color);
                        return false;
                    }
                })
                .into(holder.IV_authorImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTagsFromFirebase(tagsList.get(holder.getAdapterPosition()), holder);
            }
        });
    }

    private void getTagsFromFirebase(String tagName, final MyViewHolder holder) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("tags").child(tagName);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (tagQuotes != null)
                    tagQuotes.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    TagsQuotes quotes = dataSnapshot.getValue(TagsQuotes.class);
                    Log.i(TAG, "onDataChange: quote "+quotes.quote);
                    tagQuotes.add(quotes);
                }

                Intent sendTagsList = new Intent(context, QuoteListActivity.class);
                sendTagsList.putExtra(Constants.QUOTES_KEY, tagQuotes);
                sendTagsList.putExtra(Constants.MUTED_COLOR, color);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (!QuoteListActivity.mTwoPane) {
                        ActivityOptionsCompat options;
                        //TODO: change this
                        try {
                            options = ActivityOptionsCompat.
                                    makeSceneTransitionAnimation((GridActivity) context, holder.IV_authorImage, context.getString(R.string.authorimage_transition));
                        }catch (Exception e){
                            e.printStackTrace();
                            options = ActivityOptionsCompat.
                                    makeSceneTransitionAnimation((AuthorsActivity) context, holder.IV_authorImage, context.getString(R.string.authorimage_transition));
                        }
                        context.startActivity(sendTagsList, options.toBundle());
                    } else
                        context.startActivity(sendTagsList);
                } else
                    context.startActivity(sendTagsList);
                Log.i(TAG, "onDataChange: Got author quotes, Started QuoteListActivity");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: ", databaseError.toException());
            }
        };
        databaseReference.addValueEventListener(valueEventListener);

    }

    @Override
    public int getItemCount() {
        return tagsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.grid_element_image_view)
        ImageView IV_authorImage;
        @BindView(R.id.grid_element_text_view)
        TextView TV_authorName;
        @BindView(R.id.author_card_view)
        CardView cardView;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
