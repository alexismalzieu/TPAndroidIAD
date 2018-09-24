package com.tards.imt.mypointofinterest.list;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tards.imt.mypointofinterest.PoiListActivity;
import com.tards.imt.mypointofinterest.R;
import com.tards.imt.mypointofinterest.model.Poi;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PoiAdapter extends RecyclerView.Adapter<PoiAdapter.ViewHolder>{

    private Context mContext;
    private List<Poi> mPoiList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        public TextView mDescription;
        public TextView mDate;
        public ImageView mLocation;
        public View mView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("ViewHolder():","called");

            mTitle = itemView.findViewById(R.id.titleTv);
            mDescription = itemView.findViewById(R.id.descriptionTv);
            mDate = itemView.findViewById(R.id.dateTv);
            mLocation = itemView.findViewById(R.id.locationIv);
            mView = itemView.findViewById(R.id.view);

        }

    }

    public PoiAdapter(Context context, List<Poi> poiList) {
        this.mContext = context;
        this.mPoiList = poiList;
    }

    @NonNull
    @Override
    public PoiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poi_recycler_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PoiAdapter.ViewHolder holder, final int position) {

        Log.d("onBindViewHolder():","called");

        holder.mTitle.setText(mPoiList.get(position).getLabel());
        holder.mDescription.setText(mPoiList.get(position).getDescription());

        holder.mDate.setText(mPoiList.get(position).getDateString());

        holder.mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("POI onClick ","POI: " + mPoiList.get(position).getLabel());

                String lat = mPoiList.get(position).getLatitude();
                String lng = mPoiList.get(position).getLongitude();

                System.out.println("Lat: " + lat);
                System.out.println("Lng: " + lng);

                String uri = "geo:<" + lat +">,<" + lng +">?q=<" + lat +">,<" + lng +">";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                mContext.startActivity(intent);
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if(mContext instanceof  PoiListActivity){

                    ((PoiListActivity)mContext).startEditActivity(mPoiList.get(position));

                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPoiList.size();
    }


}
