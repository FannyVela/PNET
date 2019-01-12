package uca.es.appandroid;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AttendeesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //ArrayList de asistentes
    private ArrayList<Attendee> attendees = new ArrayList<Attendee>();
    private Button add, add2;

    private EditText name,surname1,dni,email,phone;
    private TextView birthdate, regDate;

    private DatePickerDialog.OnDateSetListener birthDateSetListener, inscDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendees);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Referenciamos al RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // Mejoramos rendimiento con esta configuración
        mRecyclerView.setHasFixedSize(true);

        // Creamos un LinearLayoutManager para gestionar el itemmm.xmlml creado antes
        mLayoutManager = new LinearLayoutManager(this);

        // Lo asociamos al RecyclerView
        mRecyclerView.setLayoutManager(mLayoutManager);

        new GetAllAttendee().execute(); //Para ejecutar la tarea asincrona

        add = (Button)findViewById(R.id.btnAdd);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.form_add);

                Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
                toolbar2.setNavigationIcon(R.drawable.ic_action_name);
                setSupportActionBar(toolbar2);

                toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        startActivity(getIntent());
                    }
                });

                name = (EditText)findViewById(R.id.EditTextName);
                surname1 = (EditText)findViewById(R.id.EditTextSurname1);
                dni = (EditText)findViewById(R.id.EditTextDni);
                birthdate = (TextView) findViewById(R.id.txtFchNacim);
                regDate = (TextView)findViewById(R.id.txtFchInsc);
                email = (EditText)findViewById(R.id.EditTextEmail);
                phone = (EditText)findViewById(R.id.EditPhone);

                birthdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(
                                AttendeesActivity.this,
                                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                birthDateSetListener,
                                year,month,day
                        );

                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }
                });

                birthDateSetListener= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month+=1;
                        String date = dayOfMonth + "-" + month + "-" + year;
                        birthdate.setText(date);
                    }
                };

                regDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(
                                AttendeesActivity.this,
                                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                inscDateSetListener,
                                year,month,day
                        );

                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }
                });

                inscDateSetListener= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month+=1;
                        String date = dayOfMonth + "-" + month + "-" + year;
                        regDate.setText(date);
                    }
                };

                add2 = (Button)findViewById(R.id.send);

                add2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(name.length() == 0)
                        {
                            name.setError("Introduzca un nombre");
                        } else if (surname1.length() == 0)
                        {
                            surname1.setError("Introduzca un apellido");
                        } else if(dni.length() == 0)
                        {
                            dni.setError("Introduzca el DNI");
                        } else if(birthdate.length() == 0)
                        {
                            birthdate.setError("Introduzca la fecha de nacimiento");
                        } else if(regDate.length() == 0)
                        {
                            regDate.setError("Introduzca la fecha de inscripción");
                        } else if(email.length() == 0)
                        {
                            email.setError("Introduzca un e-mail");
                        } else if(phone.length() == 0)
                        {
                            phone.setError("Introduzca un teléfono de contacto");
                        } else //Si todos los campos estan rellenos mandamos la petición
                        {
                            new PostAttendee().execute(); //Ejecutamos la tarea de post
                        }
                    }
                });
            }

        });

    }

    //Para el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.home)
        {
            Intent i = new Intent(AttendeesActivity.this, MainActivity.class);
            startActivity(i);
        }
        if(item.getItemId() == R.id.attendees)
        {
            //Recargar actividad
            finish();
            startActivity(getIntent());
        }
        if(item.getItemId() == R.id.program)
        {
            Intent i = new Intent(AttendeesActivity.this, ProgramPpalActivity.class);
            startActivity(i);
        }
        if(item.getItemId() == R.id.dates)
            Toast.makeText(this,"Login activity", Toast.LENGTH_SHORT).show();
        if(item.getItemId() == R.id.location)
            Toast.makeText(this,"Logout activity", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    //Mostrar asistentes
    public class GetAllAttendee extends AsyncTask<Void, Void, String> {

        String a;

        @Override
        protected String doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://192.168.1.108:8080/attendees")
                    .build();

            try{

                Response res = client.newCall(request).execute();
                return res.body().string();
            } catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String results) {
            JSONArray respJSON = null;
            try {
                if (results != null) {
                    respJSON = new JSONArray(results);

                    for(int i = 0; i < respJSON.length(); i++)
                    {
                        String id = respJSON.getJSONObject(i).getString("_id");
                        String name =  respJSON.getJSONObject(i).getString("name");
                        String surname1 = respJSON.getJSONObject(i).getString("surname1");
                        String dni = respJSON.getJSONObject(i).getString("dni");
                        String birthDate = respJSON.getJSONObject(i).getString("birthdate");
                        String regDate = respJSON.getJSONObject(i).getString("regDate");
                        String email = respJSON.getJSONObject(i).getString("email");
                        int tlf = respJSON.getJSONObject(i).getInt("phone");

                        attendees.add(new Attendee(id,name,surname1,dni, birthDate,regDate,email,tlf));
                    }

                    mAdapter = new AttendeesAdapter(attendees);
                    mRecyclerView.setAdapter(mAdapter);
                }
            } catch(JSONException e)
            {
                e.printStackTrace();
            }

        }
    }

    public class PostAttendee extends AsyncTask<Void, Void, Boolean> {

        public final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        @Override
        protected Boolean doInBackground(Void... params) {

            OkHttpClient client = new OkHttpClient();

            JSONObject postdata = new JSONObject();

            try{
                postdata.put("name",name.getText().toString());
                postdata.put("surname1", surname1.getText().toString());
                postdata.put("dni",dni.getText().toString());
                postdata.put("email",email.getText().toString());
                postdata.put("phone",phone.getText().toString());
                postdata.put("birthdate",birthdate.getText().toString());
                postdata.put("regDate",regDate.getText().toString());

            } catch (JSONException e)
            {
                e.printStackTrace();
            }

            RequestBody body = RequestBody.create(JSON , postdata.toString());
            Request request = new Request.Builder()
                    .url("http://192.168.1.108:8080/attendees")
                    .post(body)
                    .build();

            try{

                Response res = client.newCall(request).execute();
                return true;
            } catch (IOException e){
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {

            if(result)
                Toast.makeText(AttendeesActivity.this, "Asistente añadido con éxito", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(AttendeesActivity.this, "Error al añadir asistente", Toast.LENGTH_SHORT).show();

            //Recargar actividad
            finish();
            startActivity(getIntent());

        }
    }



}