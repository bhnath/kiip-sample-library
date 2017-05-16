package com.bhnath.kiipsamplelibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bhnath.kiipsamplelibrary.R;
import com.bhnath.kiipsamplelibrary.model.HackerNewsItem;

import java.util.List;

public class HackerNewsListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<HackerNewsItem> list;

    public HackerNewsListAdapter(Context context, List<HackerNewsItem> list) {
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.hacker_news_item, parent, false);

        TextView title = (TextView) view.findViewById(R.id.titleTextView);
        TextView author = (TextView) view.findViewById(R.id.authorTextView);
        TextView score = (TextView) view.findViewById(R.id.scoreTextView);
        TextView time = (TextView) view.findViewById(R.id.timeTextView);
        TextView comments = (TextView) view.findViewById(R.id.commentsTextView);

        HackerNewsItem item = (HackerNewsItem) getItem(position);

        title.setText(item.getTitle());
        author.setText(item.getBy());
        score.setText(item.getScore().toString());

        Long currentTime = System.currentTimeMillis() / 1000;
        Long diffTime = Math.abs(currentTime - item.getTime()) / 3600;

        time.setText(diffTime.toString());
        comments.setText(String.format("%1d", item.getDescendants()));

        return view;
    }
}
