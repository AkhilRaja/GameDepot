package integration.unity.akhil.gamedepot.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import integration.unity.akhil.gamedepot.R;
import integration.unity.akhil.gamedepot.databinding.FragmentMainListRowItemBinding;
import integration.unity.akhil.gamedepot.models.Result;
import integration.unity.akhil.gamedepot.view.callback.OnClickCallBack;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GameViewHolder> {

    public List<? extends  Result> gameList;


    public void setGameList(final List<? extends Result> gameList) {
        if (this.gameList == null) {
            this.gameList = gameList;
            notifyItemRangeInserted(0, gameList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return GamesAdapter.this.gameList.size();
                }

                @Override
                public int getNewListSize() {
                    return gameList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return GamesAdapter.this.gameList.get(oldItemPosition).getName() ==
                            GamesAdapter.this.gameList.get(newItemPosition).getName();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Result newArticle = gameList.get(newItemPosition);
                    Result oldArticle = gameList.get(oldItemPosition);
                    return newArticle.getName().equals(oldArticle.getName())
                            && oldArticle.getSlug().equals(newArticle.getSlug());
                }
            });
            this.gameList = gameList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        FragmentMainListRowItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.fragment_main_list_row_item,
                parent, false);

        //TODO: Add a click callback here
        binding.setCallback(new OnClickCallBack());

        return new GameViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        //TODO: Set Games
        holder.binding.setResult(gameList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return gameList == null ? 0 : gameList.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder {
        FragmentMainListRowItemBinding binding;

        public GameViewHolder(FragmentMainListRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
