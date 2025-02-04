package com.example.appgamification;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gamificationlib.PlayerRankView;
import com.gamificationlib.TopPlayersView;

public class LeaderboardActivity extends AppCompatActivity {
    private TopPlayersView topPlayersView;
    private PlayerRankView playerRankView;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        topPlayersView = findViewById(R.id.topPlayersView);
        playerRankView = findViewById(R.id.playerRankView);
        btnBack = findViewById(R.id.btnBack);

        topPlayersView.fetchTopPlayers(10);
        playerRankView.fetchPlayerRank(PlayerManager.getInstance().getPlayer().getPlayerID());

        playerRankView.getItemPlayerView().setPointsTextSize(32);
        playerRankView.getItemPlayerView().setUsernameBackground(Color.LTGRAY);
        playerRankView.getItemPlayerView().setRankTextColor(Color.RED);

        // Back button functionality
        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(LeaderboardActivity.this, PlayerDataActivity.class));
            finish();
        });
    }
}