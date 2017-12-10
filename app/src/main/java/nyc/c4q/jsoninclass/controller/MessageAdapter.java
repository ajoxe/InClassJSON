package nyc.c4q.jsoninclass.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.jsoninclass.R;
import nyc.c4q.jsoninclass.model.Message;
import nyc.c4q.jsoninclass.view.MessageViewHolder;

/**
 * Created by amirahoxendine on 12/10/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private List<Message> messageList;

    public MessageAdapter(List<Message> messageList){
        this.messageList = messageList;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_view, parent, false);
       return new MessageViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {

        Message message = messageList.get(position);
        holder.onBind(message);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
