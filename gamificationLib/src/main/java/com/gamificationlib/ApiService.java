package com.gamificationlib;

import com.gamificationlib.models.Achievement;
import com.gamificationlib.models.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

// *** Players Endpoints *** //
    // Get all players
    @GET("players/{appID}")
    Call<List<Player>> getAllPlayers(
            @Header("api_key") String apiKey,
            @Path("appID") String appID
    );
    // Get a specific player
    @GET("players/{appID}/player={playerID}")
    Call<Player> getPlayer(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID
    );
    // Get top players
    @GET("players/{appID}/top")
    Call<List<Player>> getTopPlayers(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Query("limit") int limit
    );
    // Get player's rank
    @GET("players/{appID}/player={playerID}/rank")
    Call<Integer> getPlayerRank(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID
    );
    // Add points to a player
    @POST("players/{appID}/player={playerID}/points/add")
    Call<Player> addPoints(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID,
            @Query("amount") int amount
    );
    // Reduce points for a player
    @POST("players/{appID}/player={playerID}/points/reduce")
    Call<Player> reducePoints(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID,
            @Query("amount") int amount
    );
    // Set points for a player
    @PUT("players/{appID}/player={playerID}/points/set")
    Call<Player> setPoints(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID,
            @Query("amount") int amount
    );
    // Create new player
    @POST("players/{appID}/create/player={playerID}")
    Call<Player> createPlayer(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID,
            @Body Player player
    );
    // Delete player
    @DELETE("players/{appID}/delete/player={playerID}")
    Call<Boolean> deletePlayer(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID
    );
    // Set player username
    @PUT("players/{appID}/player={playerID}/username/set")
    Call<Player> setUsername(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID,
            @Query("username") String username
    );

// *** Achievements Endpoints *** //
    // Get all achievements
    @GET("achievements/{appID}")
    Call<List<Achievement>> getAllAchievements(
            @Header("api_key") String apiKey,
            @Path("appID") String appID
    );
    // Get player's done achievements
    @GET("achievements/{appID}/player={playerID}/done")
    Call<List<Achievement>> getPlayerDoneAchievements(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID
    );
    // Get player's unfinished achievements
    @GET("achievements/{appID}/player={playerID}/todo")
    Call<List<Achievement>> getPlayerUnfinishedAchievements(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID
    );
    // Check if the player has achieved a specific achievement
    @GET("achievements/{appID}/player={playerID}/check/{achievementID}")
    Call<Boolean> checkPlayerAchievement(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID,
            @Path("achievementID") String achievementID
    );
    // Get achievement by points needed
    @GET("achievements/{appID}/points={points}")
    Call<Achievement> getAchievementByPoints(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("points") int points
    );
    // Get achievement by title
    @GET("achievements/{appID}/title={title}")
    Call<Achievement> getAchievementByTitle(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("title") String title
    );
    // Get achievement by title
    @GET("achievements/{appID}/id={id}")
    Call<Achievement> getAchievementByID(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("id") String id
    );
    // Add achievement to player
    @PUT("achievements/{appID}/player={playerID}/achievement={achievementID}/add")
    Call<Player> addAchievementToPlayer(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID,
            @Path("achievementID") String achievementID
    );
    // Remove achievement from player
    @PUT("achievements/{appID}/player={playerID}/achievement={achievementID}/remove")
    Call<Player> removeAchievementFromPlayer(
            @Header("api_key") String apiKey,
            @Path("appID") String appID,
            @Path("playerID") String playerID,
            @Path("achievementID") String achievementID
    );
}