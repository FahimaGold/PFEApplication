package com.example.pc.pfe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pc on 26/09/2018.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contenuNote;
        TextView dateCreationNote;

        public ViewHolder(View v) {
            super(v);
            contenuNote  = (TextView) v.findViewById(R.id.itemNoteContent);
            dateCreationNote = (TextView) v.findViewById(R.id.itemNoteCreationDate);

        }
    }

    public NoteAdapter(List<Note> myDataset) {
        this.mDataset = myDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Note n = mDataset.get(position);

        //here i get an exception indicating holder is null exception

        holder.contenuNote.setText(n.getContenu());
        holder.dateCreationNote.setText(n.getDateCreation());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
