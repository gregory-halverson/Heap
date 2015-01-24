// Gregory Halverson
// Pierce College
// CS 532
// Spring 2014

import java.util.Arrays;

public class TestHeap
{
    public static void main(String[] args)
    {
        int [] unsorted_array = new int[]{15, 37, 63, 49, 35, 74, 72, 34, 65, 25, 78, 98, 45, 34, 23};

        System.out.println();
        System.out.println("unsorted:\t" + Arrays.toString(unsorted_array));
        System.out.println("ascending:\t" + Arrays.toString(Heap.sortAscending(unsorted_array)));
        System.out.println("descending:\t" + Arrays.toString(Heap.sortDescending(unsorted_array)));
        System.out.println();

        MinHeap min = new MinHeap(unsorted_array);

        System.out.println("Min Heap");
        System.out.println();
        System.out.print(min.printTree());
        System.out.println();

        MaxHeap max = new MaxHeap(unsorted_array);

        System.out.println("Max Heap");
        System.out.println();
        System.out.print(max.printTree());
        System.out.println();
    }
}
