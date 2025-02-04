package com.example.appgamification;

import com.gamificationlib.models.Player;

public class PlayerManager {
    private static PlayerManager instance;
    private Player currentPlayer;

    private PlayerManager() {}

    public static synchronized PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    public void setPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getPlayer() {
        return currentPlayer;
    }

    public boolean isPlayerLoggedIn() {
        return currentPlayer != null;
    }

    public void logout() {
        currentPlayer = null;
    }
}
