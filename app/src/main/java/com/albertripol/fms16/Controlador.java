package com.albertripol.fms16;

import java.util.ArrayList;

public class Controlador {
    public static boolean primer = true;
    public static ArrayList<Activitat> info = new ArrayList<>();
    public static Activitat act;

    public static void setPrimer(boolean p){
        primer=p;
    }
    public static boolean getPrimer(){
        return primer;
    }

    public static void setActInfo(Activitat a){
        info.add(a);
    }
    public static void setFullInfo(ArrayList<Activitat> a){
        info.clear();
        for(int i=0; i<a.size(); i++){
            Activitat aux = a.get(i);
            info.add(aux);
        }
    }
    public static ArrayList<Activitat> getInfo(){
        return info;
    }
    public static void delActInfo(int i){info.remove(i);}

    public static void setAct(Activitat a){
        act = a;
    }
    public static Activitat getAct(){
        return act;
    }
}
