package com.example.tektek.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tektek.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;
import java.util.List;

public class GraphicAdapter extends RecyclerView.Adapter<GraphicAdapter.MyViewHolder>{
    private Context context;
    private List<LineData> lineDataList;
    List<Description> descriptions=new ArrayList<>();
    ArrayList<String> descriptionsStrings=new ArrayList<>();
    ArrayList<String> titleText=new ArrayList<>();

    public void setLineDataList(List<LineData> lineDataList) {
        this.lineDataList = lineDataList;
    }



    public GraphicAdapter(Context context){
        this.context=context;
        titleText.add("Weight - Time");
        titleText.add("Water Drunk - Time");
        descriptionsStrings.add("Kilogram - Day of Month");
        descriptionsStrings.add("Milliliter - Day of Month");
        Description descriptionWeight=new Description();
        Description descriptionDrunk=new Description();
        descriptionWeight.setText(descriptionsStrings.get(0));
        descriptionDrunk.setText(descriptionsStrings.get(1));
        descriptions.add(descriptionWeight);
        descriptions.add(descriptionDrunk);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.graph_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.lineChart.setData(lineDataList.get(position));
        holder.lineChart.setDescription(descriptions.get(position));
        holder.titleText.setText(titleText.get(position));
        holder.lineChart.setBackgroundColor(Color.LTGRAY);
        holder.lineChart.animateY(1000);
        holder.lineChart.invalidate();

    }

    @Override
    public int getItemCount() {
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
