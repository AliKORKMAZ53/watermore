
package com.example.watermore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.watermore.adapter.GraphicAdapter;
import com.example.watermore.viewmodel.DbViewModel;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.Utils;

import org.threeten.bp.DayOfWeek;

import java.util.ArrayList;
import java.util.List;

public class GraphicActivity extends AppCompatActivity {


    List<Entry> entries,entries2,entries3,entries4,entries5,entries6;
    List<LineData> listLineData=new ArrayList<>();
    List<LineData> listLineEmptyData=new ArrayList<>();
    DbViewModel dbViewModel;

    RecyclerView recyclerView;
    GraphicAdapter graphicAdapter;
    int age=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) throws NegativeArraySizeException{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);
        recyclerView=findViewById(R.id.recyclerView);

        Intent intent=getIntent();
        age=intent.getIntExtra("age",0);
        Utils.init(getApplicationContext());
        dbViewModel=new DbViewModel(getApplication());
        dbViewModel.getLastSevenRecords().observe(this,responses->{
            if(responses!=null){
                listLineData=new ArrayList<>();
                entries=new ArrayList<>();
                entries2=new ArrayList<>();
                entries3=new ArrayList<>();
                entries4=new ArrayList<>();
                entries5=new ArrayList<>();
                entries6=new ArrayList<>();
                if(responses.size()>1){
                    age = responses.get(responses.size()-1).age;
                }else if(responses.size()==1){
                    age = responses.get(0).age;
                }

            for (int iv=responses.size()-1;iv>=0;iv--) {
                entries.add(new Entry(responses.get(iv).date.getDayOfMonth(),responses.get(iv).weight));
                entries2.add(new Entry(responses.get(iv).date.getDayOfMonth(),responses.get(iv).drunk));
                entries3.add(new Entry(responses.get(iv).date.getDayOfMonth(),responses.get(iv).idealminweight));
                entries4.add(new Entry(responses.get(iv).date.getDayOfMonth(),responses.get(iv).idealmaxweight));
                entries5.add(new Entry(responses.get(iv).date.getDayOfMonth(), (float) (responses.get(iv).goal)*1000));
                entries6.add(new Entry(responses.get(iv).date.getDayOfMonth(), (int) responses.get(iv).bmi));

                }

            
            LineData drunklineData=new LineData();
            LineData minmaxWeightlineData=new LineData();
            LineData bmiData=new LineData();

            LineDataSet drunk=new LineDataSet(entries2,"Drunk");
            LineDataSet goal=new LineDataSet(entries5,"Goal");
            LineDataSet weg=new LineDataSet(entries,"Weight");
            LineDataSet minweg=new LineDataSet(entries3,"Minimum Weight");
            LineDataSet maxweg=new LineDataSet(entries4,"Maximum Weight");
            LineDataSet bmi=new LineDataSet(entries6,"Body Mass Index");

                bmi.setValueTextSize(13);
                bmi.setValueTextColor(Color.CYAN);
                bmi.setColor(Color.CYAN);
                bmi.setCircleColor(Color.BLACK);
                bmi.setCircleHoleColor(Color.RED);


                drunk.setValueTextSize(12);
                drunk.setValueTextColor(Color.CYAN);
                drunk.setColor(Color.CYAN);
                drunk.setCircleColor(Color.BLACK);
                drunk.setCircleHoleColor(Color.RED);


            goal.setValueTextSize(10);
            goal.setColor(Color.RED);
            goal.setValueTextColor(Color.RED);

            weg.setValueTextSize(12);
            weg.setValueTextColor(Color.CYAN);
            weg.setColor(Color.CYAN);
            weg.setCircleColor(Color.BLACK);
            weg.setCircleHoleColor(Color.RED);

            minweg.setValueTextSize(10);
            minweg.setColor(Color.RED);
            minweg.setValueTextColor(Color.RED);

            maxweg.setValueTextSize(10);
            maxweg.setColor(Color.RED);
            maxweg.setValueTextColor(Color.RED);

            bmiData.addDataSet(bmi);

            drunklineData.addDataSet(drunk);
            drunklineData.addDataSet(goal);

            minmaxWeightlineData.addDataSet(weg);
            minmaxWeightlineData.addDataSet(minweg);
            minmaxWeightlineData.addDataSet(maxweg);

              //  if(responses.size()==entries.size()){
                    listLineData.add(drunklineData);
                    listLineData.add(minmaxWeightlineData);
                    listLineData.add(bmiData);
                    graphicAdapter.setLineDataList(listLineData);
                    graphicAdapter.notifyDataSetChanged();

             //   }


    }


        });

        initRecyclerView();
    }

    private void initRecyclerView() throws NegativeArraySizeException{
        if(graphicAdapter==null){
            graphicAdapter=new GraphicAdapter(GraphicActivity.this, calculateBmiLimits(age));
            graphicAdapter.setLineDataList(listLineEmptyData);//for nullablity at beginning
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(graphicAdapter);
        }else{
            graphicAdapter.notifyDataSetChanged();
        }
    }
    public Integer[] calculateBmiLimits(int age){
        Integer[] a;
        if(age<25&&age>18){
            a= new Integer[]{19, 24};
            return a;
        }else if(35>age&&age>24){
            a= new Integer[]{20, 25};
            return a;
        }else if(45>age&&age>34){
            a= new Integer[]{21, 26};
            return a;
        }else if(55>age&&age>44){
            a= new Integer[]{22, 27};
            return a;
        }else if(65>age&&age>54){
            a= new Integer[]{23, 28};
            return a;
        }else if(age>64){
            a= new Integer[]{24, 29};
            return a;
        }else{
            Log.d("AGEE-IF","ELSE DUSTUK- yaş 13-18 ise limit koymaz");
            a= new Integer[]{0, 0};
            return a;
        }

    }

}