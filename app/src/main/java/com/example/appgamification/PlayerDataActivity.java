package com.example.appgamification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gamificationlib.ApiController;
import com.gamificationlib.models.Achievement;
import com.gamificationlib.models.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerDataActivity extends AppCompatActivity {
    private TextView tvPlayerName, tvPlayerPoints;
    private RecyclerView achievementsRecyclerView;
    private Button btnLogout, btnGameAchievements, btnLeaderboard;
    private AchievementAdapter achievementAdapter;
    private List<Achievement> achievementList = new ArrayList<>(); // This will hold achievements

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_data);

        tvPlayerName = findViewById(R.id.tvPlayerName);
        tvPlayerPoints = findViewById(R.id.tvPlayerPoints);
        achievementsRecyclerView = findViewById(R.id.achievementsRecyclerView);
        btnGameAchievements = findViewById(R.id.btnGameAchievements);
        btnLeaderboard = findViewById(R.id.btnLeaderboard);
        btnLogout = findViewById(R.id.btnLogout);

        // Get current player
        Player currentPlayer = PlayerManager.getInstance().getPlayer();
        if (currentPlayer == null) {
            Toast.makeText(this, "No player logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Set player details
        tvPlayerName.setText(currentPlayer.getUsername());
        tvPlayerPoints.setText("Points: " + currentPlayer.getPlayerPoints());

        // Initialize RecyclerView
        achievementAdapter = new AchievementAdapter(achievementList);
        achievementsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        achievementsRecyclerView.setAdapter(achievementAdapter);

        // Load achievements (Assume fetchAchievementsForPlayer() exists)
        loadAchievements(currentPlayer.getAchievementIdList());

        // Handle button clicks
        btnLogout.setOnClickListener(v -> {
            PlayerManager.getInstance().logout();
            startActivity(new Intent(PlayerDataActivity.this, LoginActivity.class));
            finish();
        });

        btnGameAchievements.setOnClickListener(v -> {
            Intent intent = new Intent(PlayerDataActivity.this, GameAchievementsActivity.class);
            startActivity(intent);
            finish();
        });

        btnLeaderboard.setOnClickListener(v -> {
            Intent intent = new Intent(PlayerDataActivity.this, LeaderboardActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void loadAchievements(List<String> achievementIds) {
        ApiController.fetchPlayerAchievementsDone(PlayerManager.getInstance().getPlayer().getPlayerID(), new ApiController.CallBack_Achievements() {
            @Override
            public void ready(List<Achievement> achievements) {
                achievementList.clear();
                achievementList.addAll(achievements);
                achievementAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(String message) {

            }
        });
    }
}