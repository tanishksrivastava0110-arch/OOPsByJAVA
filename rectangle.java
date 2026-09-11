public class rectangle {
    int l,b;
    rectangle(int l,int b){
        this.l=l;
        this.b=b;
    }
    void area(){
        System.out.println("Area is: " + l*b);
    }
}
    class Main{
        public static void main(String[] args){
            rectangle r = new rectangle(5,23);
        
        r.area();
    }
}
