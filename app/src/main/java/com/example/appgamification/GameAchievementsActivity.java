package com.example.appgamification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gamificationlib.ApiController;
import com.gamificationlib.models.Achievement;

import java.util.List;

public class GameAchievementsActivity extends AppCompatActivity {
    private RecyclerView gameAchievementsRecyclerView;
    private AchievementAdapter achievementAdapter;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_achievements);

        gameAchievementsRecyclerView = findViewById(R.id.gameAchievementsRecyclerView);
        btnBack = findViewById(R.id.btnBack);

        // Fetch achievements
        ApiController.fetchPlayerAchievementsUnfinished(PlayerManager.getInstance().getPlayer().getPlayerID(), new ApiController.CallBack_Achievements() {
            @Override
            public void ready(List<Achievement> achievements) {
                // Set up RecyclerView
                gameAchievementsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                achievementAdapter = new AchievementAdapter(achievements);
                gameAchievementsRecyclerView.setAdapter(achievementAdapter);
            }

            @Override
            public void failed(String message) {

            }
        });

        // Back button functionality
        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(GameAchievementsActivity.this, PlayerDataActivity.class));
            finish();
        });
    }
}