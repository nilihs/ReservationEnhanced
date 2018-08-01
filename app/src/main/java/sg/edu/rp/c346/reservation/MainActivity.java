package sg.edu.rp.c346.reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName, etNumber, etSize, etDay, etTime;
    CheckBox smokeCB;
    Button btnConfirm, btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.nameInput);
        etNumber = findViewById(R.id.numberInput);
        etSize = findViewById(R.id.sizeInput);
        smokeCB = findViewById(R.id.smokingCB);
        btnConfirm = findViewById(R.id.confirmButton);
        btnReset = findViewById(R.id.resetButton);
        etDay = findViewById(R.id.editTextDay);
        etTime = findViewById(R.id.editTextTime);

        etDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        etDay.setText("Date: " + dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                    }
                };

                Calendar calendar = Calendar.getInstance();
                int cYear = calendar.get(Calendar.YEAR);
                int cMonth = calendar.get(Calendar.MONTH);
                int cDay = calendar.get(Calendar.DAY_OF_MONTH);


                // Create the Date Picker Dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this, myDateListener, cYear, cMonth, cDay);
                myDateDialog.show();

            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        etTime.setText("Time: " + hourOfDay + ":" + minute);

                    }
                };

                Calendar calendar = Calendar.getInstance();
                int cHour = calendar.get(Calendar.HOUR);
                int cMinute = calendar.get(Calendar.MINUTE);

                // Create the Time Picker Dialog
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeListener, cHour, cMinute, true);
                myTimeDialog.show();

            }
        });



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getName = etName.getText().toString().trim();
                String getNumber = etNumber.getText().toString().trim();
                String getSize = etSize.getText().toString().trim();
                String getDate = etDay.getText().toString().trim();
                String getTime = etTime.getText().toString().trim();

                Calendar newDate = Calendar.getInstance();
                Calendar now = Calendar.getInstance();

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                myBuilder.setTitle("Confirm Your Order");
                myBuilder.setCancelable(false);

                if(getName.length() != 0 && getNumber.length() != 0 && getSize.length() != 0 ) {

                    if(now.after(newDate)) {
                        Toast.makeText(MainActivity.this,"Date/Time cannot be in the past",Toast.LENGTH_LONG).show();
                    }

                    else {

                        if(smokeCB.isChecked()) {

                            myBuilder.setMessage("New Reservation \n " +
                                    "Name: " + getName + "\n" +
                                    "Smoking: Yes \n" +
                                    "Size: " + getSize + "\n" +
                                    "Date: " + getDate + "\n" +
                                    "Time: " + getTime);

                        }
                        else {

                            myBuilder.setMessage("New Reservation \n " +
                                    "Name: " + getName + "\n" +
                                    "Smoking: No \n" +
                                    "Size: " + getSize + "\n" +
                                    "Date: " + getDate + "\n" +
                                    "Time: " + getTime);

                        }
                    }


                }

                else {
                    Toast.makeText(MainActivity.this,"Please input all the fields",Toast.LENGTH_LONG).show();
                }

                myBuilder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(MainActivity.this, "Booking Confirmed", Toast.LENGTH_LONG).show();

                    }
                });

                myBuilder.setNegativeButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();




            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smokeCB.setChecked(false);

                etName.setText(" ");
                etName.setHint("Enter name");
                etNumber.setText(" ");
                etNumber.setHint("Enter mobile number");
                etSize.setText(" ");
                etSize.setHint("Enter size of group");


            }
        });

    }
}
