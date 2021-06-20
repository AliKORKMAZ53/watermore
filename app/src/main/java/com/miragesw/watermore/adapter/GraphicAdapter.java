package com.miragesw.watermore.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miragesw.watermore.R;
import com.miragesw.watermore.utils.MyAxisFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.LineData;


import java.util.ArrayList;
import java.util.List;

public class GraphicAdapter extends RecyclerView.Adapter<GraphicAdapter.MyViewHolder>{
    private Context context;
    private List<LineData> lineDataList=new ArrayList<>();
    List<Description> descriptions=new ArrayList<>();
    ArrayList<String> titleText=new ArrayList<>();
    LimitLine bmiUpperLimitLine;
    LimitLine bmiDownLimitLine;

    public void setLineDataList(List<LineData> lineDataList) throws NegativeArraySizeException{
        this.lineDataList = lineDataList;
    }


    public GraphicAdapter(Context context, Integer[] bmiLimits){
        this.context=context;
        titleText.add(context.getResources().getString(R.string.drunktime));
        titleText.add(context.getResources().getString(R.string.weighttime));
        titleText.add(context.getResources().getString(R.string.bmitime));
        Description descriptionWeight=new Description();
        Description descriptionDrunk=new Description();
        Description descriptionBmi=new Description();
        descriptionWeight.setText(context.getResources().getString(R.string.descriptionWeight));//
        descriptionDrunk.setText(context.getResources().getString(R.string.descriptionDrunk));//These two might exchange
        descriptionBmi.setText(context.getResources().getString(R.string.descriptionbmi));
        descriptionWeight.setTextColor(Color.WHITE);
        descriptionDrunk.setTextColor(Color.WHITE);
        descriptionBmi.setTextColor(Color.WHITE);
        descriptions.add(descriptionWeight);
        descriptions.add(descriptionDrunk);
        descriptions.add(descriptionBmi);
        bmiUpperLimitLine =new LimitLine(bmiLimits[1],context.getResources().getString(R.string.bmiuplimit));
        bmiDownLimitLine= new LimitLine(bmiLimits[0],context.getResources().getString(R.string.bmidownlimit));
        bmiUpperLimitLine.setLineWidth(5);
        bmiUpperLimitLine.setTextColor(Color.RED);
        bmiDownLimitLine.setLineWidth(5);
        bmiDownLimitLine.setTextColor(Color.RED);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.graph_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) throws NegativeArraySizeException{
        holder.lineChart.setData(lineDataList.get(position));
        holder.lineChart.setDescription(descriptions.get(position));
        holder.titleText.setText(titleText.get(position));
        holder.titleText.setTextColor(Color.WHITE);
        holder.lineChart.setBackgroundColor(Color.TRANSPARENT);
        holder.lineChart.getXAxis().setTextColor(Color.WHITE);
        //holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        holder.lineChart.getXAxis().setValueFormatter(new MyAxisFormatter());
        holder.lineChart.getXAxis().setGranularity(1);
        //holder.lineChart.getXAxis().setAxisMaximum();
        holder.lineChart.getXAxis().setLabelCount(lineDataList.get(position).getDataSets().get(0).getEntryCount(),true);
        // holder.lineChart.getXAxis().mAxisRange=6;//will be tested

        holder.lineChart.getAxisLeft().setTextColor(Color.WHITE);
        holder.lineChart.getLegend().setTextColor(Color.WHITE);
        //holder.lineChart.getLegend().setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);
        holder.lineChart.setNoDataTextColor(Color.WHITE);
        if(position==2){
            holder.lineChart.getAxisLeft().addLimitLine(bmiUpperLimitLine);
            holder.lineChart.getAxisLeft().addLimitLine(bmiDownLimitLine);
        }
        holder.lineChart.animateY(1000);
        holder.lineChart.invalidate();

    }

    @Override
    public int getItemCount() throws NegativeArraySizeException{
        return lineDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        LineChart lineChart;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.graphicTitleText);
            lineChart=itemView.findViewById(R.id.lineChart);

        }
    }
}
