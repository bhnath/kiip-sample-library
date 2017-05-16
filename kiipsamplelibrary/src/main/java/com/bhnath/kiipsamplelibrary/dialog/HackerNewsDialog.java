package com.bhnath.kiipsamplelibrary.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bhnath.kiipsamplelibrary.R;
import com.bhnath.kiipsamplelibrary.adapter.HackerNewsListAdapter;
import com.bhnath.kiipsamplelibrary.model.HackerNewsItem;

import java.util.List;

public class HackerNewsDialog extends DialogFragment {
    private HackerNewsListAdapter adapter;
    private List<HackerNewsItem> list;
    private ListView view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View hackerNewsView =
                getActivity().getLayoutInflater().inflate(R.layout.hacker_news_view, null);
        view = (ListView) hackerNewsView.findViewById(R.id.hackerNewsListView);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = list.get(position).getUrl();
                if (url == null) {
                    Toast.makeText(getActivity(), "This story does not have a URL", Toast.LENGTH_SHORT).show();
                } else {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });

        adapter = new HackerNewsListAdapter(getActivity(), list);
        view.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(hackerNewsView);
        return builder.create();
    }

    public void setHackerNewsList(List<HackerNewsItem> list) {
        this.list = list;
    }
}
