package com.example.appgamification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamificationlib.models.Achievement;

import java.util.List;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder> {
    private List<Achievement> achievementList;

    public AchievementAdapter(List<Achievement> achievementList) {
        this.achievementList = achievementList;
    }

    @NonNull
    @Override
    public AchievementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_achievement, parent, false);
        return new AchievementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementViewHolder holder, int position) {
        Achievement achievement = achievementList.get(position);
        holder.tvAchievementTitle.setText(achievement.getTitle());
        holder.tvAchievementPoints.setText("Points: " + achievement.getPointsNeeded());
    }

    @Override
    public int getItemCount() {
        return achievementList.size();
    }

    public static class AchievementViewHolder extends RecyclerView.ViewHolder {
        TextView tvAchievementTitle, tvAchievementPoints;

        public AchievementViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAchievementTitle = itemView.findViewById(R.id.tvAchievementTitle);
            tvAchievementPoints = itemView.findViewById(R.id.tvAchievementPoints);
        }
    }
}
