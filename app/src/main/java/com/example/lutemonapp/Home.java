package com.example.lutemonapp;

import java.util.ArrayList;
import java.util.HashMap;

public class Home  {




    private static int numberOfLutemonsAtHome = 0;

    public static void createLutemon(Lutemon lutemon) {
        Storage.addLutemon(lutemon);
        numberOfLutemonsAtHome++;
    }
    public static int getNumberOfLutemonsAtHome() {
        return numberOfLutemonsAtHome;
    }
    public static void add(){
        numberOfLutemonsAtHome++;
    }
    public static void remove(){
        numberOfLutemonsAtHome--;
    }





}

