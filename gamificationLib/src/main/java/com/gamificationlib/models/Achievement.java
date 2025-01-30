package com.gamificationlib.models;

import java.util.List;

public class Achievement {
    private String achievementID;
    private String title;
    private int pointsNeeded;
    private List<String> playerIdAchievedList; // IDs of players who completed this achievement

    // Constructor
    public Achievement(String achievementID, String title, int pointsNeeded, List<String> playerIdAchievedList) {
        this.achievementID = achievementID;
        this.title = title;
        this.pointsNeeded = pointsNeeded;
        this.playerIdAchievedList = playerIdAchievedList;
    }

    // Getters
    public String getAchievementID() {
        return achievementID;
    }

    public String getTitle() {
        return title;
    }

    public int getPointsNeeded() {
        return pointsNeeded;
    }

    public List<String> getPlayerIdAchievedList() {
        return playerIdAchievedList;
    }

    // Setters
    public void setAchievementID(String achievementID) {
        this.achievementID = achievementID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPointsNeeded(int pointsNeeded) {
        this.pointsNeeded = pointsNeeded;
    }

    public void setPlayerIdAchievedList(List<String> playerIdAchievedList) {
        this.playerIdAchievedList = playerIdAchievedList;
    }
}