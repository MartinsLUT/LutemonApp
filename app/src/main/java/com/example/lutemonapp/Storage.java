package com.example.lutemonapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
                lutemonsAtBattleField.remove(lutemon.id);
                lutemonsAtHome.put(id, lutemon);
                Home.add();
                BattleField.removeLutemon();
            }
        }
    }

    public static void saveApp(Context context) {
        File file = new File(context.getFilesDir(), "LutemonApp.ser");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Lutemon lutemon : lutemons.values()) {
                writer.write(lutemon.lutemonInfo());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadApp(Context context) {
        File file = new File(context.getFilesDir(), "LutemonApp.ser");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                try {
                    String name = parts[0];
                    String color = parts[1];
                    int attack = Integer.parseInt(parts[2]);
                    int defense = Integer.parseInt(parts[3]);
                    int experience = Integer.parseInt(parts[4]);
                    int health = Integer.parseInt(parts[5]);
                    int maxHealth = Integer.parseInt(parts[6]);
                    int wins = Integer.parseInt(parts[7]);
                    int trainings = Integer.parseInt(parts[8]);
                    int battles = Integer.parseInt(parts[9]);
                    Lutemon lutemon = new Lutemon(name, color, attack, defense, experience, health, maxHealth, wins, trainings, battles);
                    Home.createLutemon(lutemon);

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void clearFile(Context context) {
        File file = new File(context.getFilesDir(), "LutemonApp.ser");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


