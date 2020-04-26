package integration.unity.akhil.gamedepot.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import integration.unity.akhil.gamedepot.R;
import integration.unity.akhil.gamedepot.databinding.FragmentAnticipatedListRowItemBinding;
import integration.unity.akhil.gamedepot.databinding.FragmentPopularListRowItemBinding;
import integration.unity.akhil.gamedepot.models.Result;
import integration.unity.akhil.gamedepot.view.MainListFragment;
import integration.unity.akhil.gamedepot.view.callback.OnClickCallBack;

public class GamesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<? extends  Result> gameList;
    private MainListFragment.GameType gameType;
    public OnClickCallBack detailCallbackListener;

    public GamesAdapter(MainListFragment.GameType gameType) {
        this.gameType = gameType;
    }

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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch(gameType) {
            case Popular:
                FragmentPopularListRowItemBinding bindingPopular = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.fragment_popular_list_row_item,
                    parent, false);
                    bindingPopular.setCallback(detailCallbackListener);
                    viewHolder = new PopularGameViewHolder(bindingPopular);
                    break;
            case TopRated:
            case Anticipated:
                FragmentAnticipatedListRowItemBinding bindingAnticipated = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.fragment_anticipated_list_row_item,
                        parent, false);
                bindingAnticipated.setCallback(detailCallbackListener);
                viewHolder =  new AnticipatedGameViewHolder(bindingAnticipated);
            break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch(gameType) {
            case Popular:
                ((PopularGameViewHolder) holder).binding.setResult(gameList.get(position));
                ((PopularGameViewHolder) holder).binding.executePendingBindings();
                break;
            case TopRated:
            case Anticipated:
                ((AnticipatedGameViewHolder) holder).binding.setResult(gameList.get(position));
                ((AnticipatedGameViewHolder) holder).binding.executePendingBindings();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return gameList == null ? 0 : gameList.size();
    }

    class PopularGameViewHolder extends RecyclerView.ViewHolder {
        FragmentPopularListRowItemBinding binding;

        PopularGameViewHolder(FragmentPopularListRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    class AnticipatedGameViewHolder extends RecyclerView.ViewHolder {
        FragmentAnticipatedListRowItemBinding binding;

        AnticipatedGameViewHolder(FragmentAnticipatedListRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
