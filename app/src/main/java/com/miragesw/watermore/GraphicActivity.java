
package com.miragesw.watermore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.miragesw.watermore.adapter.GraphicAdapter;
import com.miragesw.watermore.viewmodel.DbViewModel;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Utils;

import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;

import java.util.List;
import java.util.Locale;

public class GraphicActivity extends AppCompatActivity {


    List<Entry> entries,entries2,entries3,entries4,entries5,entries6;
    List<LineData> listLineData=new ArrayList<>();
    List<LineData> listLineEmptyData=new ArrayList<>();
    DbViewModel dbViewModel;
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss OOOO yyyy", Locale.ROOT);


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
                long timeInMilliseconds = OffsetDateTime.parse(responses.get(iv).date.format(formatter), formatter)
                        .toInstant()
                        .toEpochMilli();
                entries.add(new Entry(timeInMilliseconds,responses.get(iv).weight));
                entries2.add(new Entry(timeInMilliseconds,responses.get(iv).drunk));
                entries3.add(new Entry(timeInMilliseconds,responses.get(iv).idealminweight));
                entries4.add(new Entry(timeInMilliseconds,responses.get(iv).idealmaxweight));
                entries5.add(new Entry(timeInMilliseconds, (float) (responses.get(iv).goal)*1000));
                entries6.add(new Entry(timeInMilliseconds, (int) responses.get(iv).bmi));
                }

            
            LineData drunklineData=new LineData();
            LineData minmaxWeightlineData=new LineData();
            LineData bmiData=new LineData();

            LineDataSet drunk=new LineDataSet(entries2,getResources().getString(R.string.drunk));
            LineDataSet goal=new LineDataSet(entries5,getResources().getString(R.string.goal));
            LineDataSet weg=new LineDataSet(entries,getResources().getString(R.string.weight));
            LineDataSet minweg=new LineDataSet(entries3,getResources().getString(R.string.minweight));
            LineDataSet maxweg=new LineDataSet(entries4,getResources().getString(R.string.maxweight));
            LineDataSet bmi=new LineDataSet(entries6,getResources().getString(R.string.bmi));


                bmi.setValueTextSize(13);
                bmi.setValueTextColor(Color.GREEN);
                bmi.setColor(Color.GREEN);
                bmi.setCircleColor(Color.GREEN);
                bmi.setCircleHoleColor(Color.WHITE);


                drunk.setValueTextSize(12);
                drunk.setValueTextColor(Color.CYAN);
                drunk.setColor(Color.CYAN);
                drunk.setCircleColor(Color.CYAN);
                drunk.setCircleHoleColor(Color.WHITE);


                goal.setValueTextSize(10);
                goal.setColor(Color.GREEN);
                goal.setValueTextColor(Color.GREEN);

                weg.setValueTextSize(12);
                weg.setValueTextColor(Color.CYAN);
                weg.setColor(Color.CYAN);
                weg.setCircleColor(Color.WHITE);
                weg.setCircleHoleColor(Color.WHITE);

                minweg.setValueTextSize(10);
                minweg.setColor(Color.BLUE);
                minweg.setValueTextColor(Color.BLUE);

                maxweg.setValueTextSize(10);
                maxweg.setColor(Color.BLUE);
                maxweg.setValueTextColor(Color.BLUE);

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