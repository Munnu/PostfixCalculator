import java.io.FileReader;
import java.io.Reader;
import java.util.StringTokenizer;
import java.io.*;

/**
 *  Calculator holds the menu, when the user presses a certain
 *  number then calculator will execute the proper command.
 *
 *  @author  Monique Blake
 *  @version CSC 212, 6 April 2011
 */

public class Calculator {
  public static void main(String[] args) throws IOException {
    // menu would be in here
    /** print statements for menu */
    System.out.println("Choose an option: ");
    System.out.println("1. Compute value. ");
    System.out.println("2. Print postfix. ");
    System.out.println("3. Enter new expression. ");
    System.out.println("4. Quit");
    
    /** gets input data */
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    /** reads the option inputted from the menu selection */
    String menuOpt = input.readLine();
    
    /** string tokenizer breaks up expression */
    StringTokenizer st;
    
    /** new method for converting expression to tree */
    AExp expressionMake = new AExp(0);
    
    
    while(!menuOpt.equals("4")) {
      if (menuOpt.equals("1")) {
        // call method to calculate value
        double answer = expressionMake.calculateTree();
        System.out.println("Computed Value: "+answer);
      } else if ( menuOpt.equals("2")) {
        //method that calls postOrder()
        System.out.println("Postorder of expression inputted ");
        AExp.postOrder(expressionMake);
      } else if ( menuOpt.equals("3")) {
        // reads in expression, breaks it up into tokens
        String equation = input.readLine();
        st = new StringTokenizer(equation,"()+-*/",true); 
        expressionMake = AExp.storeIntoTree(st);  
      } else {
        System.out.println("Error, type in either 1,2,3,4");
      }
      
      // menu would be in here, another one
      System.out.println();
      System.out.println("Choose an option: ");
      System.out.println("1. Compute value. ");
      System.out.println("2. Print postfix. ");
      System.out.println("3. Enter new expression. ");
      System.out.println("4. Quit");
      

      menuOpt = input.readLine();
    } 
  }
}