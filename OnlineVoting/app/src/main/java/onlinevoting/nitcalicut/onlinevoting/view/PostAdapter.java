package onlinevoting.nitcalicut.onlinevoting.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import onlinevoting.nitcalicut.onlinevoting.R;
import onlinevoting.nitcalicut.onlinevoting.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder>{

    private Context context;
    private List<Post> postsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView post;
        public TextView dot;
        public TextView timestamp;

        public MyViewHolder(View view) {
            super(view);
            post = view.findViewById(R.id.post);
            dot = view.findViewById(R.id.dot);
            timestamp = view.findViewById(R.id.timestamp);
        }
    }

    public PostAdapter(Context context, List<Post> postsList) {
        this.context = context;
        this.postsList = postsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_postlistview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post post = postsList.get(position);
        holder.post.setText(post.getPost_name());
        holder.dot.setText(Html.fromHtml("&#8226;"));
        holder.timestamp.setText(post.getDate());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    /*private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }*/
}
