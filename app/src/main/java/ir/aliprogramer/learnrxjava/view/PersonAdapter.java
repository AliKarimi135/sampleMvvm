package ir.aliprogramer.learnrxjava.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.aliprogramer.learnrxjava.R;
import ir.aliprogramer.learnrxjava.model.Personal;
import ir.aliprogramer.learnrxjava.viewModel.MainViewModel;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    List<Personal> personalList;
    MainViewModel mainViewModel;
    public PersonAdapter(List<Personal> personals, MainViewModel mainViewModel2) {
        personalList=personals;
        mainViewModel=mainViewModel2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(personalList.get(position).getName());
        holder.family.setText(personalList.get(position).getFamily());
    }

    @Override
    public int getItemCount() {
        return personalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,family;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            family=itemView.findViewById(R.id.family);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            mainViewModel.deleteItem(getAdapterPosition());
        }
    }
}
