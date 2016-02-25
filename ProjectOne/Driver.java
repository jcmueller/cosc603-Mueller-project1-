import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Class Driver runs the program. It provides a series of prompts 
 * to receive the input needed to create a fireDangerSystemFacade.  The driver 
 * then calls calculateIndex() and displayIndex().  This performs the equations 
 * and displays the results producing the National Fire Danger Rating Indexes.
 * 
 * @author John Mueller
 */
public class Driver {
  
  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    /**
     * precipitation and snow are in inches
     * Snow helps take input from the user to set isSnowOnGround
     * windSpeed is in mph.
     * herbStateCode helps set the herb state enum.
     * Scanner is used to take input form the user. 
     */  
    boolean      isSnowOnGround = false;
    double      dryBulbTemperature, 
      wetBulbTemperature, 
      precipitation,
      snow,
      windSpeed, 
      previousBuildUpIndex;
    int      herbStateCode;
    FireDangerSystemFacade  fireDangerSystemFacade;
    HerbState     herbState = HerbState.GREEN;  
    Scanner     userInput = new Scanner(System.in);
    String errorMessage = "Error!\nThere was an error reading your input.\n"+
      "Please run the program and try again.";
    String[] 
      UserPrompts = {              // indexes
      "Please enter the dry bulb termperture:",     // 0 
      "Please enter the wet bulb termperture:",     // 1
      "Please enter the amount of percipitation in inches:",  // 2
      "Please enter the amount of snow on the ground in inches:", // 3
      "Please enter the wind speed in miles per hour:",   // 4
      "Please enter the last value of the Build Up Index:",  // 5
      "Herb state code :\n"+          // 6
      "1 = Cured\n"+            // 6
      "2 = Transition\n"+           // 6
      "3 = Green\n"+            // 6
      "Please enter the current herb state"+      // 6
      " of the district using the above code:"     // 6
    };
    
    try{
      
      System.out.println(UserPrompts[0]);
      dryBulbTemperature = userInput.nextDouble();
      System.out.println(UserPrompts[1]);
      wetBulbTemperature = userInput.nextDouble();
      System.out.println(UserPrompts[2]);
      snow = userInput.nextDouble();
      System.out.println(UserPrompts[3]);
      precipitation = userInput.nextDouble();
      System.out.println(UserPrompts[4]);
      windSpeed = userInput.nextDouble();
      System.out.println(UserPrompts[5]);
      previousBuildUpIndex = userInput.nextDouble();
      System.out.println(UserPrompts[6]);
      herbStateCode = userInput.nextInt();
      
      userInput.close();
      
      herbState =  herbState.getHerbState(herbStateCode);
      if(herbState == null)
        throw new InputMismatchException();
      
      if(snow<0)
        isSnowOnGround = true;
      
      fireDangerSystemFacade = 
        new FireDangerSystemFacade
        (dryBulbTemperature, wetBulbTemperature, isSnowOnGround,
         precipitation, windSpeed, previousBuildUpIndex, herbState);
      fireDangerSystemFacade.calculateIndex();
      fireDangerSystemFacade.displayIndex();
    }
    /*If the user enters a value wrong they are prompted and the program 
     * exits.
     */   
    catch(InputMismatchException inputMismatchException){
      System.out.println(errorMessage);
      System.exit(1);
    }
  }
}
