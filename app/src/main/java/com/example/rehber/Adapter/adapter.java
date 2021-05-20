package com.example.rehber.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehber.Model.Model;
import com.example.rehber.R;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.usersViewHolder>{
    Context context;
    List<Model> usersList;

    public adapter(Context context, List<Model> usersList) {
        this.context = context;
        this.usersList = usersList;
    }



    public class usersViewHolder extends RecyclerView.ViewHolder
    {
     TextView cardName,cardEposta,cardWeb,cardSehir;


        public usersViewHolder(View view) {
            super(view);

            cardName = view.findViewById(R.id.card_kullaniciAd);
            cardEposta = view.findViewById(R.id.card_eposta);
            cardSehir = view.findViewById(R.id.card_sehir);
            cardWeb = view.findViewById(R.id.card_web);

        }
    }

    @NonNull
    @Override
    public usersViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new usersViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.usersViewHolder holder, int position) {

        holder.cardName.setText(usersList.get(position).getUsername());
        holder.cardEposta.setText(usersList.get(position).getEmail());
       // holder.cardSehir.setText(usersList.get(position).getAddress().getCity());
        holder.cardWeb.setText(usersList.get(position).getWebsite());

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }





}
