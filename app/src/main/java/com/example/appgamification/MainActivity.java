package com.example.appgamification;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appgamification.databinding.ActivityMainBinding;
import com.example.gamificationlib.ApiController;
import com.example.gamificationlib.PlayerRankView;
import com.example.gamificationlib.TopPlayersView;
import com.example.gamificationlib.models.Achievement;
import com.example.gamificationlib.models.Player;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private TopPlayersView topPlayersView;
    private PlayerRankView playerRankView;
    private Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        refresh = binding.refreshBtn;
        refresh.setOnClickListener(v -> refreshUI());
        topPlayersView = binding.topPlayersView;
        playerRankView = binding.playerRankView;
        playerRankView.setVisibility(View.GONE);

        // Example setup information
        ApiController.setup("678bb0bb863b57a955392452", "678bb091863b57a95539244b");

        playerRankView.getItemPlayerView().setPointsTextColor(Color.RED);
    }

    private void refreshUI() {
        int limit = 2;
        topPlayersView.fetchTopPlayers(limit);
        ApiController.fetchPlayerRank("1234", new ApiController.CallBack_Integer() {
            @Override
            public void ready(Integer result) {
                topPlayersView.fetchTopPlayers(limit);
                playerRankView.setVisibility(View.VISIBLE);
                if (result > limit) {
                    playerRankView.setVisibility(View.VISIBLE);
                    playerRankView.fetchPlayerRank("1234");
                }
                else
                    playerRankView.setVisibility(View.GONE);
            }

            @Override
            public void failed(String message) {

            }
        });
    }
}