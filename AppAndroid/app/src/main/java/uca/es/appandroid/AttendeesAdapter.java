package uca.es.appandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AttendeesAdapter extends RecyclerView.Adapter<AttendeesAdapter.MyViewHolder> {

    private ArrayList<Attendee> attendees;
    private Context context;

    public AttendeesAdapter(ArrayList<Attendee> myDataset) {
        attendees = myDataset;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView dni;
        TextView nameSurname1;
        Button btnShow;

        public MyViewHolder(View v) {
            super(v);

            dni = (TextView) v.findViewById(R.id.dni);
            nameSurname1 = (TextView) v.findViewById(R.id.nameSurname1);
            btnShow = (Button) v.findViewById(R.id.btnShow);

        }
    }

    @Override
    public AttendeesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item,
                        parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    //Asigna los valores a cada TextView
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.dni.setText(String.valueOf(attendees.get(position).getDni()));
        holder.nameSurname1.setText(attendees.get(position).getName() + " " + attendees.get(position).getSurname1());

        holder.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AttendeeActivity.class);

                String [] array = new String[8];

                array[0] = String.valueOf(attendees.get(position).getName());
                array[1] = String.valueOf(attendees.get(position).getSurname1());
                array[2] = String.valueOf(attendees.get(position).getDni());
                array[3] = String.valueOf(attendees.get(position).getBirthdate());
                array[4] = String.valueOf(attendees.get(position).getRegDate());
                array[5] = String.valueOf(attendees.get(position).getEmail());
                array[6] = String.valueOf(attendees.get(position).getPhone());
                array[7] = String.valueOf(attendees.get(position).getId());

                Bundle b = new Bundle();
                b.putStringArray("dataAttendee", array);
                intent.putExtras(b);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return attendees.size();
    }




}
