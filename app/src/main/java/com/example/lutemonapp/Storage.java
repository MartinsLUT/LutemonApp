package com.example.lutemonapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    public String name;

    private static HashMap<Integer, Lutemon> lutemons = new HashMap<>();
    private static HashMap<Integer, Lutemon> lutemonsAtHome = new HashMap<>();
    private static HashMap<Integer, Lutemon> lutemonsAtBattleField = new HashMap<>();
    private static HashMap<Integer, Lutemon> lutemonsAtTrainingArea = new HashMap<>();


    public static void addLutemon(Lutemon lutemon) {
        lutemons.put(lutemon.id, lutemon);
        lutemonsAtHome.put(lutemon.id, lutemon);
    }

    public Lutemon getLutemon(int id) {
        for (Lutemon lutemon : lutemons.values()) {
            if (lutemon.id == id) {
                return lutemon;
            }
        }
        return null;
    }

    public static HashMap<Integer, Lutemon> getAllLutemons() {
        return lutemons;
    }
    public static HashMap<Integer, Lutemon> getLutemonsAtHome() {
        return lutemonsAtHome;
    }
    public static HashMap<Integer, Lutemon> getLutemonsAtBattleField() {
        return lutemonsAtBattleField;
    }
    public static HashMap<Integer, Lutemon> getLutemonsAtTrainingArea() {
        return lutemonsAtTrainingArea;
    }

    public static void moveLutemonToBattleField(int id) {
        for (Lutemon lutemon : lutemons.values()) {
            if (lutemon.id == id) {
                lutemonsAtHome.remove(lutemon.id);
                Home.remove();
                lutemonsAtBattleField.put(lutemon.id, lutemon);
                BattleField.addLutemon();
            }
        }
    }
    public static void moveLutemonToTrainingArea(int id) {
        for (Lutemon lutemon : lutemons.values()) {
            if (lutemon.id == id) {
                lutemonsAtHome.remove(lutemon.id);
                lutemonsAtTrainingArea.put(lutemon.id, lutemon);
                Home.remove();
                TrainingArea.addLutemon();

            }
        }

    }
    public static void moveLutemonToHomeFromTraining(int id) {
        for (Lutemon lutemon : lutemons.values()) {
            if (lutemon.id == id) {
                lutemonsAtTrainingArea.remove(lutemon.id);
                lutemonsAtHome.put(lutemon.id, lutemon);
                Home.add();
                TrainingArea.removeLutemon();

            }
        }
    }
    public static void moveLutemonToHomeFromBattle(int id) {
        for (Lutemon lutemon : lutemons.values()) {
            if (lutemon.id == id) {
                lutemonsAtBattleField.remove(id);
                lutemonsAtHome.put(id, lutemon);
                Home.add();
                BattleField.removeLutemon();
            }
        }
    }
}
