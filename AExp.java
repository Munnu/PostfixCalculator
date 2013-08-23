import java.io.FileReader;
import java.io.Reader;
import java.util.StringTokenizer;
import java.io.*;

/**
 *  AExp takes user inputted expression, break it up
 * into tokens, input numbers and operators into tree
 * then, includes an option for the tree inputted to be
 * placed into postfix nortation and also the method to calculate 
 * items in the tree
 *
 *  @author  Monique Blake
 *  @version CSC 212, 6 April 2011
 */

public class AExp extends BinaryTree<Object> {
  /** Constructor for a single node */
  public AExp( Object data ) {
    super(data);
  }
  /** Accessor for left child */
  public AExp getLeft() {
    return (AExp)super.getLeft();
  }
  
  /** Accessor for right child */
  public AExp getRight() {
    return (AExp)super.getRight();
  }
  
  public void setLeft(BinaryTree<Object> left) {
    throw new UnsupportedOperationException();
  }
  
  public void setRight(BinaryTree<Object> right) {
    throw new UnsupportedOperationException();
  }
  
  public void setLeft(AExp left) {
    super.setLeft(left);
  }
  
  public void setRight(AExp right) {
    super.setRight(right);
  }
  
  /**
   * Stores the expression that user inputs into tree
   * 
   * @param st the expression user inputs broken up into individual tokens
   * @returns tree the 'tree' the values in the expression gets placed into
   * @throws NumberFormatException if the user doesn't enter the proper expression format
   */
  public static AExp storeIntoTree(StringTokenizer st) {
    String val ="";
    char operator = ' ';
    double number;
    AExp tree = new AExp(operator);
    AExp left;
    AExp right;
       
    val = st.nextToken();
    try {
      if(val.equals("(")) {
        // recursion on left
        // recursion call on the sub expression, store number
        left = storeIntoTree( st );
        
        // store operator in variable cast as a character
        val = st.nextToken();
        operator = val.charAt(0);
        // place in binary tree as a root
        tree.setData(operator);
        
        // store number as right on tree using recursion
        right = storeIntoTree( st );
        val = st.nextToken();
        
        // assemble tree
        tree.setLeft(left);
        tree.setRight(right);
        
        // return tree
        return tree;
      } else {
        // read number
        // assemble tree
        number = Double.valueOf(val.trim()).doubleValue();
        tree.setData(number);
        // return leaf
        return tree;
      }
    } catch (NumberFormatException e) {
      System.out.println("Error, cannot compute, wrong formatting. Example formatting: ((x operator y ) operator z) or (x operator y)");
      return null;
    }
  }
  
  /**
   * Takes the expression user inputted and turns it into post order notation
   * where the operators go after the numbers ex: (4+3) -> 43+
   * 
   * @param tree the 'Arithmetic expression tree' the values in the expression gets placed into
   */
  public static void postOrder (AExp tree) {
    
    if (!isEmpty(tree)) {
      // get left
      postOrder(tree.getLeft());
      // get right
      postOrder(tree.getRight());
      // get root
      // print out post order
      System.out.print(" "+tree.getData());
      
    }
  }
  
  /**
   * Calculates the expression stored.
   * 
   * @returns leafVal the number inputted if not an expression
   * @returns compVal the computed value
   * @throws ClassCastException if the user fails to input an expression
   * and calls compute expression
   */
  public double calculateTree () {
    
    double compVal = 0.0;
    double leafVal;
    try {
      if( isLeaf() ) {
        // convert value back to double and return number
        leafVal = (Double)getData();
        return leafVal;
      } else {
        // get tree on left
        double leftNum = getLeft().calculateTree();
        // get tree on right
        double rightNum = getRight().calculateTree();
        // get root
        if (getData().equals('+') ) {
          compVal = leftNum + rightNum;
        } else if (getData().equals('-')) {
          compVal = leftNum - rightNum;
        } else if (getData().equals('*')) {
          compVal = leftNum * rightNum;
        } else if (getData().equals('/')) {
          compVal = leftNum / rightNum;
        }
        return compVal;
      }
      
    } catch( ClassCastException e ) { 
      System.out.println( "Error, no expression inputted" );
      return 0;
    }
  }
}