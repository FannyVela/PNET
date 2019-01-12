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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AttendeeActivity extends AppCompatActivity {

    private TextView nameSurname,dni,birthdate,regdate,email,phone;
    private EditText editName,editSurname1,editDni,editEmail,editPhone;
    private DatePickerDialog.OnDateSetListener birthDateSetListener, inscDateSetListener;
    private TextView editBirthdate, editRegDate;
    private Button edit,delete, send;
    private String id_attendee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AttendeeActivity.this, AttendeesActivity.class);
                startActivity(i);
            }
        });


        nameSurname = (TextView)findViewById(R.id.nameSurn);
        dni = (TextView)findViewById(R.id.dataDni);
        birthdate = (TextView)findViewById(R.id.dataNacim);
        regdate = (TextView)findViewById(R.id.dataInsc);
        email = (TextView)findViewById(R.id.dataEmail);
        phone = (TextView)findViewById(R.id.dataPhone);
        edit = (Button)findViewById(R.id.button_edit);
        delete = (Button)findViewById(R.id.button_delete);

        Bundle bundle = this.getIntent().getExtras();

        final String array[] = bundle.getStringArray("dataAttendee");

        String nomApel = array[0] + " " + array[1];

        nameSurname.setText(nomApel);
        dni.setText(array[2]);
        birthdate.setText(array[3]);
        regdate.setText(array[4]);
        email.setText(array[5]);
        phone.setText(array[6]);
        id_attendee = array[7];

        edit.setOnClickListener(new View.OnClickListener() {
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

                editName = (EditText)findViewById(R.id.EditTextName);
                editSurname1 = (EditText)findViewById(R.id.EditTextSurname1);
                editDni = (EditText)findViewById(R.id.EditTextDni);
                editBirthdate = (TextView)findViewById(R.id.txtFchNacim);
                editRegDate = (TextView)findViewById(R.id.txtFchInsc);
                editEmail = (EditText)findViewById(R.id.EditTextEmail);
                editPhone = (EditText)findViewById(R.id.EditPhone);

                editName.setText(array[0], TextView.BufferType.EDITABLE);
                editSurname1.setText(array[1], TextView.BufferType.EDITABLE);
                editDni.setText(array[2], TextView.BufferType.EDITABLE);
                editBirthdate.setText(array[3], TextView.BufferType.EDITABLE);
                editRegDate.setText(array[4], TextView.BufferType.EDITABLE);
                editEmail.setText(array[5], TextView.BufferType.EDITABLE);
                editPhone.setText(array[6], TextView.BufferType.EDITABLE);

                editBirthdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(
                                AttendeeActivity.this,
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
                        editBirthdate.setText(date);
                    }
                };

                editRegDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(
                                AttendeeActivity.this,
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
                        editRegDate.setText(date);
                    }
                };

                send = (Button)findViewById(R.id.send);

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(editName.length() == 0)
                        {
                            editName.setError("Introduzca un nombre");
                        } else if(editSurname1.length() == 0)
                        {
                            editSurname1.setError("Introduzca el apellido");
                        } else if(editDni.length() == 0)
                        {
                            editDni.setError("Introduzca el dni");
                        } else if(editBirthdate.length() == 0)
                        {
                            editBirthdate.setError("Introduzca fecha de nacimiento");
                        }  else if(editRegDate.length() == 0)
                        {
                            editRegDate.setError("Introduzca fecha de inscripción");
                        } else if(editEmail.length() == 0)
                        {
                            editEmail.setError("Introduzca un e-mail");
                        } else if(editPhone.length() == 0)
                        {
                            editPhone.setError("Introduzca un teléfono de contacto");
                        } else
                        {
                            new putAttendee().execute();//Ejecutamos la tarea de put
                        }
                    }
                });


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new deleteAttendee().execute();
            }
        });

    }

    public class deleteAttendee extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("http://192.168.1.108:8080/attendees/" + id_attendee)
                    .delete()
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
                Toast.makeText(AttendeeActivity.this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(AttendeeActivity.this, "Error al eliminar asistente", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(AttendeeActivity.this, MainActivity.class);

            startActivity(i);

        }
    }

    public class putAttendee extends AsyncTask<Void, Void, Boolean> {

        public final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        //Preguntar si es igual que el post
        @Override
        protected Boolean doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();

            JSONObject data = new JSONObject();

            try{
                data.put("_id",id_attendee);
                data.put("name", editName.getText().toString());
                data.put("surname1", editSurname1.getText().toString());
                data.put("dni", editDni.getText().toString());
                data.put("email", editEmail.getText().toString());
                data.put("phone", editPhone.getText().toString());
                data.put("birthdate", editBirthdate.getText().toString());
                data.put("regDate", editRegDate.getText().toString());

            } catch(JSONException e)
            {
                e.printStackTrace();
            }

            RequestBody body = RequestBody.create(JSON , data.toString());

            Request request = new Request.Builder()
                    .url("http://192.168.1.108:8080/attendees/" + id_attendee)
                    .put(body)
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
                Toast.makeText(AttendeeActivity.this, "Asistente actualizado correctamente", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(AttendeeActivity.this, "Error al actualizar asistente", Toast.LENGTH_SHORT).show();

            //Preguntar como refrescar y que se actualicen los datos
            //Ver si se puede pausar para que le de tiempo a leer el mensaje

            Intent i = new Intent(AttendeeActivity.this, AttendeesActivity.class);

            startActivity(i);

        }

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
            Intent i = new Intent(AttendeeActivity.this, MainActivity.class);
            startActivity(i);
        }

        if(item.getItemId() == R.id.attendees)
        {
            Intent i = new Intent(AttendeeActivity.this, AttendeesActivity.class);
            startActivity(i);
        }
        if(item.getItemId() == R.id.program)
        {
                Intent i = new Intent(AttendeeActivity.this, ProgramPpalActivity.class);
                startActivity(i);
        }
        if(item.getItemId() == R.id.dates)
            Toast.makeText(this,"Login activity", Toast.LENGTH_SHORT).show();
        if(item.getItemId() == R.id.location)
            Toast.makeText(this,"Logout activity", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);

    }
}
