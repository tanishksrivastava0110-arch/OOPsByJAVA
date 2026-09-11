import java.util.*;

public class predefinedexceptions {
    public static void main(String[] args) {
        // 1. Move your variables and Scanner inside the method
        int ar[] = {1, 2, 3, 4};
        int i, b;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter index for array (i) and a number (b):");
        
        // 2. Now you can safely execute these lines
        i = sc.nextInt();
        b = sc.nextInt();
        try{
        System.out.println(ar[i]);
        System.out.println(ar[i]/b);
    }
    catch (Exception e){
        System.out.println("Not possible");
    }
        // Optional: Closing the scanner when done
        finally{ 
        sc.close();
        }
    }
}