package com.trying.myapplication;

public class globalVariables {
    private static globalVariables instance;

    // Global variable
    private int data;

    // Restrict the constructor from being instantiated
    globalVariables(){}

    public void setData(int d){
        this.data=d;
    }
    public int getData(){
        return this.data;
    }

    public static synchronized globalVariables getInstance(){
        if(instance==null){
            instance=new globalVariables();
            instance=new globalVariables();
        }
        return instance;
    }


}
