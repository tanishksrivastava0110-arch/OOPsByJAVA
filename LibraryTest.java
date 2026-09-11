<<<<<<< HEAD
 class Book {
    private String name,auth,code;
    private double price;
    static int bookCount=0;
    static  final String libraryName="Sunrise Public Library";
    public Book (String name,String auth,double price,String code){
        this.name=name;
        this.auth=auth;
        this.price=price;
        this.code=code;
        bookCount++;
    }
    public String getTitle(){
        return this.name;
    }
    public String getAuthor(){
        return this.auth;
    }
    public double getPrice(){
        return this.price;
    }
}
public class LibraryTest {
    public static void main(String[] args) {
        Book b1 = new Book("Java Basics", "J. Author", 350.0, "ISBN001");
        Book b2 = new Book("OOP Concepts", "K. Writer", 420.0, "ISBN002");
 
        System.out.println(b1.getTitle() + " by " + b1.getAuthor());
        System.out.println("Price: " + b2.getPrice());
        System.out.println("Library: " + Book.libraryName);
        System.out.println("Total books added: " + Book.bookCount);
    }
}
=======
 class Book {
    private String name,auth,code;
    private double price;
    static int bookCount=0;
    static  final String libraryName="Sunrise Public Library";
    public Book (String name,String auth,double price,String code){
        this.name=name;
        this.auth=auth;
        this.price=price;
        this.code=code;
        bookCount++;
    }
    public String getTitle(){
        return this.name;
    }
    public String getAuthor(){
        return this.auth;
    }
    public double getPrice(){
        return this.price;
    }
}
public class LibraryTest {
    public static void main(String[] args) {
        Book b1 = new Book("Java Basics", "J. Author", 350.0, "ISBN001");
        Book b2 = new Book("OOP Concepts", "K. Writer", 420.0, "ISBN002");
 
        System.out.println(b1.getTitle() + " by " + b1.getAuthor());
        System.out.println("Price: " + b2.getPrice());
        System.out.println("Library: " + Book.libraryName);
        System.out.println("Total books added: " + Book.bookCount);
    }
}
>>>>>>> b0b0f58f1fbfc65e0a0dad5d5d236e289822cefc
