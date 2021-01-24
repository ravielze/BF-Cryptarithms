package cryptarithms.utils;

import java.util.ArrayList;
import java.util.List;

public final class PUtils {

    public static final long TOTAL = 3265920;
    public static List<Integer> factorial = new ArrayList<>(10);
    public static List<List<Integer>> permutations = new ArrayList<>(3265920);
    static {
        factorial.add(1);
        for (int i = 2; i <= 10; i++){
            factorial.add(i*factorial.get(i-2));
        }
        PUtils.permute(permutations, new ArrayList<>(), new int[] {0,1,2,3,4,5,6,7,8,9}); 
    }

    public static void permute(List<List<Integer>> list, List<Integer> resultList, int[] arr){
 
		if (resultList.size() == arr.length){
			list.add(new ArrayList<>(resultList));
		} else {
			for (int i=0; i < arr.length; i++){ 
 
                if(resultList.contains(arr[i])) continue;
                
				resultList.add(arr[i]);
				permute(list, resultList, arr);
				resultList.remove(resultList.size() - 1);
			}
		}
	} 
    
}
