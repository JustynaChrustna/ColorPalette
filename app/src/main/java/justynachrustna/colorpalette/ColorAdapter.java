package justynachrustna.colorpalette;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorViewHolder>{
    private final LayoutInflater layoutInflater;
    private List<String> colors= new ArrayList<>();

    public ColorAdapter(LayoutInflater layoutInflater){

        this.layoutInflater = layoutInflater;
    }
    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {
        String colorInHex=colors.get(position);
        holder.setColor(colorInHex);

    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    public void add(String color){
        colors.add(color);
        notifyItemInserted(colors.size()-1);
    }

    public void remove(int position) {
        colors.remove(position);
    }
}

class ColorViewHolder extends RecyclerView.ViewHolder{
    private String color;
    private TextView textView;
        public ColorViewHolder(View itemView){
            super(itemView);
            textView= (TextView) itemView;
        }


    public void setColor(String color) {
        this.color = color;
        textView.setText(color);
        textView.setBackgroundColor(Color.parseColor(color));
    }
    public String getColor(){
        return color;
    }
}
