package de.fh_kiel.iue.mob2021_hw_938055_933041;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<CryptoData> cryptodatalist;
    private OnNoteListener mOnNoteListener;

    public Adapter(ArrayList<CryptoData> cryptodatalist, OnNoteListener onNoteListener) {
        this.cryptodatalist = cryptodatalist;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdesign, parent, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String name = cryptodatalist.get(position).getName();
        String euro = cryptodatalist.get(position).getEuro();
        holder.setData(name, euro);
    }

    @Override
    public int getItemCount() {
        return cryptodatalist.size();
    }

    //view holder class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private TextView textView2;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemTextLeft);
            textView2 = itemView.findViewById(R.id.itemTextRight);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        public void setData(String name, String euro) {
            textView.setText(name);
            textView2.setText(euro);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}


