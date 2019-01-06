package com.innovandoapps.library.kernel.adapters;

import android.support.v7.widget.RecyclerView;
import java.util.List;

public abstract class GenericRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    @Override
    public int getItemCount() {
        return getListObjet().size();
    }

    protected abstract List<T> getListObjet();
}
