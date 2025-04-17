package com.example.lutemonapp;

import java.util.ArrayList;
import java.util.HashMap;

public class Home  {




    private static ArrayList<Lutemon> lutemons = new ArrayList<>();
    private static ArrayList<Lutemon> lutemonsAtHome = new ArrayList<>();
    private static ArrayList<Lutemon> lutemonsAtBattleField = new ArrayList<>();
    private static ArrayList<Lutemon> lutemonsAtTrainingArea = new ArrayList<>();
    private static int numberOfLutemonsAtHome = 0;

    public static void createLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
        lutemonsAtHome.add(lutemon);
        numberOfLutemonsAtHome++;
    }
    public static Lutemon getLutemon(int id) {
        for (Lutemon lutemon : lutemons) {
            if (lutemon.id == id) {
                return lutemon;

            }

        }
        return null;

    }


    public static ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }
    public static ArrayList<Lutemon> listOfLutemonsAtHome() {
        return lutemonsAtHome;

    }
    public static ArrayList<Lutemon> listOfLutemonsAtBattleField() {
        return lutemonsAtBattleField;
    }
    public static ArrayList<Lutemon> listOfLutemonsAtTrainingArea() {
        return lutemonsAtTrainingArea;
    }

    public static void moveLutemonToBattleField(int id) {
        for (Lutemon lutemon : lutemons) {
            if (lutemon.id == id) {
                lutemonsAtHome.remove(lutemon);
                numberOfLutemonsAtHome --;
                lutemonsAtBattleField.add(lutemon);
                BattleField.addLutemon();
            }
        }
    }
    public static void moveLutemonToTrainingArea(int id) {
        for (Lutemon lutemon : lutemons) {
            if (lutemon.id == id) {
                lutemonsAtHome.remove(lutemon);
                lutemonsAtTrainingArea.add(lutemon);
                numberOfLutemonsAtHome --;
                TrainingArea.addLutemon();

            }
        }

    } 
    public static void moveLutemonToHomeFromTraining(int id) {
        for (Lutemon lutemon : lutemons) {
            if (lutemon.id == id) {
                lutemonsAtTrainingArea.remove(lutemon);
                lutemonsAtHome.add(lutemon);
                numberOfLutemonsAtHome ++;
                TrainingArea.removeLutemon();

            }
        }
    }
    public static void moveLutemonToHomeFromBattle(int id) {
        for (Lutemon lutemon : lutemons) {
            if (lutemon.id == id) {
                lutemonsAtBattleField.remove(lutemon);
                lutemonsAtHome.add(lutemon);
                numberOfLutemonsAtHome++;
                BattleField.removeLutemon();
            }
        }
    }


    public static int getNumberOfLutemonsAtHome() {
        return numberOfLutemonsAtHome;
    }


}

