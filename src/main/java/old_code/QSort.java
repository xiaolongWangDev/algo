package old_code;

import java.util.stream.IntStream;

public class QSort {

    public static void main(String[] args) {
        int[] data = {8, 4, 2, 7, 9, 5, 3, 1};
        new QSort().sort(data, 0, data.length -1);
        IntStream.of(data).forEach(System.out::println);
    }

    public void sort(int[] input, int left, int right) {

        if(left < right){
            int pivot = input[left];
            int i = left + 1;
            int j = right;

            while(i < j){
                if(input[i] <= pivot){
                    i++;
                } else if(input[j] >= pivot) {
                    j--;
                } else {
                    int temp = input[j];
                    input[j] = input[i];
                    input[i] = temp;
                    i++;
                    j--;
                }
            }
            int pivotIndex;
            if(i == j && input[j] > pivot){
                input[left] = input[j - 1];
                input[j - 1] = pivot;
                pivotIndex = j - 1;
            } else {
                input[left] = input[j];
                input[j] = pivot;
                pivotIndex = j;
            }

            sort(input, left, pivotIndex - 1);
            sort(input,  pivotIndex + 1, right);

        }
    }

}
