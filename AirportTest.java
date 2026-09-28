import java.util.*;

// Create BoardingQueue class using a suitable Queue implementation
class BoardingQueue {
    // Using a Queue interface with LinkedList implementation for FIFO behavior
    private Queue<String> queue;

    public BoardingQueue() {
        this.queue = new LinkedList<>();
    }

    // a) Implement addPassenger(String name) to add a passenger to the queue
    public void addPassenger(String name) {
        queue.add(name);
        System.out.println(name + " added to the boarding queue.");
    }

    // b) Implement boardPassenger() to remove and display the passenger at the front
    public void boardPassenger() {
        if (queue.isEmpty()) {
            System.out.println("The boarding queue is empty. No passengers to board.");
        } else {
            String boardedPassenger = queue.poll();
            System.out.println("Passenger boarding: " + boardedPassenger);
        }
    }

    // c) Implement displayQueue() to display all passengers currently waiting to board
    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("No passengers currently in the queue.");
        } else {
            System.out.println("Passengers currently waiting to board: " + queue);
        }
    }
}

// Provided Test Class
public class AirportTest {
    public static void main(String[] args) {
        BoardingQueue queue = new BoardingQueue();
        
        // Demonstrating the functionality
        queue.addPassenger("Alice");
        queue.addPassenger("Bob");
        queue.addPassenger("Charlie");
        
        queue.displayQueue();
        
        queue.boardPassenger();
        queue.displayQueue();
    }
}

