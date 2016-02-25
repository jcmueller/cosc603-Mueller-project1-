
/**
 * The Class FireLoad calculates and stores the fire load index.
 * 
 * @author John Mueller
 */
public class FireLoad {
  
  private double fireLoad_;
  
  
  
  /**
   * Calculates the fire load.
   *
   * @param buildUpIndex the buildup index
   * @param grassSpreadIndex the grass spread index
   * @param timberSpreadIndex the timber spread index
   */
  public void calculate
    (double buildUpIndex, double grassSpreadIndex, double timberSpreadIndex)
  {
    if(timberSpreadIndex == 0 && buildUpIndex == 0){
      
      fireLoad_ = 0;
    }
    else{ 
      fireLoad_ = 1.75 * Math.log10(timberSpreadIndex) +
        0.32 * Math.log10(buildUpIndex) - 1.64;
      if(fireLoad_>0){
        fireLoad_ = Math.pow(10,fireLoad_);
      }
      else{
        fireLoad_ = 0;
      } 
    }
  }
  
  /**
   * this overload of calculate was created to facilitate when the fire load is 
   * automatically zero.
   */
  public void calculate()
  {
    fireLoad_ = 0;
  }
  
  /**
   * Gets the fire load.
   *
   * @return the fire load
   */
  public double getFireLoad()
  {
    return fireLoad_;
  }
}
