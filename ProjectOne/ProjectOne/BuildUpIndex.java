
/**
 * The Class BuildUpIndex handles calculating the buildup index.
 * @author John Mueller
 */
public class BuildUpIndex { 
  
  /** The precipitation_. */
  private double  previousBuildUpIndex_,
    buildUpIndex_,
    precipitation_;
  
  /**
   * Instantiates a new buildup index.
   *
   * @param previousBuildUpIndex The last value of the Buildup Index 
   */
  public BuildUpIndex(double previousBuildUpIndex) {
    previousBuildUpIndex_ = previousBuildUpIndex;
  }
  
  
  
  /**
   * Gets the buildup index.
   *
   * @return the buildup index
   */
  public double getBuildUpIndex()
  {
    return buildUpIndex_;
  }
  
  
  
  /**
   * Calculates the buildup index.
   */
  private void calculate()
  {
    precipitation_ -= 0.1;
    double i;
    i = -50*(Math.log(1-(-1*Math.exp((previousBuildUpIndex_)/50)*
                         Math.exp(-1.175*precipitation_))));
    
    if( i> 0) buildUpIndex_ = i;
    else  buildUpIndex_ = 0;
  }
  
  
  
  /**
   * Checks if enough rain has fallen and adjusts the buildup index if so.
   *
   * @param precipitation the rain in inches
   */
  public void precipitationCheck(double precipitation)
  {
    if(precipitation >= 0.1)
      calculate();
  }
}