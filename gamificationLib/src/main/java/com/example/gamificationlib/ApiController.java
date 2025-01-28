package com.example.gamificationlib;

import com.example.gamificationlib.models.Achievement;
import com.example.gamificationlib.models.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {

    private static final String BASE_URL = "https://gamificationsdk-server.onrender.com/";

    private static CallBack_Players callBackPlayers;
    private static CallBack_Player callBackPlayer;
    private static CallBack_Achievements callBackAchievements;
    private static CallBack_Achievement callBackAchievement;
    private static CallBack_Boolean callBackBoolean;
    private static CallBack_Integer callBackInteger;

    private static String APP_ID;
    private static String API_KEY;

    public static void setAppID(String appID) {
        APP_ID = appID;
    }

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public static void setup(String appID, String apiKey) {
        setAppID(appID);
        setApiKey(apiKey);
    }

    private static void setCallBackPlayers(CallBack_Players newCallBackPlayers) {
        callBackPlayers = newCallBackPlayers;
    }

    private static void setCallBackPlayer(CallBack_Player newCallBackPlayer) {
        callBackPlayer = newCallBackPlayer;
    }

    private static void setCallBackAchievements(CallBack_Achievements newCallBackAchievements) {
        callBackAchievements = newCallBackAchievements;
    }

    private static void setCallBackAchievement(CallBack_Achievement newCallBackAchievement) {
        callBackAchievement = newCallBackAchievement;
    }

    private static void setCallBackBoolean(CallBack_Boolean newCallBackBoolean) {
        callBackBoolean = newCallBackBoolean;
    }

    private static void setCallBackInteger(CallBack_Integer newCallBackInteger) {
        callBackInteger = newCallBackInteger;
    }

    public ApiController(String appID, String apiKey) {
        //APP_ID = appID;
        //API_KEY = apiKey;
    }

    private static ApiService getAPI() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ApiService.class);
    }

    // *** Players Endpoints *** //
    public static void fetchAllPlayers(CallBack_Players callBackPlayers) {
        if (callBackPlayers == null) return;
        setCallBackPlayers(callBackPlayers);
        Call<List<Player>> call = getAPI().getAllPlayers(API_KEY, APP_ID);
        call.enqueue(allPlayersCallBack);
    }
    public static void fetchPlayerById(String playerID, CallBack_Player callBackPlayer) {
        if (playerID == null || playerID.isEmpty() || callBackPlayer == null) return;
        setCallBackPlayer(callBackPlayer);
        Call<Player> call = getAPI().getPlayer(API_KEY, APP_ID, playerID);
        call.enqueue(onePlayerCallBack);
    }
    public static void fetchTopPlayers(int limit, CallBack_Players callBackPlayers) {
        if (limit <= 0 || callBackPlayers == null) return;
        setCallBackPlayers(callBackPlayers);Call<List<Player>> call = getAPI().getTopPlayers(API_KEY, APP_ID, limit);
        call.enqueue(allPlayersCallBack);
    }
    public static void fetchPlayerRank(String playerID, CallBack_Integer callBackInteger) {
        if (playerID == null || playerID.isEmpty() || callBackInteger == null) return;
        setCallBackInteger(callBackInteger);
        Call<Integer> call = getAPI().getPlayerRank(API_KEY, APP_ID, playerID);
        call.enqueue(integerCallback);
    }
    public static void addPoints(String playerID, int amount, CallBack_Player callBackPlayer) {
        if (playerID == null || playerID.isEmpty() || amount <= 0 || callBackPlayer == null) return;
        setCallBackPlayer(callBackPlayer);
        Call<Player> call = getAPI().addPoints(API_KEY, APP_ID, playerID, amount);
        call.enqueue(onePlayerCallBack);
    }
    public static void reducePoints(String playerID, int amount, CallBack_Player callBackPlayer) {
        if (playerID == null || playerID.isEmpty() || amount <= 0 || callBackPlayer == null) return;
        setCallBackPlayer(callBackPlayer);
        Call<Player> call = getAPI().reducePoints(API_KEY, APP_ID, playerID, amount);
        call.enqueue(onePlayerCallBack);
    }
    public static void setPoints(String playerID, int amount, CallBack_Player callBackPlayer) {
        if (playerID == null || playerID.isEmpty() || amount < 0 || callBackPlayer == null) return;
        setCallBackPlayer(callBackPlayer);
        Call<Player> call = getAPI().setPoints(API_KEY, APP_ID, playerID, amount);
        call.enqueue(onePlayerCallBack);
    }
    public static void createPlayer(Player newPlayer, CallBack_Player callBackPlayer) {
        if (newPlayer == null || callBackPlayer == null) return;
        setCallBackPlayer(callBackPlayer);
        Call<Player> call = getAPI().createPlayer(API_KEY, APP_ID, newPlayer.getPlayerID(), newPlayer);
        call.enqueue(onePlayerCallBack);
    }
    public static void deletePlayer(String playerID, CallBack_Boolean callBackBoolean) {
        if (playerID == null || playerID.isEmpty() || callBackBoolean == null) return;
        setCallBackBoolean(callBackBoolean);
        Call<Boolean> call = getAPI().deletePlayer(API_KEY, APP_ID, playerID);
        call.enqueue(booleanCallback);
    }
    public static void setPlayerUsername(String playerID, String newUsername, CallBack_Player callBackPlayer) {
        if (playerID == null || playerID.isEmpty() || newUsername == null || newUsername.isEmpty() || callBackPlayer == null) return;
        setCallBackPlayer(callBackPlayer);
        Call<Player> call = getAPI().setUsername(API_KEY, APP_ID, playerID, newUsername);
        call.enqueue(onePlayerCallBack);
    }

    // Achievements Endpoints
    public static void fetchAllAchievements(CallBack_Achievements callBackAchievements) {
        if (callBackAchievements == null) return;
        setCallBackAchievements(callBackAchievements);
        Call<List<Achievement>> call = getAPI().getAllAchievements(API_KEY, APP_ID);
        call.enqueue(allAchievementsCallBack);
    }
    public static void fetchPlayerAchievementsDone(String playerID, CallBack_Achievements callBackAchievements) {
        if (playerID == null || playerID.isEmpty() || callBackAchievements == null) return;
        setCallBackAchievements(callBackAchievements);
        Call<List<Achievement>> call = getAPI().getPlayerDoneAchievements(API_KEY, APP_ID, playerID);
        call.enqueue(allAchievementsCallBack);
    }
    public static void fetchPlayerAchievementsUnfinished(String playerID, CallBack_Achievements callBackAchievements) {
        if (playerID == null || playerID.isEmpty() || callBackAchievements == null) return;
        setCallBackAchievements(callBackAchievements);
        Call<List<Achievement>> call = getAPI().getPlayerUnfinishedAchievements(API_KEY, APP_ID, playerID);
        call.enqueue(allAchievementsCallBack);
    }
    public static void checkPlayerAchievement(String playerID, String achievementID, CallBack_Boolean callBackBoolean) {
        if (playerID == null || playerID.isEmpty() || achievementID == null || achievementID.isEmpty() || callBackBoolean == null) return;
        setCallBackBoolean(callBackBoolean);
        Call<Boolean> call = getAPI().checkPlayerAchievement(API_KEY, APP_ID, playerID, achievementID);
        call.enqueue(booleanCallback);
    }
    public static void getAchievementByPoints(int points, CallBack_Achievement callBackAchievement) {
        if (points < 0 || callBackAchievement == null) return;
        setCallBackAchievement(callBackAchievement);
        Call<Achievement> call = getAPI().getAchievementByPoints(API_KEY, APP_ID, points);
        call.enqueue(achievementCallback);
    }
    public static void getAchievementByTitle(String title, CallBack_Achievement callBackAchievement) {
        if (title == null || title.isEmpty() || callBackAchievement == null) return;
        setCallBackAchievement(callBackAchievement);
        Call<Achievement> call = getAPI().getAchievementByTitle(API_KEY, APP_ID, title);
        call.enqueue(achievementCallback);
    }
    public static void addAchievementToPlayer(String playerID, String achievementID, CallBack_Player callBackPlayer) {
        if (playerID == null || playerID.isEmpty() || achievementID == null || achievementID.isEmpty() || callBackPlayer == null) return;
        setCallBackPlayer(callBackPlayer);
        Call<Player> call = getAPI().addAchievementToPlayer(API_KEY, APP_ID, playerID, achievementID);
        call.enqueue(onePlayerCallBack);
    }
    public static void removeAchievementFromPlayer(String playerID, String achievementID, CallBack_Player callBackPlayer) {
        if (playerID == null || playerID.isEmpty() || achievementID == null || achievementID.isEmpty() || callBackPlayer == null) return;
        setCallBackPlayer(callBackPlayer);
        Call<Player> call = getAPI().removeAchievementFromPlayer(API_KEY, APP_ID, playerID, achievementID);
        call.enqueue(onePlayerCallBack);
    }

// Callbacks for Retrofit
    private static final Callback<List<Player>> allPlayersCallBack = new Callback<List<Player>>() {
        @Override
        public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
            if (response.isSuccessful())
                callBackPlayers.ready(response.body());
            else {
                try {
                    String errorBody = response.errorBody().string();
                    JSONObject jsonObject = new JSONObject(errorBody);
                    String errorMessage = jsonObject.optString("message", "Unknown error");
                    callBackPlayers.failed(errorMessage);
                } catch (IOException | JSONException e) {
                    callBackPlayers.failed("Unknown error occurred");
                }
            }
        }

        @Override
        public void onFailure(Call<List<Player>> call, Throwable throwable) {
            callBackPlayers.failed(throwable.getMessage());
        }
    };

    private static final Callback<Player> onePlayerCallBack = new Callback<Player>() {
        @Override
        public void onResponse(Call<Player> call, Response<Player> response) {
            if (response.isSuccessful())
                callBackPlayer.ready(response.body());
            else {
                try {
                    String errorBody = response.errorBody().string();
                    JSONObject jsonObject = new JSONObject(errorBody);
                    String errorMessage = jsonObject.optString("message", "Unknown error");
                    callBackPlayer.failed(errorMessage);
                } catch (IOException | JSONException e) {
                    callBackPlayer.failed("Unknown error occurred");
                }
            }
        }

        @Override
        public void onFailure(Call<Player> call, Throwable throwable) {
            callBackPlayer.failed(throwable.getMessage());
        }
    };

    private static final Callback<List<Achievement>> allAchievementsCallBack = new Callback<List<Achievement>>() {
        @Override
        public void onResponse(Call<List<Achievement>> call, Response<List<Achievement>> response) {
            if (response.isSuccessful())
                callBackAchievements.ready(response.body());
            else {
                try {
                    String errorBody = response.errorBody().string();
                    JSONObject jsonObject = new JSONObject(errorBody);
                    String errorMessage = jsonObject.optString("message", "Unknown error");
                    callBackAchievements.failed(errorMessage);
                } catch (IOException | JSONException e) {
                    callBackAchievements.failed("Unknown error occurred");
                }
            }
        }

        @Override
        public void onFailure(Call<List<Achievement>> call, Throwable throwable) {
            callBackAchievements.failed(throwable.getMessage());
        }
    };

    private static final Callback<Achievement> achievementCallback = new Callback<Achievement>() {
        @Override
        public void onResponse(Call<Achievement> call, Response<Achievement> response) {
            if (response.isSuccessful())
                callBackAchievement.ready(response.body());
            else {
                try {
                    String errorBody = response.errorBody().string();
                    JSONObject jsonObject = new JSONObject(errorBody);
                    String errorMessage = jsonObject.optString("message", "Unknown error");
                    callBackAchievement.failed(errorMessage);
                } catch (IOException | JSONException e) {
                    callBackAchievement.failed("Unknown error occurred");
                }
            }
        }

        @Override
        public void onFailure(Call<Achievement> call, Throwable throwable) {
            callBackAchievement.failed(throwable.getMessage());
        }
    };

    private static final Callback<Boolean> booleanCallback = new Callback<Boolean>() {
        @Override
        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
            if (response.isSuccessful())
                callBackBoolean.ready(response.body());
            else {
                try {
                    String errorBody = response.errorBody().string();
                    JSONObject jsonObject = new JSONObject(errorBody);
                    String errorMessage = jsonObject.optString("message", "Unknown error");
                    callBackBoolean.failed(errorMessage);
                } catch (IOException | JSONException e) {
                    callBackBoolean.failed("Unknown error occurred");
                }
            }
        }

        @Override
        public void onFailure(Call<Boolean> call, Throwable throwable) {
            callBackBoolean.failed(throwable.getMessage());
        }
    };

    private static final Callback<Integer> integerCallback = new Callback<Integer>() {
        @Override
        public void onResponse(Call<Integer> call, Response<Integer> response) {
            if (response.isSuccessful())
                callBackInteger.ready(response.body());
            else {
                try {
                    String errorBody = response.errorBody().string();
                    JSONObject jsonObject = new JSONObject(errorBody);
                    String errorMessage = jsonObject.optString("message", "Unknown error");
                    callBackInteger.failed(errorMessage);
                } catch (IOException | JSONException e) {
                    callBackInteger.failed(e.getMessage());
                }
            }
        }

        @Override
        public void onFailure(Call<Integer> call, Throwable throwable) {
            callBackInteger.failed(throwable.getMessage());
        }
    };

// Interfaces for Callbacks
    public interface CallBack_Players {
        void ready(List<Player> players);
        void failed(String message);
    }

    public interface CallBack_Player {
        void ready(Player player);
        void failed(String message);
    }

    public interface CallBack_Achievements {
        void ready(List<Achievement> achievements);
        void failed(String message);
    }

    public interface CallBack_Achievement {
        void ready(Achievement achievement);
        void failed(String message);
    }

    public interface CallBack_Boolean {
        void ready(Boolean result);
        void failed(String message);
    }

    public interface CallBack_Integer {
        void ready(Integer result);
        void failed(String message);
    }
}