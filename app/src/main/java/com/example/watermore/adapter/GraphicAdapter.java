package com.example.watermore.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watermore.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
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
        titleText.add("Water Drunk - Time");
        titleText.add("Weight - Time");
        titleText.add("Body Mass Index - Time");
        Description descriptionWeight=new Description();
        Description descriptionDrunk=new Description();
        Description descriptionBmi=new Description();
        descriptionWeight.setText("Milliliter - Day of Month");
        descriptionDrunk.setText("Kilogram - Day of Month");
        descriptionBmi.setText("Bmi - Day of Month");
        descriptionWeight.setTextColor(Color.YELLOW);
        descriptionDrunk.setTextColor(Color.YELLOW);
        descriptionBmi.setTextColor(Color.YELLOW);
        descriptions.add(descriptionWeight);
        descriptions.add(descriptionDrunk);
        descriptions.add(descriptionBmi);
        Log.d("AGEE",Integer.toString(bmiLimits[0]));
        bmiUpperLimitLine =new LimitLine(bmiLimits[1],"Overweight");
        bmiDownLimitLine= new LimitLine(bmiLimits[0],"Underweight");
        bmiUpperLimitLine.setLineWidth(5);
        bmiUpperLimitLine.setTextColor(Color.YELLOW);
        bmiDownLimitLine.setLineWidth(5);
        bmiDownLimitLine.setTextColor(Color.YELLOW);
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
        holder.titleText.setTextColor(Color.YELLOW);
        holder.lineChart.setBackgroundColor(Color.BLACK);
        holder.lineChart.getXAxis().setTextColor(Color.YELLOW);
        //holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        holder.lineChart.getAxisLeft().setTextColor(Color.YELLOW);
        holder.lineChart.getLegend().setTextColor(Color.YELLOW);
        //holder.lineChart.getLegend().setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);
        holder.lineChart.setNoDataTextColor(Color.YELLOW);
        if(position==2){
            holder.lineChart.getAxisLeft().addLimitLine(bmiUpperLimitLine);
            holder.lineChart.getAxisLeft().addLimitLine(bmiDownLimitLine);
        }
        holder.lineChart.animateY(1000);
        holder.lineChart.invalidate();

    }

    @Override
    public int getItemCount() throws NegativeArraySizeException{
        Log.d("graphicAdapter",Integer.toString(lineDataList.size()));
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
