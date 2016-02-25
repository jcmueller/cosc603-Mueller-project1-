
/**
 * The Class FireDangerSystemFacade uses its components to generate the National
 * Fire Rating Indexes. IT also stores these results and can display them.  This
 * class does most of the work and all of the coordination in this program.
 * @author John Mueller
 */
public class FireDangerSystemFacade {
  
  private boolean    isSnowOnGround_;
  
  private double     bulbTemperatureDifference_, 
    precipitation_, 
    grassSpreadIndex_, 
    timberSpreadIndex_, 
    buildUpIndexTotal_;
  
  private BuildUpIndex   buildUpIndex_;
  
  private FineFuelMoisture  fineFuelMoisture_ = new FineFuelMoisture();
  
  private FireLoad    fireLoad_ = new FireLoad();
  
  private HerbState    herbState_;
  
  private TimberSpreadIndex       wind_;
  
  private String[]    results =  {      //indexes
    "The result for the drying factor is: ",      //0
    "The result for the fine fuel moisture is: ",     //1
    "The result for the adjusted (10 day lag) fuel moisture is: ", //2
    "The result for the fine fuel spread is: ",     //3
    "The result for the timber spread index is: ",     //4
    "The result for the fire load rating (man-hour base) is: ",  //5
    "The result for the buildup index is: "};      //6
  
  /**
   * Instantiates a new fire danger system facade.
   *
   * @param dryBulbTemperature the dry bulb temperature
   * @param wetBulbTemperature the wet bulb temperature
   * @param isSnowOnGround is true if there is snow on the ground
   * @param percipitation the rain in inches
   * @param windSpeed the wind speed in mph
   * @param previousBuildUpIndex the previous buildup index
   * @param herbState the herb state
   */
  public FireDangerSystemFacade
    (double dryBulbTemperature, double wetBulbTemperature, 
     boolean isSnowOnGround, double percipitation, double windSpeed,
     double previousBuildUpIndex, HerbState herbState) 
  {
    bulbTemperatureDifference_ = dryBulbTemperature + wetBulbTemperature;
    isSnowOnGround_ = isSnowOnGround;
    precipitation_ = percipitation;
    wind_ = new TimberSpreadIndex(windSpeed);
    buildUpIndex_ = new BuildUpIndex(previousBuildUpIndex);
    herbState_ = herbState;
  }
  
  /**
   * Calculates the National Fire Rating Indexes.  It first checks for snow.
   * If there is no snow it calculates the fine fuel moisture.  Depending on 
   * the fine fuel moisture level wind adjustments are applied.  Then, 
   * regardless of the wind, the fire load is calculated.
   */
  public void calculateIndex()
  {
    if(!isSnowOnGround_){
      fineFuelMoisture_.calculate(bulbTemperatureDifference_, herbState_);
      buildUpIndex_.precipitationCheck(precipitation_);
      buildUpIndexTotal_ = buildUpIndex_.getBuildUpIndex()
        +fineFuelMoisture_.getDryingfactor();
      fineFuelMoisture_.calculateAdjustedFuelMoisture(buildUpIndexTotal_);
      
      if(fineFuelMoisture_.getAdjustedFuelMoisture()>33
           &&fineFuelMoisture_.getMoisture()>33){
        grassSpreadIndex_ = 1;
        timberSpreadIndex_ = 1;
        fireLoad_.calculate();
      }
      
      else
      {
        wind_.calculate(fineFuelMoisture_);
        grassSpreadIndex_ = wind_.getGrassSpreadIndex();
        timberSpreadIndex_ = wind_.getTimberSpreadIndex();
        fireLoad_.calculate
          (buildUpIndexTotal_, grassSpreadIndex_, timberSpreadIndex_);
      }
    }
    
    else{
      grassSpreadIndex_ = 0;
      timberSpreadIndex_ = 0;
      fireLoad_.calculate();
      buildUpIndex_.precipitationCheck(precipitation_);
      buildUpIndexTotal_ = buildUpIndex_.getBuildUpIndex();
    }
    
  }
  
  /**
   * Displays the National Fire Rating Indexes.
   */
  public void displayIndex()
  {
    System.out.println(results[0]+fineFuelMoisture_.getDryingfactor());
    System.out.println(results[1]+fineFuelMoisture_.getMoisture());
    System.out.println
      (results[2]+fineFuelMoisture_.getAdjustedFuelMoisture());
    System.out.println(results[3]+grassSpreadIndex_);
    System.out.println(results[4]+timberSpreadIndex_);
    System.out.println(results[5]+fireLoad_.getFireLoad());
    System.out.println(results[6]+buildUpIndexTotal_);
  }
  
}
