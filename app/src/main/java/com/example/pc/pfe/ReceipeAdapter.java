package com.example.pc.pfe;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pc on 25/05/2018.
 */

public class ReceipeAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{
    private List<ReceipeListContent> contenusList;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titre;

        public MyViewHolder(View view) {
            super(view);
            titre = (TextView) view.findViewById(R.id.receipeList);
           // titre.setTextSize(12);


        }
    }


    public ReceipeAdapter(List<ReceipeListContent> contenusList) {
        this.contenusList = contenusList;
    }

    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       TextView titre;
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tracking_list_content_layout, parent, false);

        return new ListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListAdapter.MyViewHolder holder, int position) {
        ReceipeListContent contenu = contenusList.get(position);
        holder.titre.setText(contenu.getReceipeName());

    }

    @Override
    public int getItemCount() {
        return contenusList.size();
    }



}
