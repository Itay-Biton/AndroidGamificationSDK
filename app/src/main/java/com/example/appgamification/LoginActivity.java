package com.example.appgamification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gamificationlib.ApiController;
import com.gamificationlib.models.Player;

public class LoginActivity extends AppCompatActivity {
    private EditText etPlayerID;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Example setup information
        ApiController.setup("678bb0bb863b57a955392452", "678bb091863b57a95539244b");

        etPlayerID = findViewById(R.id.etPlayerID);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String playerID = etPlayerID.getText().toString().trim();
            if (!playerID.isEmpty()) {
                ApiController.fetchPlayerById(playerID, new ApiController.CallBack_Player() {
                    @Override
                    public void ready(Player player) {
                        PlayerManager.getInstance().setPlayer(player);
                        startActivity(new Intent(LoginActivity.this, PlayerDataActivity.class));
                        finish();
                    }

                    @Override
                    public void failed(String message) {
                        Toast.makeText(LoginActivity.this, "Invalid Player ID", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                Toast.makeText(LoginActivity.this, "Enter Player ID", Toast.LENGTH_SHORT).show();
            }
        });
    }
}