package com.example.lutemonapp;

import java.util.HashMap;

public class Home  {




    private static HashMap<Integer, Lutemon> lutemons = new HashMap<>();
    private static HashMap<Integer, Lutemon> lutemonsAtHome = new HashMap<>();
    private static HashMap<Integer, Lutemon> lutemonsAtBattleField = new HashMap<>();
    private static HashMap<Integer, Lutemon> lutemonsAtTrainingArea = new HashMap<>();
    private static int numberOfLutemonsAtHome = 0;

    public static void createLutemon(Lutemon lutemon) {
        int lutemonid = lutemon.id;
        lutemons.put(lutemonid,lutemon);
        lutemonsAtHome.put(lutemonid, lutemon);
        numberOfLutemonsAtHome++;
    }


    public static HashMap<Integer, Lutemon> getLutemons() {
        return lutemons;
    }
    public static HashMap<Integer, Lutemon> listOfLutemonsAtHome() {
        return lutemonsAtHome;

    }
    public static HashMap<Integer, Lutemon> listOfLutemonsAtBattleField() {
        return lutemonsAtBattleField;
    }
    public static HashMap<Integer, Lutemon> listOfLutemonsAtTrainingArea() {
        return lutemonsAtTrainingArea;
    }

    public static void moveLutemonToBattleField(int id) {
        for (Lutemon lutemon : lutemons.values()) {
            if (lutemon.id == id) {
                lutemonsAtHome.remove(lutemon.id, lutemon);
                numberOfLutemonsAtHome --;
                lutemonsAtBattleField.put(lutemon.id, lutemon);
                BattleField.addLutemon();
            }
        }
    }
    public static void moveLutemonToTrainingArea(int id) {
        for (Lutemon lutemon : lutemons.values()) {
            if (lutemon.id == id) {
                lutemonsAtHome.remove(lutemon.id, lutemon);
                lutemonsAtTrainingArea.put(lutemon.id, lutemon);
                numberOfLutemonsAtHome --;
                TrainingArea.addLutemon();

            }
        }

    } 
    public static void moveLutemonToHomeFromTraining(int id) {
        for (Lutemon lutemon : lutemons.values()) {
            if (lutemon.id == id) {
                lutemonsAtTrainingArea.remove(lutemon.id, lutemon);
                lutemonsAtHome.put(lutemon.id, lutemon);
                numberOfLutemonsAtHome ++;
                TrainingArea.removeLutemon();

            }
        }
    }
    public static void moveLutemonToHomeFromBattle(int id) {
        for (Lutemon lutemon : lutemons.values()) {
            if (lutemon.id == id) {
                lutemonsAtBattleField.remove(lutemon.id, lutemon);
                lutemonsAtHome.put(lutemon.id, lutemon);
                numberOfLutemonsAtHome++;
                BattleField.removeLutemon();
            }
        }
    }


    public static int getNumberOfLutemonsAtHome() {
        return numberOfLutemonsAtHome;
    }


}

