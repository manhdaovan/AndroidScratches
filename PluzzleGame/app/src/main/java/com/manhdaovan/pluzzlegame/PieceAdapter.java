package com.manhdaovan.pluzzlegame;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class PieceAdapter extends RecyclerView.Adapter<PieceAdapter.NumberViewHolder> {
    private static final String TAG = PieceAdapter.class.getSimpleName();

    private int numberItems;

    public PieceAdapter(int numberOfItems){
        numberItems = numberOfItems;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_piece;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position){
        Log.e(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount(){
        return numberItems;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder{
        TextView listItemNumberView;

        public NumberViewHolder(View itemView){
            super(itemView);
            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_piece_number);
        }

        public void bind(int listIndex){
            listItemNumberView.setText("" + listIndex);
        }
    }
}
