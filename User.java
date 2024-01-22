package OnlineShopingSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class User {
    protected String name;
    protected int age;
    protected int ID;
    private static int NEXT_ID = 100;
    protected double balance;
    protected List<Product> cart = new ArrayList<>();
    public User(String name, int age) {
        this.name = name;
        this.age = age;
        ID = NEXT_ID++;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", ID=" + ID +
                '}';
    }
}
class Customer extends User{
    private String occupation;
    protected Random random = new Random();
    private static String[] occupations = {"Web developer", "Cashier","Teacher","Waiter"};

    public Customer(String name, int age,double balance) {
        super(name, age);
        this.balance = balance;
        occupation = occupations[random.nextInt(0,4)];
    }

    @Override
    public String toString() {
        return "Customer{" +
                "balance=" + balance +
                ", occupation='" + occupation + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", ID=" + ID +
                ", cart=" + cart +
                "} ";

    }

}
class ShoppingCart {
    private List<Product> catalog;
    private User customer;

    public ShoppingCart(Catalog<Product> catalog, User customer) {
        this.catalog = catalog.getCatalog();
        this.customer = customer;
    }

    public void addItem(Product product) {
        if (catalog != null) {
            Iterator<Product> iterator = catalog.listIterator();
            while (iterator.hasNext()) {
                Product i = iterator.next();
                if (i.equals(product)) {
                    customer.cart.add(product);
                    product.available--;
                    if (product.available <= 0) {
                        iterator.remove();
                    }
                    break; // Move break statement inside the if block
                }
            }
        }
    }

    public void removeItem(Product product){
        if(catalog != null){
            Iterator<Product> iterator = customer.cart.listIterator();
            while(iterator.hasNext()){
                Product i = iterator.next();
                if(i.equals(product)){
                    iterator.remove();
                    product.available++;
                    if (product.available >= 5) {
                        return;
                    }
                }
            }
        }
    }

    public void displayCart(){
        System.out.print(customer.name + "'s " + getClass().getSimpleName() + ": ");
        System.out.println("Balance -- > " + customer.balance  + "$:");
        double sum = 0;
        for( var c : customer.cart){
            System.out.print(c);
            sum += c.price;
        }
        System.out.println("Full price: "+ sum+ "$" );
        System.out.println("-".repeat(30));
    }
}
