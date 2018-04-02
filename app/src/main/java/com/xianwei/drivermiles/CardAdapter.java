package com.xianwei.drivermiles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xianwei li on 3/31/2018.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<RecyclerviewItemModel> lists;
    private RecyclerviewItemModel item;
    private Context context;

    public CardAdapter(Context context, List<RecyclerviewItemModel> lists) {
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        item = lists.get(position);
        holder.imageView.setImageResource(item.getImageSourceId());
        holder.name.setText(item.getItemName());
        holder.subName.setText(item.getSubItemName());
//        holder.itemView
//                .animate()
//                .alpha(1.0f)
//                .setDuration((position + 1)*300)
//                .start();
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        ImageView imageView;
        @BindView(R.id.item_name)
        TextView name;
        @BindView(R.id.item_subname)
        TextView subName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
