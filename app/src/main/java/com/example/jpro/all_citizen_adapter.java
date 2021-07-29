package com.example.jpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class all_citizen_adapter extends RecyclerView.Adapter<all_citizen_adapter.all_citizen_adapterVh> implements Filterable {
    private Context context;
    private List<IdData> allcitizenList;
    private List<IdData> getallCitizenListFiltered;
    private allselectedCitizen allselectedcitizen;

    public all_citizen_adapter(List<IdData> all_citizen_list, all_citizen_adapter.allselectedCitizen allselectedcitizen){
        this.allcitizenList = all_citizen_list;
        this.getallCitizenListFiltered=all_citizen_list;
        this.allselectedcitizen = allselectedcitizen;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public all_citizen_adapter.all_citizen_adapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new all_citizen_adapterVh(LayoutInflater.from(context).inflate(R.layout.row_users, null));
    }

    @Override
    public void onBindViewHolder(@NonNull all_citizen_adapter.all_citizen_adapterVh holder, int position) {
        IdData userModel = allcitizenList.get(position);
        String username = userModel.getRefnum();
//        String surname = userModel.getLastname();
        String prefix = userModel.getRefnum().substring(0,1).toUpperCase();
        holder.tvusername.setText(username);
//        holder.tvsurname.setText(surname);
        holder.tvprefix.setText(prefix);

    }

    @Override
    public int getItemCount() {
        return allcitizenList.size();
    }

    public interface allselectedCitizen{
       void allselectedcitizen(IdData idData);
    }

    public class all_citizen_adapterVh extends RecyclerView.ViewHolder{
        TextView tvprefix;
        TextView tvusername;
        TextView tvsurname;
        ImageView imgicon;

        public all_citizen_adapterVh(@NonNull View inflate) {
            super(inflate);
            tvprefix = itemView.findViewById(R.id.prefix);
            tvusername = itemView.findViewById(R.id.username);
            tvsurname = itemView.findViewById(R.id.lname);
            imgicon = itemView.findViewById(R.id.imageview);

            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allselectedcitizen.allselectedcitizen(allcitizenList.get(getAdapterPosition()));
                }
            });
        }
    }
}
