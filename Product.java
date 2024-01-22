package OnlineShopingSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class Product {
    protected String type;
    protected String company;
    protected double price;
    protected int available = 5;
    protected int quantity = 1;
    protected int ID;
    private static int next_id = 1000;

    public Product(String type, String company, double price) {
        this.type = type;
        this.company = company;
        this.price = price;
        ID = next_id++;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFullPrice(){
        return price * quantity;
    }
    abstract void display();

    public void description(){
        System.out.println("The products we sell are in a demand around the world");
    }
    @Override
    public String toString() {
        return "Product{" +
                "type = '" + type + '\'' +
                ", company= '" + company + '\'' +
                ", price = " + price + "$" +
                ", quantity = " + quantity +
                ", ID = " + ID +
                "}\n";
    }
}
class Electronic extends Product{
    private String model;
    public Electronic(String type, String company,String model, double price) {
        super(type, company, price);
        this.model = model;
    }

    private String definingProduct() {
        return String.format("%s --> %s%nModel: %s%nCompany: %s%nAvailable: %d%nPrice: %.2f$%nID: %d",
                getClass().getSimpleName(),type,model,company,available,price,ID);
    }
    public void display(){
        System.out.println(definingProduct());
    };
    public void description(){
        display();
        System.out.println("Description:");
        String description = switch (type.toUpperCase()){
            case "PHONE" -> "The device ("+ model +") features a vibrant and expansive touchscreen display";
            case "LAPTOP" -> "Laptop(" + model + ")is a powerful and versatile computing device designed to\n" +
                    " meet the demands of both professional and personal users.";
            case "HEADPHONES" -> "The headphones("+ model +")are a premium audio accessory that delivers\n" +
                    " an unparalleled listening experience";
            default -> "";
        };
        System.out.println(description);
    }

}
class Cloth extends Product{
    private String material;
    public Cloth(String type, String company,String material, double price) {
        super(type, company, price);
        this.material = material;
    }

    private String definingProduct() {
        return String.format("%s --> %s%nCompany:%s%nMaterial: %s%n" +
                            "nAvailable: %d%nThe price is: %.2f$%nID: %d",
                    getClass().getSimpleName(),type,company,material,available,price,ID);
    }
    public void display(){
        System.out.println(definingProduct());
    };
    public void description(){
        display();
        System.out.println("Description:");
        String description = switch (type.toUpperCase()){
            case "HOODIE" -> "The(" +type+ ") is a versatile and comfortable garment" +
                    " that seamlessly combines style with functionality.";
            case "TROUSERS" -> "The("+type+ ") are a versatile and stylish wardrobe " +
                    "essential designed for both comfort and fashion.";
            case "T-SHIRT" -> "The("+ type +") is a classic and essential wardrobe staple " +
                    "that effortlessly blends comfort with casual style.";
            default -> "";
        };
        System.out.println(description);
    }
}
class Book extends Product {
    private int pages;
    private String plot;
    private static Random random = new Random();
    private static String[] plots = {
            "A story of a man in search of truth told with the simple clarity\n and beauty of" +
                    " Bunyan’s prose make this the ultimate English classic.",
            "By the end of the 19th century, no book in English literary history had enjoyed" +
                    " more editions, spin-offs\n and translations.Crusoe’s world-famous novel is a complex " +
                    "literary confection, and it’s irresistible.",
            "A satirical masterpiece that’s never been out of print, Jonathan Swift’s Gulliver’s\n " +
                    "Travels comes third in our list of the best novels written in English"};

    public Book(String type, String company, double price) {
        super(type, company, price);
        pages = random.nextInt(100, 500);
        plot = type.equalsIgnoreCase("adventure") ? plots[0] :
                type.equalsIgnoreCase("history") ? plots[1] :
                        plots[2];
    }

    private String definingProduct() {
        return String.format("%s --> %s%nPlot:%s%nPages: %d%nMade by %s%nAvailable: %d%n" +
                        "The price is: %.2f$%nID: %d",
                getClass().getSimpleName(), type, plot, pages, company, available,price, ID);
    }

    public void display(){
        System.out.println(definingProduct());
    }
    public void description(){
        display();
    }
}
class Catalog<T extends Product>{
   protected final Product[] products = {
            new Electronic("PHONE","Apple","Iphone",1299.99),
            new Electronic("LAPTOP","HP","Hp spectre",499.99),
            new Electronic("HEADPHONES","SAMSUNG","Buds", 199.9),
            new Cloth("hoodie","H&M","Cotton",29.99),
            new Cloth("trousers","Zara","polyester",39.99),
            new Cloth("T-SHIRT","Nike","Tech-Fleece",59.99),
            new Book("Adventure","John Bunyan",5.99),
            new Book("History","Daniel Defoe ",4.99),
            new Book("Travelling","Jonathan Swift",6.99)
    };
    private List<Product> catalog;
    public Catalog(){
        this.catalog = new ArrayList<>(List.of(products));
    }
    public void addItem(Product t){
        Iterator<Product> iterator = catalog.listIterator();
        while(iterator.hasNext()){
            Product i = iterator.next();
            if(i.equals(t)){
                return;
            }
        }
        catalog.add(t);
    }
    public void removeItem(Product t){
        Iterator<Product> iterator = catalog.listIterator();
        while(iterator.hasNext()){
            Product i = iterator.next();
            if(i.equals(t)){
                iterator.remove();
            }
        }
    }
    public void displayCatalog(){
        for(var t : catalog){
            t.display();
            System.out.println("-".repeat(30));
        }
    }

    public List<Product> getCatalog() {
        return catalog;
    }
}
