package vn.lab.habittracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HabitDetail extends AppCompatActivity {
    int[] buttonIDs = new int[] {R.id.d1, R.id.d2, R.id.d3,R.id.d4, R.id.d5, R.id.d6, R.id.d7,R.id.d8 , R.id.d9 ,R.id.d10 , R.id.d11 ,R.id.d12 ,R.id.d13 ,R.id.d14 , R.id.d15 ,R.id.d16 ,R.id.d17 ,R.id.d18 ,R.id.d19 ,R.id.d20 ,R.id.d21 };
    ArrayList<HabitMeta> habitList;
    int habitIndex;
    TextView habitName;
    TextView habitCompletedDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_progress);
        habitList = MainActivity.habitList;
        Intent currentIntent = getIntent();
        habitIndex = Integer.parseInt(currentIntent.getStringExtra("habitIndex"));
        int habitCompletedDays = habitList.get(habitIndex).habitCompletedDay;
        for (int i = 0 ; i < 21 ; i++) {
            Button tempButton = findViewById(buttonIDs[i]);
            tempButton.setText("Ngày " + String.valueOf(i+1));

        }

        for (int i = 0 ; i < habitCompletedDays ; i++) {
            Button tempButton = findViewById(buttonIDs[i]);
            tempButton.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));
        }

        habitName = findViewById(R.id.habitName);
        habitName.setText(habitList.get(habitIndex).habitName);
        habitCompletedDay = findViewById(R.id.habitCompletedDay);
        habitCompletedDay.setText(String.valueOf(habitList.get(habitIndex).habitCompletedDay) + " days completed");



    }


    public void changeHabitDay(View view) {

        String selectedDay = getResources().getResourceEntryName(view.getId());
        int day = Integer.parseInt(selectedDay.substring(1));
        for (int i = 0 ; i < 21 ; i++){
            Button tempButton = findViewById(buttonIDs[i]);
            tempButton.setBackgroundColor(getResources().getColor(R.color.material_on_surface_emphasis_medium));
        }


        for (int i = 0 ; i < day ; i++){
            Button tempButton = findViewById(buttonIDs[i]);
            tempButton.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));
        }

        HabitMeta newHabit = new HabitMeta(habitList.get(habitIndex).habitName,day);
        habitList.set(habitIndex,newHabit);

        TextView habitName = findViewById(R.id.habitName);
        TextView habitDay = findViewById(R.id.habitCompletedDay);
        habitName.setText(habitList.get(habitIndex).habitName);
        habitCompletedDay.setText(String.valueOf(habitList.get(habitIndex).habitCompletedDay) + " days completed");

    }
}
