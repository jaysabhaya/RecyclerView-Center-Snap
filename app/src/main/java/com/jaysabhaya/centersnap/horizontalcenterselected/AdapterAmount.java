package com.jaysabhaya.centersnap.horizontalcenterselected;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaysabhaya.centersnap.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterAmount extends RecyclerView.Adapter<AdapterAmount.viewHolder> {
    Context context;
    List<String> Items;
    ClickItemAmount onClickListenerItemAmount;
    ItemListener itemListener;
    public int mSelectedItem = -1;

    private RecyclerView parentRecycler;
    public static final int VIEW_TYPE_PADDING = 1111;
    public static final int VIEW_TYPE_PADDING_Left = 3333;
    public static final int VIEW_TYPE_ITEM = 2222;
    private int paddingWidth;

    int layout;
    int bgitemselected;
    int bgitem;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        parentRecycler = recyclerView;
    }

    public AdapterAmount(Context context, int bgitemselected, int bgitem, ClickItemAmount onClickListenerItemAmount,
                         ItemListener itemListener, int padding, int layout) {
        this.context = context;
        Items = new ArrayList<>();
        this.itemListener = itemListener;
        this.onClickListenerItemAmount = onClickListenerItemAmount;
        this.paddingWidth = padding;
        this.layout = layout;
        this.bgitemselected = bgitemselected;
        this.bgitem = bgitem;
    }

    public AdapterAmount(Context context, ArrayList<String> list) {
        this.context = context;
        Items = list;
    }

    public void setItems(List<String> Items) {
        this.Items.clear();
        this.Items.addAll(Items);
        notifyDataSetChanged();
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amount, parent, false);
        // if (viewType == VIEW_TYPE_PADDING) {
        // RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)
        // view.getLayoutParams();
        // layoutParams.setMargins(0, 0, paddingWidth, 0);
        // view.setLayoutParams(layoutParams);
        // } else if (viewType == VIEW_TYPE_PADDING_Left) {
        // RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)
        // view.getLayoutParams();
        // layoutParams.setMargins(paddingWidth, 0, 0, 0);
        // view.setLayoutParams(layoutParams);
        // } else {
        // RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)
        // view.getLayoutParams();
        // layoutParams.setMargins(0, 0, 0, 0);
        // view.setLayoutParams(layoutParams);
        // }
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position) {
        holder.itemView.setVisibility(View.VISIBLE);
        holder.textView.setText(String.valueOf(Items.get(position)));
        if (position == mSelectedItem) {
            // holder.textView.setBackgroundResource(R.drawable.rectangle_round_red);
            holder.textView.setTextSize(30);
        } else {
            // holder.textView.setBackgroundResource(R.drawable.rectangle_round_full_gray);
            holder.textView.setTextSize(15);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListenerItemAmount != null) {
                    onClickListenerItemAmount.ClickItem(position);
                }
            }
        });

    }

    public void setSelecteditem(int selecteditem) {
        this.mSelectedItem = selecteditem;
        if (itemListener != null) {
            this.itemListener.getValue(Items.get(selecteditem), selecteditem);
        }
        notifyDataSetChanged();
    }

    public String getmSelectedItem() {
        return Items.get(mSelectedItem);
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_PADDING_Left;
        } else if (position == Items.size() - 1) {
            return VIEW_TYPE_PADDING;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public viewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.title);

            textView.setOnClickListener(view -> {
                parentRecycler.smoothScrollToPosition(getAdapterPosition());
            });
        }

        /*
         * public void showText() {
         * float scale = (parentHeight - textView.getHeight()) / (float)
         * imageView.getHeight();
         * }
         *
         * public void hideText() {
         * imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(),
         * R.color.grayIconTint));
         * textView.setVisibility(View.INVISIBLE);
         * imageView.animate().scaleX(1f).scaleY(1f)
         * .setDuration(200)
         * .start();
         * }
         */

    }
}