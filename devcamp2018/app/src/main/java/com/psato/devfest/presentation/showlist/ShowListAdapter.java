package com.psato.devfest.presentation.showlist;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.psato.devfest.BR;
import com.psato.devfest.R;
import com.psato.devfest.data.entity.ShowInfo;
import com.psato.devfest.databinding.ItemShowInfoBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by psato on 30/10/16.
 */

public class ShowListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ShowItemViewModel> mItemViewModels = new ArrayList<>();


    public void setData(List<ShowInfo> showInfos) {
        mItemViewModels.clear();
        for (ShowInfo info : showInfos) {
            ShowItemViewModel viewModel = new ShowItemViewModel(info);
            mItemViewModels.add(viewModel);
        }
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemShowInfoBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), R.layout.item_show_info, parent, false);
        return new ShowInfoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ShowInfoViewHolder) {
            ShowInfoViewHolder infoHolder = (ShowInfoViewHolder) holder;
            infoHolder.getItemBinding()
                    .setVariable(BR.viewModel,
                            mItemViewModels.get(position));
        }

    }

    protected static class ShowInfoViewHolder extends RecyclerView.ViewHolder {
        private ItemShowInfoBinding mBinding;

        public ShowInfoViewHolder(ItemShowInfoBinding itemBinding) {
            super(itemBinding.getRoot());
            mBinding = itemBinding;
        }

        public ItemShowInfoBinding getItemBinding() {
            return mBinding;
        }
    }

    @Override
    public int getItemCount() {
        return mItemViewModels.size();
    }
}
