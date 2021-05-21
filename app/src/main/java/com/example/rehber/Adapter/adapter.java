package com.example.rehber.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rehber.Model.Model;
import com.example.rehber.R;

import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.usersViewHolder> implements Filterable {
    Context context;
    List<Model> usersListFull;
    List<Model> returnList;

    public adapter(Context context, List<Model> fullList) {
        this.context = context;
        this.returnList = fullList;
        usersListFull = new ArrayList<>(returnList);
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

        holder.cardName.setText(returnList.get(position).getUsername());
        holder.cardEposta.setText(returnList.get(position).getEmail());
       // holder.cardSehir.setText(usersList.get(position).getAddress().getCity());
        holder.cardWeb.setText(returnList.get(position).getWebsite());

    }

    @Override
    public int getItemCount() {
        return returnList.size();
    }
    @Override
    public Filter getFilter() {
        return modelFilter;
    }

    private Filter modelFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Model> filteredList = new ArrayList<>();
            if(constraint==null || constraint.length() == 0)
            {
                filteredList.addAll(usersListFull);
            }
            else
            {
                String filterpattern = constraint.toString().toLowerCase().trim();
                for (Model model : usersListFull)
                {
                    if (model.getUsername().toLowerCase().contains(filterpattern))
                    {
                        filteredList.add(model);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            returnList.clear();
            returnList.addAll((List) results.values);
            notifyDataSetChanged();


        }
    };



}
