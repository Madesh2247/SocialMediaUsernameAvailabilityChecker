import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class InventoryManager {

    // productId -> stock
    private ConcurrentHashMap<String, AtomicInteger> stockMap;

    // productId -> waiting list (FIFO)
    private ConcurrentHashMap<String, LinkedHashMap<Integer, Long>> waitingList;

    public InventoryManager() {
        stockMap = new ConcurrentHashMap<>();
        waitingList = new ConcurrentHashMap<>();
    }

    // Add product to inventory
    public void addProduct(String productId, int stock) {
        stockMap.put(productId, new AtomicInteger(stock));
        waitingList.put(productId, new LinkedHashMap<>());
    }

    // Check stock instantly O(1)
    public String checkStock(String productId) {

        AtomicInteger stock = stockMap.get(productId);

        if (stock == null) {
            return "Product not found";
        }

        return stock.get() + " units available";
    }

    // Purchase item
    public synchronized String purchaseItem(String productId, int userId) {

        AtomicInteger stock = stockMap.get(productId);

        if (stock == null) {
            return "Product not found";
        }

        // If stock available
        if (stock.get() > 0) {

            int remaining = stock.decrementAndGet();

            return "Success, " + remaining + " units remaining";
        }

        // If out of stock -> add to waiting list
        LinkedHashMap<Integer, Long> queue = waitingList.get(productId);

        queue.put(userId, System.currentTimeMillis());

        int position = queue.size();

        return "Added to waiting list, position #" + position;
    }

    // View waiting list
    public void printWaitingList(String productId) {

        LinkedHashMap<Integer, Long> queue = waitingList.get(productId);

        System.out.println("Waiting List:");

        int pos = 1;

        for (Integer user : queue.keySet()) {
            System.out.println("Position " + pos + " -> User " + user);
            pos++;
        }
    }
}

public class FlashSaleSystem {

    public static void main(String[] args) {

        InventoryManager manager = new InventoryManager();

        String product = "IPHONE15_256GB";

        manager.addProduct(product, 100);

        System.out.println(manager.checkStock(product));

        // Simulating purchases
        for (int i = 1; i <= 105; i++) {

            String result = manager.purchaseItem(product, i);

            System.out.println("User " + i + " -> " + result);
        }

        System.out.println(manager.checkStock(product));

        manager.printWaitingList(product);
    }
}