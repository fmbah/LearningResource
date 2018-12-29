package more.thread.test.queue数据结构;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @ClassName Q1
 * @Description
 * @Author root
 * @Date 18-12-26 上午11:21
 * @Version 1.0
 **/
public class Q1 {

    public static void main(String[] args) {

        Queue<String> myQueue = new LinkedList<String>();

        // add elements in the queue using offer() - return true/false
        myQueue.offer("Monday");//tail add element
        myQueue.offer("Thusday");//tail add element
        boolean flag = myQueue.offer("Wednesday");//tail add element return flag with add success or fail

        System.out.println("Wednesday inserted successfully? "+flag);

        // add more elements using add() - throws IllegalStateException
        try {
            myQueue.add("Thursday");//tail add element
            myQueue.add("Friday");
            myQueue.add("Weekend");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        System.out.println("Pick the head of the queue: " + myQueue.peek());//get head but not remove

        String head = null;
        try {
            // remove head - remove()
            head = myQueue.remove();//remove head and get element
            System.out.print("1) Push out " + head + " from the queue ");
            System.out.println("and the new head is now: "+myQueue.element());//get head element
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        // remove the head - poll()
        head = myQueue.poll();//remove head and get element
        System.out.print("2) Push out " + head + " from the queue");
        System.out.println("and the new head is now: "+myQueue.peek());

        // find out if the queue contains an object
        System.out.println("Does the queue contain 'Weekend'? " + myQueue.contains("Weekend"));
        System.out.println("Does the queue contain 'Thusday'? " + myQueue.contains("Thusday"));
        System.out.println("Does the queue contain 'Monday'? " + myQueue.contains("Monday"));
    }

}
