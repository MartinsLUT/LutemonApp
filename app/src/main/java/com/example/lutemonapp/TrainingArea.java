package com.example.lutemonapp;

import java.util.ArrayList;

public class TrainingArea {
    private static int numberOfTrainingSessions;
    private static int lutemonsAtTrainingArea;
    private static ArrayList<Lutemon> lutemons;
    public static void train(Lutemon lutemon){
        lutemon.attack += lutemon.experience;
        lutemon.experience = 0;




    }
    public static int getNumberOfTrainingSessions(){
        return numberOfTrainingSessions;
    }
    public static void addXp(Lutemon lutemon){
        lutemon.experience++;
        lutemon.trainings++;
        numberOfTrainingSessions++;
    }

    public static void addLutemon(){
        lutemonsAtTrainingArea++;
    }
    public static void removeLutemon(){
        lutemonsAtTrainingArea--;
    }

    public static int getNumberOfLutemonsAtTrainingArea(){
        return lutemonsAtTrainingArea;
    }


}
