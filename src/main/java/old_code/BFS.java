package old_code;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class BFS {

    int[][] adjMat ={{-1, 1, 10, -1, -1}
            ,{-1, -1, -1, 2, -1}
            ,{-1, -1, -1, -10, -1}
            ,{-1, -1, -1, -1, 3}
            ,{-1, -1, -1, -1, -1}};

    static ScoreObj[] score = new ScoreObj[5];
    static int start = 4;
    static int target = 4;

    public static void main(String[] args) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(start);

        score[start] = new ScoreObj(0, -1);


        Set<Integer> explored = new HashSet<>();
        explored.add(start);
        new BFS().search(queue, explored);
    }

    public void search(LinkedList<Integer> queue, Set<Integer> explored){
        while(!queue.isEmpty()){
            int currentNode = queue.pop();
            for(int i =0; i<=4; i++){
                if(adjMat[currentNode][i] != -1){
                    if(score[i] == null || score[i].score > score[currentNode].score + adjMat[currentNode][i]){
                        score[i] = new ScoreObj(score[currentNode].score + adjMat[currentNode][i], currentNode);
                    }

//                        // return point for shortest hop
//                    if (target == i){
//                        recPrint(4);
//                        return;
//                    }

                    if(!explored.contains(i)){
                        queue.addLast(i);
                        explored.add(currentNode);
                    }
                }
            }
        }

        // if weighted, need to print here
        recPrint(target);
    }

    private void recPrint(int scoreObjIndex){
        if(scoreObjIndex == -1){
            return;
        }
        ScoreObj scoreObj = score[scoreObjIndex];
        recPrint(scoreObj.from);
        System.out.println(scoreObjIndex);
    }

    static class ScoreObj {
        int score;
        int from;

        public ScoreObj(int score, int from) {
            this.score = score;
            this.from = from;
        }
    }
}
