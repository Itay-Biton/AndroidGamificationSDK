package com.gamificationlib;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gamificationlib.models.Player;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<Player> players;

    public PlayerAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlayerView itemView = new ItemPlayerView(parent.getContext(), null);
        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.itemView.setLayoutParams(
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        );
        Player player = players.get(position);
        holder.itemPlayerView.setRank(position + 1);
        holder.itemPlayerView.setUsername(player.getUsername());
        holder.itemPlayerView.setPoints(player.getPlayerPoints());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        ItemPlayerView itemPlayerView;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPlayerView = (ItemPlayerView) itemView;
        }
    }
}
