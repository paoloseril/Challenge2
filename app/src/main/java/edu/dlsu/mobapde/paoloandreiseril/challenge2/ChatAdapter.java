package edu.dlsu.mobapde.paoloandreiseril.challenge2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {

    private ArrayList<ChatModel> chatModels;

    public ChatAdapter() {
        chatModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.chat, viewGroup, false);

        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder chatHolder, int i) {
        chatHolder.setImage(chatModels.get(i).getImage());
        chatHolder.setText(chatModels.get(i).getMessage());
    }

    @Override
    public int getItemCount() {
        return chatModels.size();
    }

    public void addItem(int img, String name){
        chatModels.add(new ChatModel(img, name));
        notifyItemInserted(chatModels.size() - 1);
    }
}
