// Gregory Halverson
// Pierce College
// CS 532
// Spring 2014

// Heap with maximum value at top
public class MaxHeap extends Heap
{
    // Copy from an array
    MaxHeap(int [] init)
    {
        super(init);
        heapify();
    }

    // Traverse the tree down and swap with the largest child on each iteration
    @Override void siftDown(int index)
    {
        int larger_child;

        while (hasChildren(index) && value(index) < value(larger_child = largerChild(index)))
        {
            swap(index, larger_child);
            index = larger_child;
        }
    }

    // Traverse the tree up and swap with parent until larger value is found
    @Override void siftUp(int index)
    {
        while (hasParent(index) && parentValue(index) < value(index))
        {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }
}
