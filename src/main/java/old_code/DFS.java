package old_code;

import java.util.*;

public class DFS {
    int[][] adjMat ={{-1, 1, 10, -1, -1}
            ,{-1, -1, -1, 2, -1}
            ,{-1, -1, -1, -10, -1}
            ,{-1, -1, -1, -1, 3}
            ,{-1, -1, -1, -1, -1}};

    public static void main(String[] args) {
        LinkedList<Integer> path = new LinkedList<Integer>();
        path.add(0);
        Set<Integer> explored = new HashSet<>();
        explored.add(0);
        new DFS().search(4, path, explored, 0);
//        path.forEach(System.out::println);
    }

    public void search(int target, LinkedList<Integer> path, Set<Integer> explored, int sum){

        int currentNode = path.getLast();
        for(int i = 0; i<=4; i++){
            if(!explored.contains(i)){
                if(adjMat[currentNode][i]!=-1){
                    if(i == target){
                        path.forEach(System.out::println);
                        System.out.println(sum + adjMat[currentNode][i]);
                    } else {
                        explored.add(i);
                        path.add(i);
                        search(target, path, explored, sum + adjMat[currentNode][i]);
                        explored.remove(i);
                        path.removeLast();
                    }
                }
            }
        }

    }
}
