package cryptarithms.utils;

import java.util.List;

import cryptarithms.CBF;

public class ShowUtils {

    public static void show(List<String> operands, List<Integer> result, List<Character> character){
        int id = 0;
        for (String operand : operands){
            if (id == operands.size()-1){
                CBF.getInstance().getLogger().info("------------");
            }
            System.out.println(operand);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < operand.length(); i++){
                sb.append(result.get(character.indexOf(operand.charAt(i))));
            }
            CBF.getInstance().getLogger().info(sb.toString());
            id++;
        }
    }

    public static void showAll(List<String> operands, List<List<Integer>> resultList, List<Character> characters){
        int id = 1;
        for (List<Integer> l : resultList){
            if (resultList.size() > 1){
                CBF.getInstance().getLogger().info(String.format("Solusi ke-%d:", id));
            }
            show(operands, l, characters);
            if (resultList.size() > 1){
                CBF.getInstance().getLogger().info("");
            }
            id++;
        }
    }
    
}
