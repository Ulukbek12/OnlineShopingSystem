package OnlineShopingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static OnlineShopingSystem.RegistrationLogin.login;
import static OnlineShopingSystem.RegistrationLogin.register;

//        Product phone = new Electronic("PHONE","Apple","Iphone",
//                1299.99);
//        Product laptop = new Electronic("LAPTOP","HP","Hp spectre",
//                499.99);
//        Product headphones = new Electronic("HEADPHONES","SAMSUNG","Buds",
//                199.9);
//        Product hoodie = new Cloth("hoodie","H&M","Cotton",29.99);
//        Product trousers = new Cloth("trousers","Zara","polyester",39.99);
//        Product tShirt = new Cloth("T-SHIRT","Nike","Tech-Fleece",59.99);
//
//        Product pilgrim = new Book("Adventure","John Bunyan",5.99);
//        Product crusoe = new Book("History","Daniel Defoe ",4.99);
//        Product gulliver = new Book("Travelling","Jonathan Swift",6.99);
//        Catalog<Product> catalog = new Catalog<>(phone,laptop,headphones,hoodie,
//                trousers,tShirt,pilgrim,crusoe,gulliver);
//        User customer = new Customer("Tim",22,10000.0);
//        ShoppingCart cart = new ShoppingCart(catalog,customer);
//        cart.addItem(phone);
//        cart.displayCart();
//        catalog.displayCatalog();
//        Product[] products = {
//                new Electronic("PHONE","Apple","Iphone",1299.99),
//                new Electronic("LAPTOP","HP","Hp spectre",499.99),
//                new Electronic("HEADPHONES","SAMSUNG","Buds", 199.9),
//                new Cloth("hoodie","H&M","Cotton",29.99),
//                new Cloth("trousers","Zara","polyester",39.99),
//                new Cloth("T-SHIRT","Nike","Tech-Fleece",59.99),
//                new Book("Adventure","John Bunyan",5.99),
//                new Book("History","Daniel Defoe ",4.99),
//                new Book("Travelling","Jonathan Swift",6.99)
//        };
//        Catalog<Product> catalog = new Catalog<>();
//        User customer = new Customer("Tim",22,10000.0);
//        ShoppingCart cart = new ShoppingCart(catalog,customer);
//             cart.addItem(products[0]);
//            cart.displayCart();
//             catalog.displayCatalog();
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean app = true;
        try {
            while (app) {
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character

                switch (choice) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        register(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting the application. Goodbye!");
                        System.exit(0);
                        app = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }catch (InputMismatchException e){
            System.out.println("Only numbers are available");
        }
    }
}


class RegistrationLogin {
    private static final String USER_DATA_FILE = "userdata.txt";
    User customer;
    //The main goal of this method is to convert file data to userData (hashMap)
    private static Map<String,String> readUserData(){
        Map<String,String> userData = new HashMap<>();
        try(Scanner fileLine = new Scanner(new File(USER_DATA_FILE))){
            while(fileLine.hasNext()){
                String line = fileLine.nextLine();
                String[] parts = line.split(",");
                if(parts.length == 2){
                    userData.put(parts[0],parts[1]);
                }
            }
        }
        catch (IOException e){

        }
        return userData;
    }
    private static void writeUserData(Map<String,String> userData){//opposite it gets all userData and pushes it to file
        try(FileWriter fileWriter = new FileWriter(USER_DATA_FILE)){
            for(Map.Entry<String,String> entry : userData.entrySet()){
                fileWriter.write(entry.getKey() + "," + entry.getValue());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    static void login(Scanner scanner) {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        Map<String,String> userData = readUserData();
        if(userData.containsKey(username) && userData.get(username).equals(password)) {
            System.out.println("Login successful. Welcome, " + username + "!");

        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }
    static void register(Scanner scanner){
        System.out.println("Enter new username: ");
        String username = scanner.nextLine();
        System.out.println("Enter new password: ");
        String password = scanner.nextLine();
        Map<String,String> userData = readUserData();

        if(!userData.containsKey(username)){
            userData.put(username,password);
            writeUserData(userData);

            User customer = new Customer(username,22,10000.0);
//            ShoppingCart cart = new ShoppingCart(catalog,customer);
            System.out.println("Registration successful. You can now log in with your new account.");
        } else {
            System.out.println("Registration failed. Username already exists.");
        }
    }
}

