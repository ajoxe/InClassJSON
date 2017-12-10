package nyc.c4q.jsoninclass.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import nyc.c4q.jsoninclass.R;
import nyc.c4q.jsoninclass.model.Message;

/**
 * Created by amirahoxendine on 12/10/17.
 */



public class MessageViewHolder extends RecyclerView.ViewHolder{
    TextView textView;

    public MessageViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.message_textview);
    }

    public void onBind(Message message){
        textView.setText(message.getMessage());
    }
}
