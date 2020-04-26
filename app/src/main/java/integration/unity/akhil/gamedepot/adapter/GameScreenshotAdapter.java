package integration.unity.akhil.gamedepot.adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import integration.unity.akhil.gamedepot.R;
import integration.unity.akhil.gamedepot.databinding.FragmentDetailItemBinding;
import integration.unity.akhil.gamedepot.models.ShortScreenshot;


public class GameScreenshotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<? extends ShortScreenshot> screenshots;

    public void setGameList(final List<? extends ShortScreenshot> screenshots) {
        if (this.screenshots == null) {
            this.screenshots = screenshots;
            notifyItemRangeInserted(0, screenshots.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return GameScreenshotAdapter.this.screenshots.size();
                }

                @Override
                public int getNewListSize() {
                    return screenshots.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return GameScreenshotAdapter.this.screenshots.get(oldItemPosition).getId() ==
                            GameScreenshotAdapter.this.screenshots.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    ShortScreenshot newArticle = screenshots.get(newItemPosition);
                    ShortScreenshot oldArticle = screenshots.get(oldItemPosition);
                    return newArticle.getImage().equals(oldArticle.getImage());
                }
            });
            this.screenshots = screenshots;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        FragmentDetailItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.fragment_detail_item,
                parent, false);
        viewHolder = new ScreenShotViewHolder(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("GameAdapter: ", "" + screenshots.get(position).getImage());
        ((ScreenShotViewHolder) holder).binding.setResult(screenshots.get(position));
        ((ScreenShotViewHolder) holder).binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return screenshots == null ? 0 : screenshots.size();
    }

    class ScreenShotViewHolder extends RecyclerView.ViewHolder {
        FragmentDetailItemBinding binding;

        ScreenShotViewHolder(FragmentDetailItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
