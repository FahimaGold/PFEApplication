package com.example.pc.pfe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pc on 30/04/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{

    private List<TrackingListContent> contenusList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titre;

        public MyViewHolder(View view) {
            super(view);
            titre = (TextView) view.findViewById(R.id.contenuList);

        }
    }


    public ListAdapter(List<TrackingListContent> contenusList) {
        this.contenusList = contenusList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tracking_list_content_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TrackingListContent contenu = contenusList.get(position);
        holder.titre.setText(contenu.getTrackingType());

    }

    @Override
    public int getItemCount() {
        return contenusList.size();
    }
}
