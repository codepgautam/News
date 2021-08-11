package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    ArrayList<ArticlesList> my_list;

    public CustomAdapter(Context context, ArrayList<ArticlesList> my_list) {
        this.my_list = my_list;
        this.context = context;
    }

//    public void setList(ArrayList<ArticlesList> my_list) {
//        this.my_list = my_list;
//        notifyDataSetChanged();
//    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CustomAdapter.ViewHolder holder, int position) {
        final ArticlesList temp = my_list.get(position);

        holder.title.setText(my_list.get(position).getTitle());
        holder.source.setText(my_list.get(position).getSource().getName());
        holder.description.setText(my_list.get(position).getDescription());
        holder.time.setText(convertTime(my_list.get(position).getTimimg()));
        Glide.with(context).load(my_list.get(position).getImage_url()).into(holder.image);

        holder.title.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setData(Uri.parse(my_list.get(position).getUrl()));
            context.startActivity(i);
        });
    }

    private String convertTime(String timing) {
        timing = timing.substring(0,9) + " || " + timing.substring(11,19);
        return timing;
    }


    @Override
    public int getItemCount() {
        return my_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView source, title, description, time;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.news_image);
            source = view.findViewById(R.id.source);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            time = view.findViewById(R.id.time);
        }

    }
}
