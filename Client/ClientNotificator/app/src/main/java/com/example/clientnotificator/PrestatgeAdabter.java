package com.example.clientnotificator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.clientnotificator.TipoIncidencia.*;

public class PrestatgeAdabter extends RecyclerView.Adapter<PrestatgeAdabter.ViewHolder> {

    private List<PrestatgeView> userModelList;

    public PrestatgeAdabter() {
        userModelList = new ArrayList<PrestatgeView>();
    }

    public void updateData (List<PrestatgeView> userModelList) {
        this.userModelList = userModelList;
    }

    @NonNull
    @Override
    public PrestatgeAdabter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.prestatge, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PrestatgeAdabter.ViewHolder holder, int position) {
        String nomPrestatge        = userModelList.get(position).getNom();
        String descripcioPrestatge = userModelList.get(position).getUbicacio();
        int nFalles                = userModelList.get(position).getnFallos();

        holder.nomPrestatge.setText(nomPrestatge);
        holder.descripcioPrestatge.setText(descripcioPrestatge);
        holder.nFalles.setText(String.valueOf(nFalles));

        TipoIncidencia tIncidencia = VERDE;

        if (nFalles > 0) tIncidencia = AMARILLO;
        if (nFalles > 5) tIncidencia = ROJO;

        switch (tIncidencia) {
            case VERDE: holder.notificacioImg.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.like));    break;
            case AMARILLO: holder.notificacioImg.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.care)); break;
            case ROJO: holder.notificacioImg.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.red));      break;
        }
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nomPrestatge;
        private TextView descripcioPrestatge;
        private ImageView notificacioImg;
        private TextView nFalles;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomPrestatge = itemView.findViewById(R.id.nomPrestatgeID);
            descripcioPrestatge = itemView.findViewById(R.id.DescripcioPrestatgeID);
            notificacioImg = itemView.findViewById(R.id.imageView);
            nFalles = itemView.findViewById(R.id.nNumId);
        }
    }
}
