package OnlineShopingSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

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
    static Scanner scanner = new Scanner(System.in);
    static RegistrationLogin registrationLogin = new RegistrationLogin();
    static boolean  app = true;
    public static void main(String[] args) {
        beginning();

    }
    static void beginning(){
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
                        registrationLogin.login(scanner);
                        if(RegistrationLogin.loggedRegistered){
                            app = false;
                        }
                        break;
                    case 2:
                        registrationLogin.register(scanner);
                        if(RegistrationLogin.loggedRegistered){
                            app = false;
                        }
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



