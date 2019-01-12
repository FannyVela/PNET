package uca.es.appandroid;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActividadAdapter extends BaseAdapter {

    private ArrayList<Actividad> activities;
    private Context context;

    public ActividadAdapter(ArrayList<Actividad> myDataset, Context context) {
        activities = myDataset;
        this.context = context;
    }

    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public Object getItem(int position) {
        return activities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //Aqui es donde creamos cada itemmm y asignamos los valores para cada elto de cada itemmm
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Actividad item = (Actividad) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_item, null);

        //Creamos cada elto
        ImageView time = (ImageView)convertView.findViewById(R.id.time);
        TextView title = (TextView)convertView.findViewById(R.id.title);
        TextView speaker = (TextView)convertView.findViewById(R.id.speaker);

        time.setImageResource(item.getTime());
        title.setText(item.getTitle());
        speaker.setText(item.getSpeaker());

        return convertView;
    }

}
