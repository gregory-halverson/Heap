// Gregory Halverson
// Pierce College
// CS 532
// Spring 2014

// Abstract class for Heap, implemented by MaxHeap and MinHeap
public class Heap
{
    // Constants
    public static final int TERMINAL = -1;

    // Member data
    private int [] array;
    protected int size;

    // Constructors

    // Default constructor: empty tree
    Heap()
    {
        size = 0;
        array = null;
    }

    // Copy from array
    Heap(int [] init)
    {
        // Copy size from source array
        size = init.length;

        // Allocate memory for array
        array = new int[(int)Math.pow(2, getHeight()) - 1];

        // Initialize values of array
        for (int i = 0; i < size; i++)
            array[i] = init[i];
    }

    // Allocate enough memory in a new array to contain an extra level on the tree
    void allocateLevel()
    {
        // Store data
        int [] temp = array;

        // Allocate memory
        array = new int[(int)Math.pow(2, getHeight() + 1) - 1];

        // Copy data to new array
        for (int i = 0; i < size; i++)
            array[i] = temp[i];
    }

    // Size of array
    int getAllocatedSize()
    {
        return array.length;
    }

    // Size of tree
    int getSize()
    {
        return size;
    }

    // Check if the tree is full
    boolean full()
    {
        return size == array.length;
    }

    // Calculate height of tree
    int getHeight()
    {
        return (int)Math.ceil(Math.log(size + 1) / Math.log(2));
    }

    // Find parent if it exists
    int parentIndex(int index)
    {
        if (index == 1)
            return TERMINAL;
        else
            return index / 2;
    }

    // Find left child if it exists
    int leftIndex(int index)
    {
        int left_index = 2 * index;

        if (left_index > size)
            return TERMINAL;
        else
            return left_index;
    }

    // Find right child if it exists
    int rightIndex(int index)
    {
        int right_index = 2 * index + 1;

        if (right_index > size)
            return -1;
        else
            return right_index;
    }

    // Return value at 1-based index
    int value(int index)
    {
        return array[index - 1];
    }

    // Return value of parent if it exists
    int parentValue(int index)
    {
        if (hasParent(index))
            return array[parentIndex(index) - 1];
        else
            return TERMINAL;
    }

    // Return value of left child if it exists
    int leftValue(int index)
    {
        int left_index = leftIndex(index);

        if (left_index == TERMINAL)
            return TERMINAL;
        else
            return array[leftIndex(index) - 1];
    }

    // Return value of right child if it exists
    int rightValue(int index)
    {
        int right_index = rightIndex(index);

        if (right_index == TERMINAL)
            return TERMINAL;
        else
            return array[rightIndex(index) - 1];
    }

    // Check if node has a parent
    boolean hasParent(int index)
    {
        return parentIndex(index) != TERMINAL;
    }

    // Check if node has children
    boolean hasChildren(int index)
    {
        return hasLeft(index) || hasRight(index);
    }

    // Check if node has a left child
    boolean hasLeft(int index)
    {
        return leftIndex(index) != TERMINAL;
    }

    // Check if node has a right child
    boolean hasRight(int index)
    {
        return rightIndex(index) != TERMINAL;
    }

    // Find smallest child, if it exists
    int smallerChild(int index)
    {
        if (!hasChildren(index))
            return TERMINAL;
        else if (hasLeft(index) && !hasRight(index))
            return leftIndex(index);
        else if (!hasLeft(index) && hasRight(index))
            return rightIndex(index);
        else if (leftValue(index) < rightValue(index))
            return leftIndex(index);
        else
            return rightIndex(index);
    }

    // Find largest child, if it exists
    int largerChild(int index)
    {
        if (!hasChildren(index))
            return TERMINAL;
        else if (hasLeft(index) && !hasRight(index))
            return leftIndex(index);
        else if (!hasLeft(index) && hasRight(index))
            return rightIndex(index);
        else if (leftValue(index) > rightValue(index))
            return leftIndex(index);
        else
            return rightIndex(index);
    }

    // Swap two values in the tree
    void swap(int index1, int index2)
    {
        int temp = array[index1 - 1];
        array[index1 - 1] = array[index2 - 1];
        array[index2 - 1] = temp;
    }

    // Implemented in MaxHeap and MinHeap
    void siftDown(int index) {}

    // Implemented in MaxHeap and MinHeap
    void siftUp(int index) {}

    // Sift down all nodes to make the tree a heap
    void heapify()
    {
        for (int i = size / 2; i > 0; i--)
        {
            siftDown(i);
        }
    }

    // Insert a value and sift up to resort the tree
    void insert(int value)
    {
        // Check if array is full and allocate memory if necessary
        if (full())
            allocateLevel();

        // Insert value to end of tree
        array[size++] = value;

        // Resort tree
        siftUp(size);
    }

    // Remove top value of tree and resort
    int pop()
    {
        int result = value(1);

        // Hide value at the end of the tree
        swap(1, size);
        size--;
        siftDown(1);

        return result;
    }

    // Produce sorted array and restore the tree
    int [] sort()
    {
        int original_size = size;
        int [] result = new int[size];

        // Pop each value into an array
        for (int i = 0; i < original_size; i++)
            result[i] = pop();

        // Restore heap
        size = original_size;
        heapify();

        return result;
    }

    // Use MinHeap to sort an array in ascending order
    public static final int [] sortAscending(int [] unsorted_array)
    {
        return new MinHeap(unsorted_array).sort();
    }

    // Use MaxHeap to sort an array in descending order
    public static final int [] sortDescending(int [] unsorted_array)
    {
        return new MaxHeap(unsorted_array).sort();
    }

    // Print data in it's raw order in memory
    String printArray()
    {
        String output_string = new String();

        output_string += "(";

        // Iterate array
        for (int i = 0; i < size; i++)
        {
            output_string += array[i];

            if (i != size - 1)
                output_string += ", ";
        }

        output_string += ")";

        return output_string;
    }

    // Print tree with each level centered
    String printTree()
    {
        String output_string = new String();

        int minimum_field_size = 0;

        // Find widest value
        for (int c = 0; c < size; c++)
            if (Integer.toString(array[c]).length() > minimum_field_size)
                minimum_field_size = Integer.toString(array[c]).length();

        // Find width of the bottom level
        int longest_line = (int)(Math.pow(2, getHeight() - 1) * (minimum_field_size + 1));

        int i = 0;

        // Iterate tree
        for (int level = 0; level <= getHeight(); level++)
        {
            for (int element = 0; element < Math.pow(2, level) && i < size; element++)
            {
                String next_element = Integer.toString(array[i++]);

                int field_size = (int)(longest_line / Math.pow(2, level));

                // Indent output
                if (field_size - next_element.length() == 1)
                    output_string += " ";
                else
                    for (int c = 0; c < (field_size - next_element.length()) / 2; c++)
                        output_string += " ";

                output_string += next_element;

                for (int c = 0; c < (field_size - next_element.length()) / 2; c++)
                    output_string += " ";
            }

            output_string += System.getProperty("line.separator");
        }

        return output_string;
    }
}
