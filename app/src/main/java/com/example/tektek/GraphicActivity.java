
package com.example.tektek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tektek.adapter.GraphicAdapter;
import com.example.tektek.database.UserTable;
import com.example.tektek.utils.TiviTypeConverters;
import com.example.tektek.viewmodel.DbViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphicActivity extends AppCompatActivity {


    List<Entry> entries=new ArrayList<>();
    List<Entry> entries2=new ArrayList<>();
    List<Entry> entries3=new ArrayList<>();
    List<Entry> entries4=new ArrayList<>();

    List<LineData> listLineData=new ArrayList<>();
    DbViewModel dbViewModel;

    RecyclerView recyclerView;
    GraphicAdapter graphicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);
        recyclerView=findViewById(R.id.recyclerView);

        Utils.init(getApplicationContext());
        dbViewModel=new DbViewModel(getApplication());
        dbViewModel.getLastSevenRecords().observe(this,responses->{
            if(responses!=null){

            for (int iv=0;iv<responses.size();iv++) {
                entries.add(new Entry(responses.get(iv).date.getDayOfMonth(),responses.get(iv).weight));
                entries2.add(new Entry(responses.get(iv).date.getDayOfMonth(),responses.get(iv).drunk));
                entries3.add(new Entry(responses.get(iv).date.getDayOfMonth(),responses.get(iv).idealminweight));
                entries4.add(new Entry(responses.get(iv).date.getDayOfMonth(),responses.get(iv).idealmaxweight));
                Log.d("count","entryLOOP");
            }

            LineData drunklineData=new LineData();
            LineData minmaxWeightlineData=new LineData();

            LineDataSet drunk=new LineDataSet(entries2,"Drunk");
            LineDataSet weg=new LineDataSet(entries,"Weight");
            LineDataSet minweg=new LineDataSet(entries3,"Minimum Weight");
            LineDataSet maxweg=new LineDataSet(entries4,"Maximum Weight");

            drunk.setValueTextSize(11);
            drunk.setColor(Color.parseColor("#ff0000"));
            drunk.setCircleColor(Color.BLACK);
            drunk.setCircleHoleColor(Color.RED);

            weg.setValueTextSize(11);
            weg.setColor(Color.CYAN);
            weg.setCircleColor(Color.BLACK);
            weg.setCircleHoleColor(Color.RED);


            drunklineData.addDataSet(drunk);

            minmaxWeightlineData.addDataSet(weg);
            minmaxWeightlineData.addDataSet(minweg);
            minmaxWeightlineData.addDataSet(maxweg);

              //  if(responses.size()==entries.size()){
                    listLineData.add(drunklineData);
                    listLineData.add(minmaxWeightlineData);
                    graphicAdapter.setLineDataList(listLineData);
                graphicAdapter.notifyDataSetChanged();

             //   }



    }


        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        if(graphicAdapter==null){
            graphicAdapter=new GraphicAdapter(GraphicActivity.this);
            graphicAdapter.setLineDataList(listLineData);//for nullablity at beginning
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(graphicAdapter);
        }else{
            graphicAdapter.notifyDataSetChanged();
        }
    }

}