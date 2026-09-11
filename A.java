<<<<<<< HEAD
public class A{
    int a,b,c;
    A(){
        System.out.println("Default");
    }
    A(int a){
        this.a=a;
    }
    A(int a,int b){
        this.a=a;
        this.b=b;
    }
    A(int a,int b,int c){
        this.a=a;
        this.b=b;
        this.c=c;
    }
    public static void main(String[] args) {
        A obj1=new A();
        A obj2=new A(10);
        System.out.println(obj1.a+" "+obj2.b+" "+ obj2.c);
        
    }
}
=======
public class A{
    int a,b,c;
    A(){
        System.out.println("Default");
    }
    A(int a){
        this.a=a;
    }
    A(int a,int b){
        this.a=a;
        this.b=b;
    }
    A(int a,int b,int c){
        this.a=a;
        this.b=b;
        this.c=c;
    }
    public static void main(String[] args) {
        A obj1=new A();
        A obj2=new A(10);
        System.out.println(obj1.a+" "+obj2.b+" "+ obj2.c);
        
    }
}
>>>>>>> b0b0f58f1fbfc65e0a0dad5d5d236e289822cefc
