package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: Write more unit tests to test the implementation of ArrayIntQueue
 * 
 * for the {@link LinkedIntQueue} and
 * {@link ArrayIntQueue} classes, as described in the handout. The
 * {@link ArrayIntQueue} class contains a few bugs. Use the tests you wrote for
 * the {@link LinkedIntQueue} class to test the {@link ArrayIntQueue}
 *
 * @author Alex Lockwood, George Guo
 */
public class IntQueueTest {

    private IntQueue ArrayIntQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        ArrayIntQueue = new LinkedIntQueue();
//        ArrayIntQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(ArrayIntQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        ArrayIntQueue.enqueue(1);
        assertFalse(ArrayIntQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        assertNull(ArrayIntQueue.peek());
    }

    @Test
    public void testPeekNoEmptyQueue() {
        Integer input = 1;
        ArrayIntQueue.enqueue(input);
        assertEquals(input, ArrayIntQueue.peek());
    }

    @Test
    public void testEnqueue() {
        for (int i = 0; i < testList.size(); i++) {
            ArrayIntQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), ArrayIntQueue.peek());
            assertEquals(i + 1, ArrayIntQueue.size());
        }
    }

    @Test
    public void testDequeue() {
        testList.forEach(n -> ArrayIntQueue.enqueue(n));
        for (int i = 0; i < testList.size(); i++) {
            assertEquals(testList.get(i), ArrayIntQueue.dequeue());
            assertEquals(testList.size() - i - 1, ArrayIntQueue.size());
        }
    }

    @Test
    public void testContent() throws IOException {
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                ArrayIntQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(ArrayIntQueue.dequeue(), result);
            }
        }
    }

    // more test case 
    @Test
    public void  testClear() {
        // Test the clear method
        ArrayIntQueue.enqueue(1);
        ArrayIntQueue.enqueue(2);
        ArrayIntQueue.enqueue(3);
        ArrayIntQueue.clear();
        assertTrue(ArrayIntQueue.isEmpty());
        assertEquals(0, ArrayIntQueue.size());
    }
}
