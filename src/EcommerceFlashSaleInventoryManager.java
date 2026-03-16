import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* =====================================================
   Problem 2: Flash Sale Inventory Manager
   ===================================================== */

class InventoryManager {

    private ConcurrentHashMap<String, AtomicInteger> stockMap;
    private ConcurrentHashMap<String, LinkedHashMap<Integer, Long>> waitingList;

    public InventoryManager() {
        stockMap = new ConcurrentHashMap<>();
        waitingList = new ConcurrentHashMap<>();
    }

    public void addProduct(String productId, int stock) {
        stockMap.put(productId, new AtomicInteger(stock));
        waitingList.put(productId, new LinkedHashMap<>());
    }

    public String checkStock(String productId) {

        AtomicInteger stock = stockMap.get(productId);

        if (stock == null) {
            return "Product not found";
        }

        return stock.get() + " units available";
    }

    public synchronized String purchaseItem(String productId, int userId) {

        AtomicInteger stock = stockMap.get(productId);

        if (stock == null) {
            return "Product not found";
        }

        if (stock.get() > 0) {

            int remaining = stock.decrementAndGet();

            return "Success, " + remaining + " units remaining";
        }

        LinkedHashMap<Integer, Long> queue = waitingList.get(productId);

        queue.put(userId, System.currentTimeMillis());

        int position = queue.size();

        return "Added to waiting list, position #" + position;
    }

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

/* =====================================================
   Problem 3: DNS Cache
   ===================================================== */

class DNSEntry {

    String ip;
    long expiry;

    DNSEntry(String ip, int ttl) {
        this.ip = ip;
        this.expiry = System.currentTimeMillis() + ttl * 1000;
    }

    boolean expired() {
        return System.currentTimeMillis() > expiry;
    }
}

class DNSCache {

    private LinkedHashMap<String, DNSEntry> cache;

    DNSCache(int capacity) {

        cache = new LinkedHashMap<String, DNSEntry>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> eldest) {
                return size() > capacity;
            }
        };
    }

    public synchronized String resolve(String domain) {

        if (cache.containsKey(domain)) {

            DNSEntry e = cache.get(domain);

            if (!e.expired())
                return "Cache HIT → " + e.ip;

            cache.remove(domain);
        }

        String ip = "172.217." + new Random().nextInt(255) + "." + new Random().nextInt(255);

        cache.put(domain, new DNSEntry(ip, 300));

        return "Cache MISS → " + ip;
    }
}

/* =====================================================
   Problem 5: Real-Time Analytics
   ===================================================== */

class AnalyticsSystem {

    HashMap<String, Integer> pageViews = new HashMap<>();
    HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    HashMap<String, Integer> sources = new HashMap<>();

    void processEvent(String url, String user, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(user);

        sources.put(source, sources.getOrDefault(source, 0) + 1);
    }

    void dashboard() {

        System.out.println("Top Pages:");

        pageViews.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(10)
                .forEach(e -> {

                    int unique = uniqueVisitors.get(e.getKey()).size();

                    System.out.println(e.getKey() + " → "
                            + e.getValue() + " views (" + unique + " unique)");
                });
    }
}

/* =====================================================
   Problem 6: Rate Limiter
   ===================================================== */

class TokenBucket {

    int tokens;
    int max;
    long lastRefill;

    TokenBucket(int max) {

        this.max = max;
        this.tokens = max;
        this.lastRefill = System.currentTimeMillis();
    }

    synchronized boolean allow() {

        long now = System.currentTimeMillis();

        if (now - lastRefill > 3600000) {
            tokens = max;
            lastRefill = now;
        }

        if (tokens > 0) {
            tokens--;
            return true;
        }

        return false;
    }
}

class RateLimiter {

    ConcurrentHashMap<String, TokenBucket> map = new ConcurrentHashMap<>();

    boolean check(String client) {

        map.putIfAbsent(client, new TokenBucket(1000));

        return map.get(client).allow();
    }
}

/* =====================================================
   Problem 8: Parking Lot
   ===================================================== */

class ParkingLot {

    String[] table;
    int size;

    ParkingLot(int size) {
        this.size = size;
        table = new String[size];
    }

    int hash(String plate) {
        return Math.abs(plate.hashCode()) % size;
    }

    void park(String plate) {

        int index = hash(plate);

        while (table[index] != null)
            index = (index + 1) % size;

        table[index] = plate;

        System.out.println("Vehicle parked at spot " + index);
    }
}

/* =====================================================
   Problem 9: Two Sum Fraud
   ===================================================== */

class Transaction {

    int id;
    int amount;

    Transaction(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}

class TwoSumDetector {

    void find(List<Transaction> list, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : list) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {

                System.out.println("Fraud Pair: "
                        + map.get(complement).id + " & " + t.id);
            }

            map.put(t.amount, t);
        }
    }
}

/* =====================================================
   MAIN PROGRAM
   ===================================================== */

public class Main {

    public static void main(String[] args) {

        System.out.println("FLASH SALE INVENTORY SYSTEM");

        InventoryManager manager = new InventoryManager();

        String product = "IPHONE15_256GB";

        manager.addProduct(product, 100);

        System.out.println(manager.checkStock(product));

        for (int i = 1; i <= 105; i++) {

            String result = manager.purchaseItem(product, i);

            System.out.println("User " + i + " -> " + result);
        }

        manager.printWaitingList(product);

        System.out.println("\nDNS CACHE");

        DNSCache dns = new DNSCache(100);

        System.out.println(dns.resolve("google.com"));

        System.out.println("\nREAL TIME ANALYTICS");

        AnalyticsSystem analytics = new AnalyticsSystem();

        analytics.processEvent("/news", "user1", "google");
        analytics.processEvent("/news", "user2", "facebook");

        analytics.dashboard();

        System.out.println("\nRATE LIMITER");

        RateLimiter limiter = new RateLimiter();

        System.out.println("Allowed: " + limiter.check("client1"));

        System.out.println("\nPARKING SYSTEM");

        ParkingLot lot = new ParkingLot(500);

        lot.park("ABC1234");

        System.out.println("\nTWO SUM FRAUD DETECTOR");

        List<Transaction> tx = new ArrayList<>();

        tx.add(new Transaction(1, 500));
        tx.add(new Transaction(2, 300));
        tx.add(new Transaction(3, 200));

        new TwoSumDetector().find(tx, 500);
    }
}
