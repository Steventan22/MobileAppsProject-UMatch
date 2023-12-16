package com.uMatch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterChat extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int sender = 100;
    private final int receiver = 200;
    private List<ChatItems> items= new ArrayList<>();
    private Context ctx;
    private OnItemClickListener onClickListener;



    public interface OnItemClickListener{
        void onItemClick(View view, ChatItems obj, int position);
    }
    public void  setOnItemClickListener(final OnItemClickListener itemClickListener){
        this.onClickListener = itemClickListener;
    }

    public AdapterChat(Context context){
        this.ctx = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == sender){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.container_sender,parent,false);
            viewHolder = new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.container_receiver,parent,false);
            viewHolder = new ItemViewHolder(view);
        }
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if(holder instanceof ItemViewHolder){
            final ChatItems c = items.get(position);
            ItemViewHolder itemHolders = (ItemViewHolder) holder;
            itemHolders.txtContent.setText(c.getContent());
            itemHolders.txtTime.setText(c.getDate());
            itemHolders.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickListener != null){
                        onClickListener.onItemClick(view, c, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.items.get(position).isFromMe() ? sender : receiver;
    }

    public void insertItem(ChatItems items){
        this.items.add(items);
        notifyItemInserted(getItemCount());
    }

    public void setItems(List<ChatItems> items){
        this.items = items;
    }





    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView txtContent;
        public TextView txtTime;
        public View layout;

        public ItemViewHolder(View view){
            super(view);
            txtContent = view.findViewById(R.id.text_content);
            txtTime = view.findViewById(R.id.text_time);
            layout = view.findViewById(R.id.layout);
        }
    }


}
