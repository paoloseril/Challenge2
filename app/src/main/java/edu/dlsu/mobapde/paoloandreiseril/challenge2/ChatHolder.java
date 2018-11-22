package edu.dlsu.mobapde.paoloandreiseril.challenge2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatHolder extends RecyclerView.ViewHolder {

    private ImageView imageSrc;
    private TextView commentView;

    public ChatHolder(@NonNull View itemView) {
        super(itemView);

        imageSrc = itemView.findViewById(R.id.char_icon);
        commentView = itemView.findViewById(R.id.msg);
    }

    public void setImage(int image) {
        imageSrc.setImageResource(image);
    }

    public void setText(String text) {
        commentView.setText(text);
    }
}
