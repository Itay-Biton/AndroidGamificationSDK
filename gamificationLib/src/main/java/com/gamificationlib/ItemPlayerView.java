package com.gamificationlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemPlayerView extends LinearLayout {

    private TextView tvRank, tvUsername, tvPoints;

    public ItemPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // Inflate the layout
        LayoutInflater.from(context).inflate(R.layout.item_top_player, this, true);

        // Get references to views
        tvRank = findViewById(R.id.tv_rank);
        tvUsername = findViewById(R.id.tv_username);
        tvPoints = findViewById(R.id.tv_points);

        // Read custom attributes
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemPlayerView);

        // Apply background colors
        setBackgroundColor(tvRank, a, R.styleable.ItemPlayerView_rankBackground);
        setBackgroundColor(tvUsername, a, R.styleable.ItemPlayerView_usernameBackground);
        setBackgroundColor(tvPoints, a, R.styleable.ItemPlayerView_pointsBackground);

        // Apply text colors
        setTextColor(tvRank, a, R.styleable.ItemPlayerView_rankTextColor);
        setTextColor(tvUsername, a, R.styleable.ItemPlayerView_usernameTextColor);
        setTextColor(tvPoints, a, R.styleable.ItemPlayerView_pointsTextColor);

        // Apply text sizes
        setTextSize(tvRank, a, R.styleable.ItemPlayerView_rankTextSize);
        setTextSize(tvUsername, a, R.styleable.ItemPlayerView_usernameTextSize);
        setTextSize(tvPoints, a, R.styleable.ItemPlayerView_pointsTextSize);

        a.recycle(); // Free resources
    }

    // Helper methods for applying attributes
    private void setBackgroundColor(View view, TypedArray a, int index) {
        if (a.hasValue(index)) {
            view.setBackgroundColor(a.getColor(index, Color.TRANSPARENT));
        }
    }

    private void setTextColor(TextView view, TypedArray a, int index) {
        if (a.hasValue(index)) {
            view.setTextColor(a.getColor(index, Color.BLACK));
        }
    }

    private void setTextSize(TextView view, TypedArray a, int index) {
        if (a.hasValue(index)) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, a.getDimension(index, view.getTextSize()));
        }
    }

    public void setRankBackground(int color) {
        tvRank.setBackgroundColor(color);
    }

    public void setUsernameBackground(int color) {
        tvUsername.setBackgroundColor(color);
    }

    public void setPointsBackground(int color) {
        tvPoints.setBackgroundColor(color);
    }

    public void setRankTextColor(int color) {
        tvRank.setTextColor(color);
    }

    public void setUsernameTextColor(int color) {
        tvUsername.setTextColor(color);
    }

    public void setPointsTextColor(int color) {
        tvPoints.setTextColor(color);
    }

    public void setRankTextSize(float size) {
        tvRank.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setUsernameTextSize(float size) {
        tvUsername.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setPointsTextSize(float size) {
        tvPoints.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setRank(int rank) {
        tvRank.setText(String.valueOf(rank));
    }

    public void setUsername(String username) {
        tvUsername.setText(username);
    }

    public void setPoints(int points) {
        tvPoints.setText(String.valueOf(points));
    }
}