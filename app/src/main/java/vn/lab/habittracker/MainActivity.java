package vn.lab.habittracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mRcvAdapter;
    public static ArrayList<HabitMeta> habitList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        habitList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.habitListView);

        for (int i = 0 ; i < 7 ; i++){
            habitList.add(new HabitMeta("Tên hoạt động 1",21));
        }
        mRcvAdapter = new RecyclerViewAdapter(habitList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRcvAdapter);
    }
}

class HabitMeta {
    String habitName;
    int habitCompletedDay;
    public HabitMeta (String habitName , int habitCompletedDay){
        this.habitName = habitName;
        this.habitCompletedDay = habitCompletedDay;
    }
}

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{
    private ArrayList<HabitMeta> data;

    public RecyclerViewAdapter(ArrayList<HabitMeta> data) {
        this.data = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.habit_list, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.habitName.setText(data.get(position).habitName);
        holder.daysCompleted.setText(String.valueOf(data.get(position).habitCompletedDay));
        holder.btnDelete.setId(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HabitDetail.class);
                intent.putExtra("habitIndex", String.valueOf(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView habitName;
        TextView daysCompleted;
        Button btnDelete;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            habitName = (TextView) itemView.findViewById(R.id.habitName);
            daysCompleted = (TextView) itemView.findViewById(R.id.daysCompleted);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
        }
    }
}