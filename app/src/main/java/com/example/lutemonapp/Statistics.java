package com.example.lutemonapp;

public class Statistics {

    //General stats
    public static void printGeneralStats() {
        System.out.println("Total Lutemons Created: " + Lutemon.getNumberOfCreatedLutemons());
        System.out.println("Total Battles: " + BattleField.getNumberOfBattles());
        System.out.println("Total Training Sessions: " + TrainingArea.getNumberOfTrainingSessions());
    }

    //Lutemon Preformance
    public void getLutemonPreformance(Lutemon lutemon){
        System.out.println("Lutemon: " + lutemon.color + " (" + lutemon.name + ")");
        System.out.println("Wins: " + lutemon.wins);
        System.out.println("Battles: " + lutemon.battles);
        System.out.println("Trainings: " + lutemon.trainings);

    }
}
