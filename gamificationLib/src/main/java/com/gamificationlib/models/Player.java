package com.gamificationlib.models;

import java.util.List;

public class Player {
    private String playerID;
    private String username;
    private int playerPoints;
    private List<String> achievementIdList; // IDs of achievements completed by the player

    // Constructor
    public Player(String playerID, String username, int playerPoints, List<String> achievementIdList) {
        this.playerID = playerID;
        this.username = username;
        this.playerPoints = playerPoints;
        this.achievementIdList = achievementIdList;
    }

    // Getters
    public String getPlayerID() {
        return playerID;
    }

    public String getUsername() {
        return username;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public List<String> getAchievementIdList() {
        return achievementIdList;
    }

    // Setters
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }

    public void setAchievementIdList(List<String> achievementIdList) {
        this.achievementIdList = achievementIdList;
    }
}