import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class Product implements Serializable {
    private String productID, name;
    private int quantity;
    private Location location;

    public Product(String productID, String name, int quantity, Location location) {
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.location = location;
    }

    public String getProductID() { 
        return productID; 
    }
    public String getName() { 
        return name; 
    }
    public int getQuantity() { 
        return quantity; 
    }
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
    }
    public Location getLocation() { 
        return location; 
    }

    @Override
    public String toString() {
        return "Product : " + name + ", ID: " + productID + ", Quantity: " + quantity + ", Location: " + location;
    }
}

class Location implements Serializable {
    private int aisle, shelf, bin;

    public Location(int aisle, int shelf, int bin) {
        this.aisle = aisle;
        this.shelf = shelf;
        this.bin = bin;
    }

    @Override
    public String toString() {
        return "Aisle " + aisle + ", Shelf " + shelf + ", Bin " + bin;
    }
}

class OutOfStockException extends Exception {
    public OutOfStockException(String message) { 
        super(message); 
    }
}

class Order {
    private String orderID;
    private List<String> productIDs;
    private Priority priority;

    public Order(String orderID, List<String> productIDs, Priority priority) {
        this.orderID = orderID;
        this.productIDs = productIDs;
        this.priority = priority;
    }

    public String getOrderID() { 
        return orderID; 
    }
    public List<String> getProductIDs() { 
        return productIDs; 
    }
    public Priority getPriority() { 
        return priority; 
    }

    public enum Priority { 
        STANDARD, 
        EXPEDITED 
    }

    @Override
    public String toString() {
        return "Order ID : " + orderID + ", Priority : " + priority;
    }
}

class OrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getPriority().compareTo(o2.getPriority());
    }
}

class InventoryManager {
    private Map<String, Product> products = new ConcurrentHashMap<>();
    private PriorityQueue<Order> orderQueue = new PriorityQueue<>(new OrderComparator());

    public synchronized void addProduct(Product product) {
        products.put(product.getProductID(), product);
    }

    public synchronized void processOrder() {
        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll();
            System.out.println("Processing : " + order);
            
            for (String productID : order.getProductIDs()) {
                try {
                    fulfillOrder(productID);
                } catch (OutOfStockException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private synchronized void fulfillOrder(String productID) throws OutOfStockException {
        Product product = products.get(productID);
        if (product == null || product.getQuantity() == 0) {
            throw new OutOfStockException("Product " + productID + " is out of stock.");
        }
        product.setQuantity(product.getQuantity() - 1);
        System.out.println("Order fulfilled: " + product.getName());
    }

    public void addOrder(Order order) {
        orderQueue.add(order);
    }

    public void saveInventory() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("inventory.txt"))) {
            oos.writeObject(products);
        }
    }

    public void loadInventory() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("inventory.txt"))) {
            products = (Map<String, Product>) ois.readObject();
        }
    }
}

public class WareHouse{
    public static void main(String[] args) {
        InventoryManager inventory = new InventoryManager();

        inventory.addProduct(new Product("P001", "Laptop", 10, new Location(1, 2, 3)));
        inventory.addProduct(new Product("P002", "Phone", 5, new Location(2, 3, 1)));

        Order order1 = new Order("O001", Arrays.asList("P001", "P002"), Order.Priority.EXPEDITED);
        Order order2 = new Order("O002", Arrays.asList("P002"), Order.Priority.STANDARD);

        inventory.addOrder(order1);
        inventory.addOrder(order2);

        Thread t1 = new Thread(() -> inventory.processOrder());
        Thread t2 = new Thread(() -> inventory.processOrder());

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

        try {
            inventory.saveInventory();
            System.out.println("Inventory saved.");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }
}
