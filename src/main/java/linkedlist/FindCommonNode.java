package linkedlist;

import java.util.List;

import static linkedlist.LinkedListUtils.*;

public class FindCommonNode {

    public static void main(String[] args) {

        // my awesome test data
        int firstSize = 8;
        int secondSize = 6;
        int commonSize = 5;
        // no loop, no common
        List<Node> testDataBundle1 = generateTestDataForTheCommonNodeProblem(firstSize, secondSize, 0, 0, 100, false, false);
        printTrio(testDataBundle1, firstSize, secondSize, commonSize);
        // has loop, no common
        List<Node> testDataBundle2 = generateTestDataForTheCommonNodeProblem(firstSize, secondSize, 0, 0, 100, true, false);
        printTrio(testDataBundle2, firstSize, secondSize, commonSize);
        // no loop, has common
        List<Node> testDataBundle3 = generateTestDataForTheCommonNodeProblem(firstSize, secondSize, commonSize, 0, 100, false, false);
        printTrio(testDataBundle3, firstSize, secondSize, commonSize);
        // has loop, has common, common entry point
        List<Node> testDataBundle4 = generateTestDataForTheCommonNodeProblem(firstSize, secondSize, commonSize, 0, 100, true, false);
        printTrio(testDataBundle4, firstSize, secondSize, commonSize);
        // has loop, has common, different entry point
        List<Node> testDataBundle5 = generateTestDataForTheCommonNodeProblem(firstSize, secondSize, commonSize, 0, 100, true, false);
        printTrio(testDataBundle5, firstSize, secondSize, commonSize);

    }

    private static void printTrio(List<Node> testDataBundle, int firstSize, int secondSize, int commonSize){
        System.out.println("");
        System.out.println("");
        printFirstN(testDataBundle.get(0), firstSize);
        System.out.println("___________");
        printFirstN(testDataBundle.get(1), secondSize);
        System.out.println("___________");
        printFirstN(testDataBundle.get(2), commonSize);
    }
}
