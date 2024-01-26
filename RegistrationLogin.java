package OnlineShopingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class RegistrationLogin {
    Catalog catalog = new Catalog();
    public static boolean loggedRegistered = false;
    private static final String USER_DATA_FILE = "userdata.txt";
    String username;
    private static Random random = new Random();

    //The main goal of this method is to convert file data to userData (hashMap)
    private Map<String, String> readUserData() {
        Map<String, String> userData = new HashMap<>();
        try (Scanner fileLine = new Scanner(new File(USER_DATA_FILE))) {
            while (fileLine.hasNext()) {
                String line = fileLine.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userData.put(parts[0].trim(), parts[1].trim());  // Trim whitespaces
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userData;
    }

    private void writeUserData(Map<String, String> userData) {//opposite it gets all userData and pushes it to file
        try (FileWriter fileWriter = new FileWriter(USER_DATA_FILE)) {
            for (Map.Entry<String, String> entry : userData.entrySet()) {
                fileWriter.write(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void login(Scanner scanner) {
        System.out.println("Enter username: ");
        username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        Map<String, String> userData = readUserData();
        if (userData.containsKey(username) && userData.get(username).equals(password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            loggedRegistered = true;
            if(loggedRegistered){
                action();
            }
        } else {
            System.out.println("Login failed. Invalid username or password.");
            loggedRegistered = false;
        }
    }

    void register(Scanner scanner) {

        System.out.println("Enter new username: ");
        username = scanner.nextLine();
        System.out.println("Enter new password: ");
        String password = scanner.nextLine();
        Map<String, String> userData = readUserData();

        if (!userData.containsKey(username)) {
            userData.put(username, password);
            writeUserData(userData);
            System.out.println("Registration successful. You can now log in with your new account.");
            loggedRegistered = true;
        } else {
            System.out.println("Registration failed. Username already exists.");
            loggedRegistered = false;
        }
    }
    void action() {
        Scanner scanner = new Scanner(System.in);
        boolean opt = true;
        User customer = new Customer(username, random.nextInt(17,22),
                10000);
        ShoppingCart cart = new ShoppingCart(catalog,customer);
        while (opt){
            System.out.println("Choose an action: ");
            System.out.println("1. Catalog");
            System.out.println("2. Purchase");
            System.out.println("3. Take a transaction");
            String action = scanner.nextLine();
            int nAction = Integer.parseInt(action);
            switch (nAction) {
                case 1 -> catalog.displayCatalog();
                case 2 -> {
                    System.out.println("Please provide an id of a product you want to buy");
                    String id = scanner.nextLine();
                    int nid = Integer.parseInt(id);
                    Iterator<Product> iterator = catalog.getCatalog().listIterator();
                    while(iterator.hasNext()){
                        Product i = iterator.next();
                        if(i.ID == nid){
                            cart.addItem(i);
                            customer.balance -= i.price;
                            if(customer.balance <= 0){
                                System.out.println("You do not have enough money to buy");
                            }
                            if(i.available == 0){
                                System.out.println("We do not have it anymore");
                            }
                        }
                        cart.displayCart();
                        catalog.displayCatalog();
                    }
                }
                case 3 -> {
                    System.out.println("Your transaction is made successfully");
                    customer.cart.clear();
                    System.out.println("Here is your current Shopping cart:");
                    cart.displayCart();
                }
                default -> {
                    opt = false;
                }
            }
        }
    }
}




