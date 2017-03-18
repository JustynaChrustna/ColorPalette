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

    private ColorClickedListener colorClickedListener;
    public ColorAdapter(LayoutInflater layoutInflater){

        this.layoutInflater = layoutInflater;
    }
    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        return new ColorViewHolder(view, this);
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

    public void setColorClickedListener(ColorClickedListener colorClickedListener) {
        this.colorClickedListener = colorClickedListener;
    }

    public void clicked(int position) {
        if(colorClickedListener!=null){
            colorClickedListener.onColorClicked(colors.get(position));
        }

    }

    public void replace(String oldColor, String colorInHex) {
        int indexOf= colors.indexOf(oldColor);
        colors.set(indexOf,colorInHex);
        notifyItemChanged(indexOf);
    }

    public interface ColorClickedListener{
        void onColorClicked(String colorInHex);
    }
}

class ColorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private String color;
    private TextView textView;
    private final ColorAdapter colorAdapter;

    public ColorViewHolder(View itemView, ColorAdapter colorAdapter){
            super(itemView);
            textView= (TextView) itemView;
        this.colorAdapter = colorAdapter;
        textView.setOnClickListener(this);
        }


    public void setColor(String color) {
        this.color = color;
        textView.setText(color);
        textView.setBackgroundColor(Color.parseColor(color));
    }
    public String getColor(){
        return color;
    }

    @Override
    public void onClick(View view) {
        colorAdapter.clicked(getAdapterPosition());
    }
}
