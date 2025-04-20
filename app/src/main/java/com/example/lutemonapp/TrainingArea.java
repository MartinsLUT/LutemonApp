package com.example.lutemonapp;

public class TrainingArea {
    private static int numberOfTrainingSessions;
    private static int lutemonsAtTrainingArea;
    public static void train(Lutemon lutemon){
        //training algorithm
        lutemon.attack += lutemon.experience;
        lutemon.experience = 0;
    }
    public static int getNumberOfTrainingSessions(){
        return numberOfTrainingSessions;
    }
    public static void addXp(Lutemon lutemon){
        //when trained lutemon gets xp and lutemon and game stats get updated
        lutemon.health = lutemon.getMaxHealth();
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
