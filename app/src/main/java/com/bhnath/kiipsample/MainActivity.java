package com.bhnath.kiipsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bhnath.kiipsamplelibrary.HackerNews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final HackerNews hackerNews = new HackerNews();
        hackerNews.initSDK(MainActivity.this);

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hackerNews.showHackerNews();
            }
        });
    }
}
