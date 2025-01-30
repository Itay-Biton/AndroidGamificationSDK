package com.gamificationlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gamificationlib.models.Player;


public class PlayerRankView extends LinearLayout {
    private TextView tvError;
    private ProgressBar loadingIndicator;
    private ItemPlayerView itemPlayerView;

    // Constructor for programmatically creating the view
    public PlayerRankView(Context context) {
        super(context);
        init(context, null);
    }

    // Constructor for inflating the view from XML
    public PlayerRankView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    // Constructor for inflating the view with a style
    public PlayerRankView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.player_rank, this, true);
        // Handle custom attributes if necessary
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemPlayerView);
            // Retrieve and apply custom attributes
            a.recycle();
        }
        tvError = findViewById(R.id.tv_error);
        loadingIndicator = findViewById(R.id.loading_indicator);
        itemPlayerView = findViewById(R.id.item_player_view);
    }

    public void fetchPlayerRank(String playerID) {
        loadingIndicator.setVisibility(View.VISIBLE);
        itemPlayerView.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);

        ApiController.fetchPlayerRank(playerID, new ApiController.CallBack_Integer() {
            @Override
            public void ready(Integer result) {
                ApiController.fetchPlayerById(playerID, new ApiController.CallBack_Player() {
                    @Override
                    public void ready(Player player) {
                        loadingIndicator.setVisibility(View.GONE);
                        itemPlayerView.setVisibility(View.VISIBLE);
                        itemPlayerView.setRank(result);
                        itemPlayerView.setUsername(player.getUsername());
                        itemPlayerView.setPoints(player.getPlayerPoints());
                    }

                    @Override
                    public void failed(String message) {
                        displayError(message);
                    }
                });
            }

            @Override
            public void failed(String message) {
                displayError(message);
            }
        });
    }

    private void displayError(String message) {
        tvError.setText(message);
        tvError.setVisibility(VISIBLE);
        itemPlayerView.setVisibility(View.GONE);
    }

    public ItemPlayerView getItemPlayerView() {
        return itemPlayerView;
    }
}