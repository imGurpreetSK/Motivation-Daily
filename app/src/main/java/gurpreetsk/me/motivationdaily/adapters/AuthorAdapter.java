package gurpreetsk.me.motivationdaily.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;

/**
 * Created by Gurpreet on 25/12/16.
 */

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.MyViewHolder> {

    ArrayList<String> authorList = new ArrayList<>();
    Context context;

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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //TODO: 2
    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.grid_element_image_view)
        ImageView IV_authorImage;
        @BindView(R.id.grid_element_text_view)
        TextView TV_authorName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
