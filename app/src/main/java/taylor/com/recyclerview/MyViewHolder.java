package taylor.com.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public MyViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(String data){
        ((TextView) itemView.findViewById(R.id.tv)).setText(data);
    }
}
