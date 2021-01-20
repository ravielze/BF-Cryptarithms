package cryptarithms.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import cryptarithms.CBF;

public class Solver {

    private Scanner scanner;
    private ArrayList<String> operands;
    private HashSet<Character> character;
    private boolean isError = true;

    public Solver(String fileName){
        Reader pFileReader = new Reader(fileName);

        if (!pFileReader.isAvailable()) return;

        this.scanner = pFileReader.getScanner();
        this.isError = false;
    }

    public boolean isError(){
        return this.isError;
    }

    public void parse(){
        if (this.isError) return;
        if (this.scanner == null) return;

        this.operands = new ArrayList<String>(4);
        this.character = new HashSet<Character>(10);

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.startsWith("-")){
                if (scanner.hasNextLine()){
                    line = scanner.nextLine();
                }
            }
            
            StringBuilder sb = new StringBuilder();
            line = line.trim().replace(" ", "").toUpperCase();
            sb.append(line);
            sb.reverse();
            operands.add(sb.toString());
            
            for (int i = 0; i < line.length(); i++){
                char x = line.charAt(i);
                character.add(x);
                if (character.size() > 10){
                    CBF.getInstance().getLogger().warning("Error on loading problem: character is more than 10");
                    return;
                }
            }
        }
    }

    private ArrayList<Integer> availableNumber = new ArrayList<>(10);
    private HashMap<Character, Integer> map = new HashMap<>(10);
    {
        for (int i=0; i<10; i++){
            availableNumber.add(i);
        }
    }

    public void assign(char c, int x){
        if (!availableNumber.contains(x)){
            CBF.getInstance().getLogger().warning("DEBUG: Number " + x + " is not available.");
            return;
        }

        availableNumber.remove(Integer.valueOf(x));
        map.put(c, x);
    }

    public int unassign(char c){
        if (!map.containsKey(c)){
            CBF.getInstance().getLogger().warning("DEBUG: Character " + c + " is not available.");
            return -1;
        }

        int x = map.remove(c);
        availableNumber.add(x);
        return x;
    }

    public void swap(char a, char b){
        int nilaiA = unassign(a);
        int nilaiB = unassign(b);
        if (nilaiA >= 0 && nilaiB >= 0){
            assign(b, nilaiA);
            assign(a, nilaiB);
        } else if (nilaiA == -1 && nilaiB >= 0){
            assign(b, nilaiB);
        } else if (nilaiB == -1 && nilaiA >= 0){
            assign(a, nilaiA);
        }
    }

    public HashMap<Character,Integer> brute(){
        if (this.isError) return null;

        long it = 0;
        long maxit = (long) Math.pow(10, this.character.size());
        int pos = 0;
        while (it < maxit && (!check())){
            
            it++;
        }

        return this.map;
    }

    public boolean check() {
        int result = value(operands.get(operands.size()-1));
        if (result == -1) return false;

        int total = 0;
        for (int i = 0; i < this.operands.size()-1; i++){
            int val = value(operands.get(0));
            if (val == -1) return false;

            total += val;
        }

        return total == result;
    }

    public int value(String text){
        String txt = text.trim().replace(" ", "").toUpperCase();
        int result = 0;
        for (int i = 0; i < txt.length(); i++){
            char x = txt.charAt(i);
            if (!map.containsKey(x)) return -1;

            result += map.get(x);
        }
        return result;
    }
    
}
