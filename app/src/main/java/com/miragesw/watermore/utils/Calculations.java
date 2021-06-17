package com.miragesw.watermore.utils;

public class Calculations {

    public double goalWater(int weight){
        return (weight*Constants.WATER_CONSTANT); //mL
    }

    public double bmi(int weight, int height){
        double doubleheight=(double) height;
        doubleheight=doubleheight/100; //cm to meter
        return (double) (weight/(doubleheight*doubleheight)); // kg/m2
    }

    public int idealMinWeight(int height){ //double yap
        double dheight=height;
        double calc=(18.5)*(dheight/100)*(dheight/100);
        return ((int) calc);
    }
    public int idealMaxWeight(int height){  //double yap
        double dheight=height;
        double calc=(25)*(dheight/100)*(dheight/100);
        return ((int)calc);
    }

    public int bloodRate(int gender,int weight){
        int bloodRate = 0;
        if(gender==Constants.GENDER_MALE){
            bloodRate= (int)((0.006012*Constants.INC*Constants.INC*Constants.INC)+(14.6*weight*2.2046)+604);
        }else if(gender==Constants.GENDER_FEMALE){
            bloodRate= (int)((0.005835*Constants.INC*Constants.INC*Constants.INC)+(15*weight*2.2046)+183);
        }
        return bloodRate;
    }

    public int bmh(int gender,int weight,int height,int age){
        int bmh=0;
        if(gender==Constants.GENDER_MALE){
            bmh=(int) (66.5+(13.75*weight)+(5.03*height)-(6.75*age));
        }else if(gender==Constants.GENDER_FEMALE){
            bmh = (int) (655.1+(9.56*weight)+(1.85*height)-(4.68*age));
        }
        return bmh;
    }

    public int dailyminprotein(int weight, int bmi){
        int minprotein=0;
        if(bmi<18.49){
            minprotein= (int) (weight*1.1);
        }
        if(bmi>18.5 && bmi<24.99){
            minprotein= (int) (weight*1.4);
        }
        if(bmi>25){
            minprotein= (int) (weight*1.2);
        }
        return minprotein;
    }

    public int dailymaxprotein(int weight, int bmi){
        int maxprotein=0;
        if(bmi<18.49){
            maxprotein= (int) (weight*1.3);
        }
        if(bmi>18.5 && bmi<24.99){
            maxprotein= (int) (weight*1.8);
        }
        if(bmi>25){
            maxprotein= (int) (weight*1.5);
        }
        return maxprotein;
    }

    public int minFat(int bmh){
        return (int)(((bmh/4)*100)/884.1);
    }

    public int maxFat(int bmh){
        return (int) ((((bmh*30)/100)*100/884.1));
    }





}
