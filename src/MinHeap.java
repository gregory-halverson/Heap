// Gregory Halverson
// Pierce College
// CS 532
// Spring 2014

// Heap with minimum value at top
public class MinHeap extends Heap
{
    // Copy from an array
    MinHeap(int [] init)
    {
        super(init);
        heapify();
    }

    // Traverse the tree down and swap with the smallest child on each iteration
    @Override void siftDown(int index)
    {
        int smaller_child;

        while (hasChildren(index) && value(index) > value(smaller_child = smallerChild(index)))
        {
            swap(index, smaller_child);
            index = smaller_child;
        }
    }

    // Traverse the tree up and swap with parent until smaller value is found
    @Override void siftUp(int index)
    {
        while (hasParent(index) && parentValue(index) > value(index))
        {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }
}