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

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Users_adapter extends RecyclerView.Adapter<Users_adapter.Users_adapterVh> implements Filterable {

    private List<User> userList;
    private List<User> getuserListFiltered;
    private Context context;
    private SelectedUser selectedUser;

    public Users_adapter(List<User> userList, SelectedUser selectedUser) {
        this.userList = userList;
        this.getuserListFiltered=userList;
        this.selectedUser = selectedUser;
    }

    @NonNull
    @Override
    public Users_adapter.Users_adapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new Users_adapterVh(LayoutInflater.from(context).inflate(R.layout.row_users, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Users_adapter.Users_adapterVh holder, int position) {
        User userModel = userList.get(position);
        String username = userModel.getFirstname();
        String surname = userModel.getLastname();
        String prefix = userModel.getFirstname().substring(0,1).toUpperCase();

        holder.tvusername.setText(username);
        holder.tvsurname.setText(surname);
        holder.tvprefix.setText(prefix);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (charSequence==null | charSequence.length()==0){
                    filterResults.count = getuserListFiltered.size();
                    filterResults.values = getuserListFiltered;
                }else {
                    String searchCharacter = charSequence.toString().toLowerCase();
                    List<User> resultData = new ArrayList<>();
                    for (User user : getuserListFiltered){
                        if (user.getFirstname().toLowerCase().contains(searchCharacter)){
                            resultData.add(user);
                        }
                    }
                    filterResults.count=resultData.size();
                    filterResults.values = resultData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                userList = (List<User>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    public interface SelectedUser{
        void selectedUser(User user);
    }

    public class Users_adapterVh extends RecyclerView.ViewHolder {
        TextView tvprefix;
        TextView tvusername;
        TextView tvsurname;
        ImageView imgicon;
        public Users_adapterVh(@NonNull View itemView) {
            super(itemView);
            tvprefix = itemView.findViewById(R.id.prefix);
            tvusername = itemView.findViewById(R.id.username);
            tvsurname = itemView.findViewById(R.id.lname);
            imgicon = itemView.findViewById(R.id.imageview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedUser.selectedUser(userList.get(getAdapterPosition()));
                }
            });
        }
    }
}
