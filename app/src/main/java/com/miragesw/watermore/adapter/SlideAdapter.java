package com.miragesw.watermore.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager.widget.PagerAdapter;

import com.miragesw.watermore.R;
import com.miragesw.watermore.usernamesPop;

import java.util.ArrayList;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    public static MutableLiveData<Boolean> isButtonClicked=new MutableLiveData<>();

    Intent intent;
    // list of images
    public int[] lst_images = {
            R.mipmap.watermoreicon_round,
            R.drawable.ifadeler,
            R.drawable.kullanim,
            R.drawable.damlatanitim
    };
    // list of titles
    ArrayList<String> lst_title=new ArrayList<>();

    // list of descriptions
    ArrayList<String> lst_description=new ArrayList<>();

    // list of background colors
    public int[]  lst_backgroundcolor = {
            Color.rgb(0,190,255),
            Color.rgb(0,215,165),
            Color.rgb(215,205,100),
            Color.rgb(200,0,85)
    };


    public SlideAdapter(Context context) {
        this.context = context;
        lst_title.add(context.getResources().getString(R.string.title1));
        lst_title.add(context.getResources().getString(R.string.title2));
        lst_title.add(context.getResources().getString(R.string.title3));
        lst_title.add(context.getResources().getString(R.string.title4));

        lst_description.add(context.getResources().getString(R.string.description1));
        lst_description.add(context.getResources().getString(R.string.description2));
        lst_description.add(context.getResources().getString(R.string.description3));
        lst_description.add(context.getResources().getString(R.string.description4));

        isButtonClicked.setValue(false);
        intent=new Intent(context,usernamesPop.class);
    }

    @Override
    public int getCount() {
        return lst_title.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(ConstraintLayout)object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slidelinearlayout,container,false);
        ConstraintLayout layoutslide = (ConstraintLayout) view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = (ImageView)  view.findViewById(R.id.slideimg);
        TextView txttitle= (TextView) view.findViewById(R.id.txttitle);
        TextView description = (TextView) view.findViewById(R.id.txtdescription);
        Button swipeBut=view.findViewById(R.id.kaydirButton);
        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(lst_images[position]);
        txttitle.setText(lst_title.get(position));
        description.setText(lst_description.get(position));
        if(position<3){
            swipeBut.setBackgroundColor(Color.TRANSPARENT);
        }else if(position==3){
            swipeBut.setText(context.getString(R.string.entertheApp));
            swipeBut.setOnClickListener(v -> {
                isButtonClicked.setValue(true);
            });

        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
    }
    public LiveData<Boolean> checkButtonClicked(){
        return isButtonClicked;
    }


}
