package com.example.lutemonapp;

public class BattleField {
    private static int numberOfBattles;
    private static int numberOflutemonsAtBatlle = 0;
    public static int fight(Lutemon lutemon1, Lutemon lutemon2){
        //Battle algorithm
        int damage = lutemon2.defense(lutemon1);
        lutemon2.health -= damage;
        //if return is 1 then fight is over lutemon1 wins
        if (lutemon2.health <= 0){
            lutemon1.experience += 1;
            lutemon1.wins += 1;
            lutemon1.battles += 1;
            lutemon2.battles +=1;
            lutemon2.health = lutemon2.maxHealth;
            lutemon2.experience = 0;
            numberOfBattles++;
            return 1;
        }
        else{
            //fight can continue
            return 0;
        }
    }

    public static int getNumberOfBattles(){
        return numberOfBattles;
    }

    public static int getNumberOfLutemonsAtBattle(){
        return numberOflutemonsAtBatlle;
    }

    public static void addLutemon(){
        numberOflutemonsAtBatlle++;
    }

    public static void removeLutemon(){
        numberOflutemonsAtBatlle--;
    }


}
