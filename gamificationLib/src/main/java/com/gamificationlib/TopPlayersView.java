package com.gamificationlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gamificationlib.models.Player;

import java.util.List;

public class TopPlayersView extends LinearLayout {

    private RecyclerView recyclerView;
    private TextView tvError;
    private PlayerAdapter adapter;

    // Constructor for programmatically creating the view
    public TopPlayersView(Context context) {
        super(context);
        init(context, null);
    }

    // Constructor for inflating the view from XML
    public TopPlayersView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    // Constructor for inflating the view with a style
    public TopPlayersView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.top_players_view, this, true);
        // Handle custom attributes if necessary
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemPlayerView);
            // Retrieve and apply custom attributes
            a.recycle();
        }
        recyclerView = findViewById(R.id.recyclerViewTopPlayers);
        tvError = findViewById(R.id.tv_error);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public void fetchTopPlayers(int limit) {
        ApiController.fetchTopPlayers(limit, new ApiController.CallBack_Players() {
            @Override
            public void ready(List<Player> players) {
                if (players.isEmpty()) {
                    showError("No players found.");
                } else {
                    showPlayers(players);
                }
            }

            @Override
            public void failed(String message) {
                showError("Failed to load players.");
            }
        });
    }

    private void showPlayers(List<Player> players) {
        tvError.setVisibility(GONE);
        recyclerView.setVisibility(VISIBLE);
        adapter = new PlayerAdapter(players);
        recyclerView.setAdapter(adapter);
    }

    private void showError(String message) {
        tvError.setText(message);
        tvError.setVisibility(VISIBLE);
        recyclerView.setVisibility(GONE);
    }
}