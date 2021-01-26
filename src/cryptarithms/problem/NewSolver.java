package cryptarithms.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import cryptarithms.CBF;
import cryptarithms.utils.PUtils;

public class NewSolver {

    private Scanner scanner;
    private ArrayList<String> operands;
    private ArrayList<String> realOperands;
    private ArrayList<Character> character;
    private int maxstrip = 0;
    private HashMap<Character, Long> value = new HashMap<>(10);
    private HashMap<Character, Long> rvalue = new HashMap<>(10);

    private boolean isError = true;

    public NewSolver(String fileName){
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
        this.realOperands = new ArrayList<String>(4);
        this.character = new ArrayList<Character>(10);

        boolean isResult = false;
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (line.startsWith("-")){
                if (scanner.hasNextLine()){
                    line = scanner.nextLine();
                    isResult = true;
                }
            }
            
            StringBuilder sb = new StringBuilder();
            line = line.trim().replace(" ", "").toUpperCase();
            sb.append(line);
            sb.reverse();
            realOperands.add(line);
            operands.add(sb.toString());
            this.maxstrip = Math.max(this.maxstrip, line.length()+2);
            
            for (int i = 0; i < line.length(); i++){
                char x = sb.toString().charAt(i);
                if (!character.contains(x))
                    character.add(x);

                if (character.size() > 10){
                    CBF.getInstance().getLogger().warning("Error on loading problem: character is more than 10");
                    return;
                }

                long val = Long.parseLong(String.format("%.0f", Math.pow(10, i)));
                if (!isResult){
                    value.put(x, value.getOrDefault(x, 0L)+val);
                } else {
                    rvalue.put(x, rvalue.getOrDefault(x, 0L)+val);
                }
            }
        }
    }

    public long solve(int i){
        if (this.isError) return -1L;

        long left = 0;
        long right = 0;

        List<Integer> list = PUtils.permutations.get(i);
        for (Entry<Character,Long> et : value.entrySet()){
            long k = et.getValue();
            left += k*list.get(character.indexOf(et.getKey()));
        }

        for (Entry<Character,Long> et : rvalue.entrySet()){
            long k = et.getValue();
            right += k*list.get(character.indexOf(et.getKey()));
        }

        for (String s : this.operands){
            char first = s.charAt(s.length()-1);
            int idx = character.indexOf(first);
            if (list.get(idx) == 0) return -1L;
        }
        return (left == right) ? right : -1L;
    }

    public void findSolution(List<Integer> solution, List<Character> characters){
        if (this.isError) return;

        for (int i=0; i < PUtils.TOTAL; i++){
            long s = solve(i);
            if (s != -1L){
                solution.clear();
                solution.addAll(PUtils.permutations.get(i));
                characters.clear();
                characters.addAll(this.character);
                CBF.getInstance().getLogger().info(String.format("Ditemukan setelah: %d iterasi", (i+1)));
                break;
            }
        }
    }
    public void findAllSolution(List<List<Integer>> solutions, List<Character> characters){
        if (this.isError) return;

        Set<Long> uniqueResult = new HashSet<>();
        for (int i=0; i < PUtils.TOTAL; i++){
            long s = solve(i);
            if (s != -1L && uniqueResult.add(s)){
                solutions.add(PUtils.permutations.get(i));
                characters.clear();
                characters.addAll(this.character);
            }
        }
    }

    public List<String> getOperands(){
        return this.realOperands;
    }

    public String getBar(){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < this.maxstrip; i++){
            sb.append("-");
        }
        return sb.toString();
    }
}
