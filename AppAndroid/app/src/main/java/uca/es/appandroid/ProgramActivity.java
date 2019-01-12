package uca.es.appandroid;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProgramActivity extends AppCompatActivity {

    private ListView mListView;
    private ActividadAdapter mAdapter;



    private TextView txtTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProgramActivity.this, ProgramPpalActivity.class);
                startActivity(i);
            }
        });


        txtTitulo = (TextView)findViewById(R.id.txtTitle);

        Bundle bundle = this.getIntent().getExtras();
        String result = bundle.getString("day");
        int numDay = bundle.getInt("numDay");
        txtTitulo.setText(result);

        mListView = findViewById(R.id.my_list_view);

        // Creamos un ArrayList de actividades
        ArrayList<Actividad> activities = new ArrayList<Actividad>();

        activities.add(new Actividad(R.drawable.am8,"Registration", ""));

        if(numDay == 1)
        {
            activities.add(new Actividad(R.drawable.am9,"Video Motion Capture Makes Human Motion Analysis", "Prof. Yoshihiko Nakamura"));
            activities.add(new Actividad(R.drawable.am10,"The Role of Cognition in Computational Ontology", "Dr. Marco Schorlemmer"));
        } else if(numDay == 2)
        {
            activities.add(new Actividad(R.drawable.am9,"Fuzzy & Annotated Semantic Web Languages", "Prof. Hans-Jörg Kreowski"));
            activities.add(new Actividad(R.drawable.am10,"Coming soon", "Dr. Lluis Marquez"));
        } else
        {
            activities.add(new Actividad(R.drawable.am9,"Intelligent Collaborative Systems and their Application", "Prof. Patrick Doherty"));
            activities.add(new Actividad(R.drawable.am10,"Human Authentication Using Facial Cues", "Dr. Raquel Martínez-Unanue"));
        }

        activities.add(new Actividad(R.drawable.am11,"Break", ""));

        if(numDay == 1)
        {
            activities.add(new Actividad(R.drawable.am12,"Coming soon", "Dr. Vali Nazarzehi"));
        } else if(numDay == 2)
        {
            activities.add(new Actividad(R.drawable.am12,"Importance of knowledge in machine learning applications", "Dr. Jean Fred Fontaine"));
        } else
        {
            activities.add(new Actividad(R.drawable.am12,"Coming soon", "Enric Delgado Samper"));
        }

        activities.add(new Actividad(R.drawable.pm1,"Lunch", ""));

        if(numDay == 1)
        {
            activities.add(new Actividad(R.drawable.pm4,"Data Science for Efficient Building Energy Management", "Dr. Juan Gomez-Romero"));
        } else if(numDay == 2)
        {
            activities.add(new Actividad(R.drawable.pm4,"Coming soon", "Dr. Mariarosaria Taddeo"));
        } else
        {
            activities.add(new Actividad(R.drawable.pm4,"Neuromorphic computing with emerging memory devices", "Prof. Daniele Ielmini"));
        }

        activities.add(new Actividad(R.drawable.pm5,"Posters session & Sponsors exhibition", ""));
        activities.add(new Actividad(R.drawable.pm6,"Oral sessions", ""));
        activities.add(new Actividad(R.drawable.pm7,"Break", ""));
        activities.add(new Actividad(R.drawable.pm8,"Cultural Activity", ""));

        // Creamos un ActividadAdapter pasándole todos nuestras actividades
        mAdapter = new ActividadAdapter(activities, this);
        // Asociamos el adaptador al RecyclerView
        mListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home)
        {
            Intent i = new Intent(ProgramActivity.this, MainActivity.class);
            startActivity(i);
        }
        if(item.getItemId() == R.id.attendees)
        {
            Intent i = new Intent(ProgramActivity.this, AttendeesActivity.class);
            startActivity(i);
        }
        if(item.getItemId() == R.id.program)
        {
            Intent i = new Intent(ProgramActivity.this, ProgramPpalActivity.class);
            startActivity(i);
        }
        if(item.getItemId() == R.id.dates)
            Toast.makeText(this,"Dates activity", Toast.LENGTH_SHORT).show();
        if(item.getItemId() == R.id.location)
            Toast.makeText(this,"Location activity", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }


}
