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

public class citizen_adapter extends RecyclerView.Adapter<citizen_adapter.citizen_adapterVh> implements Filterable {

    private Context context;
    private List<CitizenDataClass> citizenList;
    private List<CitizenDataClass> getCitizenListFiltered;
    private selectedCitizen selectedcitizen;

    public citizen_adapter(List<CitizenDataClass> citizenList, citizen_adapter.selectedCitizen selectedcitizen) {
        this.citizenList = citizenList;
        this.getCitizenListFiltered=citizenList;
        this.selectedcitizen = selectedcitizen;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public citizen_adapter.citizen_adapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       context = parent.getContext();
       return new citizen_adapterVh(LayoutInflater.from(context).inflate(R.layout.row_users,null));
    }

    @Override
    public void onBindViewHolder(@NonNull citizen_adapter.citizen_adapterVh holder, int position) {
        CitizenDataClass userModel = citizenList.get(position);
        String username = userModel.getNames();
//        String surname = userModel.getLastname();
        String prefix = userModel.getNames().substring(0,1).toUpperCase();

        holder.tvusername.setText(username);
//        holder.tvsurname.setText(surname);
        holder.tvprefix.setText(prefix);

    }

    @Override
    public int getItemCount() {
        return citizenList.size();
    }


    public interface selectedCitizen {
        void selectedcitizen(CitizenDataClass citizenDataClass);
    }


    public class citizen_adapterVh extends RecyclerView.ViewHolder{
        TextView tvprefix;
        TextView tvusername;
        TextView tvsurname;
        ImageView imgicon;

        public citizen_adapterVh(View inflate) {
            super(inflate);
            tvprefix = itemView.findViewById(R.id.prefix);
            tvusername = itemView.findViewById(R.id.username);
            tvsurname = itemView.findViewById(R.id.lname);
            imgicon = itemView.findViewById(R.id.imageview);

            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedcitizen.selectedcitizen(citizenList.get(getAdapterPosition()));
                }
            });
        }
    }
}

