/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Objects;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * WIP
 * @author Rio
 */
public class BattleLogic {
    private List<Unit> playerOneUnits;
    private List<Unit> playerTwoUnits;
    
    private List<Unit> allUnits;
    
    public record Pair<K, V>(K key, V value) {
    // intentionally empty
    }


    List<Integer> attack = new ArrayList<>();
    List<Integer> health = new ArrayList<>();
    List<Integer> idx = new ArrayList<>();
    List<Integer> teams = new ArrayList<>();
    List<Pair<Integer, Integer>> priority = new ArrayList<>();

    public BattleLogic(List<Unit> playerOneUnits, List<Unit> playerTwoUnits) {
        System.out.println("playerOneUnits playerOneUnits : " + playerOneUnits);
        this.playerOneUnits = playerOneUnits;
        this.playerTwoUnits = playerTwoUnits;

        
        
        // Populate attack, health, teams, and idx lists
        for (Unit obj : playerOneUnits) {
            attack.add(obj.getAttack());
            health.add(obj.getHealth());
            teams.add(0);
            idx.add(playerOneUnits.indexOf(obj));
        }

        for (Unit obj : playerTwoUnits) {
            attack.add(obj.getAttack());
            health.add(obj.getHealth());
            teams.add(1);
            idx.add(playerTwoUnits.indexOf(obj));
        }
        
        
        // Initialize sort_idx array
        List<Integer> sortIdx = new ArrayList<>();
        for (int i = 0; i < attack.size(); i++) {
            sortIdx.add(i);
        }

        sortIdx.sort((a, b) -> Integer.compare(attack.get(b), attack.get(a)));

        List<Integer> uniquea = new ArrayList<>(new HashSet<>(attack));
        Collections.sort(uniquea, Collections.reverseOrder());

         int startIdx = 0;
         for (int uattack : uniquea) {
            // Get collision idx
            List<Integer> tempIdx = new ArrayList<>();
            for (int i = 0; i < attack.size(); i++) {
                if (attack.get(i) == uattack) {
                    tempIdx.add(i);
                }
            }
            List<Integer> tempAttack = tempIdx.stream().map(attack::get).collect(Collectors.toList());

            // Initialize final idx for sorting
            List<Integer> tempSortIdx = new ArrayList<>();
            for (int i = 0; i < tempIdx.size(); i++) {
                tempSortIdx.add(i);
            }
            
            if (tempIdx.size() < 2) {
                int endIdx = startIdx + tempIdx.size();
                for (int i = startIdx; i < endIdx; i++) {
                    sortIdx.set(i, tempIdx.get(i - startIdx));
                }
                startIdx = endIdx;
                continue;
            }
            
            // Correct attack collisions by adding in health
            List<Integer> tempHealth = new ArrayList<>(); // tempIdx.stream().map(health::get).collect(Collectors.toList());
            for (Integer index : tempIdx) {
                tempHealth.add(health.get(index));
            }

            
            List<Integer> tempStats = new ArrayList<>();
            for (int i = 0; i < tempAttack.size(); i++) {
                tempStats.add(tempAttack.get(i) + tempHealth.get(i));
            }
            
            int tempStartIdx = 0;
            List<Integer> tempSortIdxShuffled = new ArrayList<>();
            
            List<Integer> uniqueStats = new ArrayList<>(new HashSet<>(tempStats));
            Collections.sort(uniqueStats, Collections.reverseOrder());
            
            for (int ustats : uniqueStats) {
                List<Integer> tempSIdx = new ArrayList<>();
                for (int i = 0; i < tempStats.size(); i++) {
                    if (tempStats.get(i) == ustats) {
                        tempSIdx.add(i);
                    }
                }
                Collections.shuffle(tempSIdx, new Random());
                tempSortIdxShuffled.addAll(tempSIdx);
            }
            
              // Dereference temp_sort_idx and store in sort_idx
            int endIdx = startIdx + tempIdx.size();
            for (int i = startIdx; i < endIdx; i++) {
                sortIdx.set(i, tempIdx.get(tempSortIdxShuffled.get(i - startIdx)));
            }
            startIdx = endIdx;
            
         }
         
         
          // Finish sorting by max attack
        List<Integer> finalAttack = sortIdx.stream().map(attack::get).collect(Collectors.toList());
        List<Integer> finalTeams = sortIdx.stream().map(teams::get).collect(Collectors.toList());
        List<Integer> finalIdx = sortIdx.stream().map(idx::get).collect(Collectors.toList());

        // Build final queue
        
        for (int i = 0; i < finalTeams.size(); i++) {
            int team = finalTeams.get(i);
            int index = finalIdx.get(i);

            List<Unit> selectedTeam = (team == 0) ? playerOneUnits : playerTwoUnits;
            
            if (!selectedTeam.isEmpty()) {
                priority.add(new Pair<>(team, index));
            }
        }
                
        start(1);
    }
    
    
    public class BattlePhases {
        public enum Option {
          FirstOption,
          SecondOption,
          ThirdOption
        }
      }

    
    public boolean isTeam1Alive() {
        return playerOneUnits.stream().anyMatch(Unit::isAlive);
    }

    public boolean isTeam2Alive() {
        return playerTwoUnits.stream().anyMatch(Unit::isAlive);
    }
    
    
    private List<BattlePhases.Option> phases = Arrays.asList(BattlePhases.Option.values());
    
    boolean start(int battleIteration) {
        // @TODO
        boolean foundPlayerOne = false;
        boolean foundPlayerTwo = false;
        
        
        if (!playerOneUnits.isEmpty()) foundPlayerOne = true;
        if (!playerTwoUnits.isEmpty()) foundPlayerTwo = true;
                
        
        System.out.println("-- --");

        for (Unit x : playerOneUnits) {
            System.out.println(x.getName());
        }
        System.out.println("-- --");
        for (Unit x : playerTwoUnits) {
            System.out.println(x.getName());
        }
        System.out.println("-- --");
        
        
        for (BattlePhases.Option phase : phases) {
            if (phase == BattlePhases.Option.FirstOption) {
//                for (Pair<Integer,Integer> player_index : this.priority) {
//                    List<Unit> selectedTeam = (player_index.key == 0) ? playerOneUnits : playerTwoUnits;
//                    Unit firstUnit = selectedTeam.get(player_index.value);
//                    System.out.println("Units:" + player_index.key + " " +firstUnit.getName());
//                    
//                    
//                }
                if (playerOneUnits.get(0).isAlive()) {
                
                }
            }
        }
        
        int team1AttackerIndex = 0;
        int team2AttackerIndex = 0;
        
        while (isTeam1Alive() && isTeam2Alive()) {
            Unit team1Attacker = playerOneUnits.get(team1AttackerIndex);
            Unit team2Attacker = playerTwoUnits.get(team2AttackerIndex);

            // Check if the units in the first slot are alive and attack each other
            if (team1Attacker.isAlive() && team2Attacker.isAlive()) {
                int damage1 = team1Attacker.getAttack();
                int damage2 = team2Attacker.getAttack();

                team2Attacker.takeDamage(damage1);
                team1Attacker.takeDamage(damage2);

                // If a unit's health is zero or less, it's defeated, and the next unit takes its place
                if (!team1Attacker.isAlive()) {
                    team1AttackerIndex++;
                }
                if (!team2Attacker.isAlive()) {
                    team2AttackerIndex++;
                }
            } else {
                // If a unit is defeated, move to the next unit in the slot
                if (!team1Attacker.isAlive()) {
                    team1AttackerIndex++;
                }
                if (!team2Attacker.isAlive()) {
                    team2AttackerIndex++;
                }
            }
        }
        
        if (isTeam1Alive()) {
            System.out.println("Team 1 won");
        } else if (isTeam2Alive()) {
            System.out.println("Team 2 won");
        } else {
            System.out.println("Draw");
        }
        
        return true;
    }
    
    
    
    
}
