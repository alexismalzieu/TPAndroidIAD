package com.tards.imt.mypointofinterest.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tards.imt.mypointofinterest.R;
import com.tards.imt.mypointofinterest.model.Poi;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PoiAdapter extends RecyclerView.Adapter<PoiAdapter.ViewHolder>{

    private List<Poi> mPoiList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        public TextView mDescription;
        public TextView mDate;
        public ImageView mLocation;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("ViewHolder():","called");

            mTitle = itemView.findViewById(R.id.titleTv);
            mDescription = itemView.findViewById(R.id.descriptionTv);
            mDate = itemView.findViewById(R.id.dateTv);
            mLocation = itemView.findViewById(R.id.locationIv);

        }

    }

    public PoiAdapter(List<Poi> poiList) {
        this.mPoiList = poiList;
    }

    @NonNull
    @Override
    public PoiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poi_recycler_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PoiAdapter.ViewHolder holder, int position) {

        Log.d("onBindViewHolder():","called");

        holder.mTitle.setText(mPoiList.get(position).getLabel());
        holder.mDescription.setText(mPoiList.get(position).getDescription());

        holder.mDate.setText(mPoiList.get(position).getDateString());

    }

    @Override
    public int getItemCount() {
        return mPoiList.size();
    }

}
