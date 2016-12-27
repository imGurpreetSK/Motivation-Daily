package gurpreetsk.me.motivationdaily.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.activities.GridActivity;
import gurpreetsk.me.motivationdaily.activities.QuoteListActivity;
import gurpreetsk.me.motivationdaily.utils.AuthorImageUrl;
import gurpreetsk.me.motivationdaily.utils.Constants;

/**
 * Created by Gurpreet on 25/12/16.
 */

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.MyViewHolder> {

    private ArrayList<String> authorList = new ArrayList<>();
    private Context context;

    private ArrayList<String> authorQuotes = new ArrayList<>();
    private static final String TAG = "AuthorAdapter";

    public AuthorAdapter(Context context, ArrayList<String> authorList) {
        this.context = context;
        this.authorList = authorList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_element, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //TODO: Set image url
        holder.TV_authorName.setText(authorList.get(position));
        Glide.with(context)
                .load(AuthorImageUrl.getAuthorImage(authorList.get(position)))
                .into(holder.IV_authorImage);
//        InputStream is;
//        Bitmap myBitmap = null;
//        try {
//            is = context.getContentResolver().openInputStream(Uri.parse(AuthorImageUrl.getAuthorImage(authorList.get(position))));
//            myBitmap = BitmapFactory.decodeStream(is);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        int color = getColorPalette(myBitmap);
//        holder.TV_authorName.setBackgroundResource(color);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getQuotesFromFirebase(authorList.get(holder.getAdapterPosition()));
            }
        });
    }

//    private int getColorPalette(Bitmap myBitmap) {
//        if (myBitmap != null && !myBitmap.isRecycled())
//            return Palette.from(myBitmap).generate().getLightVibrantColor(context.getResources().getColor(R.color.colorAccent));
//        return context.getResources().getColor(R.color.colorAccent);
//    }

//    @Override
//    public int getItemViewType(int position) {
//        super.getItemViewType(position);
//        //TODO: make first card big, denote it with quote of the day
//    }

    private void getQuotesFromFirebase(final String authorName) {
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child(authorName);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (authorQuotes != null)
                    authorQuotes.clear();
                for (DataSnapshot child : dataSnapshot.getChildren())
                    authorQuotes.add(child.child("Quote").getValue().toString());

                Intent sendAuthorsList = new Intent(context, QuoteListActivity.class);
                sendAuthorsList.putStringArrayListExtra(Constants.QUOTES_KEY, authorQuotes);
                sendAuthorsList.putExtra(Constants.AUTHOR_NAME_KEY, authorName);
                context.startActivity(sendAuthorsList);
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
        return authorList.size();
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
