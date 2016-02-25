
/**
 * The Class TimberSpreadIndex calculates and stores the timber spread index and 
 * the fine fuel spread.
 * @author John Mueller
 */
public class TimberSpreadIndex
{
  
  private double   grassSpreadIndex_,
    timberSpreadIndex_,
    windSpeed_;
  
  /** The A and B columns from the timber spread index equation. */
  private double[]  aSpreads = {0.01312,  0.009184},
    bSpreads = {6,   14.4};
    
    private FineFuelMoisture fineFuelMoisture_;
    
    /**
     * Instantiates a new timber spread index.
     *
     * @param windSpeed the wind speed in mph
     */
    public TimberSpreadIndex(double windSpeed) {
      windSpeed_ = windSpeed;
    }
    
    
    
    /**
     * Calculate the timber spread index and the fine fuel spread
     *
     * @param fineFuelMoisture the fine fuel moisture
     */
    public void calculate(FineFuelMoisture fineFuelMoisture)
    {
      fineFuelMoisture_ = fineFuelMoisture;
      
      if(fineFuelMoisture_.getMoisture()>33){
        timberSpreadIndex_ = 1;
        if(windSpeed_<14){
          grassSpreadIndex_ = aSpreads[0]*(windSpeed_+bSpreads[0])*
            (Math.pow(33-fineFuelMoisture.getMoisture(), 
                      1.65))-3;
          
          if(grassSpreadIndex_ < 0)
            grassSpreadIndex_ = 1;
        }
        else{
          grassSpreadIndex_ = aSpreads[1]*(windSpeed_+bSpreads[1])*
            (Math.pow(33-fineFuelMoisture.getMoisture(),
                      1.65))-3;
        }
        
      }
      else{
        
        if(windSpeed_<14){
          grassSpreadIndex_ = aSpreads[0]*(windSpeed_+bSpreads[0])*
            (Math.pow(33-fineFuelMoisture.getMoisture(),
                      1.65))-3;
          timberSpreadIndex_ = aSpreads[0]*(windSpeed_+bSpreads[0])*
            (Math.pow
               (33-fineFuelMoisture.getAdjustedFuelMoisture(),
                1.65))-3;
          
          if(grassSpreadIndex_ < 0)
            grassSpreadIndex_ = 1;
          
          if(timberSpreadIndex_ < 1)
            timberSpreadIndex_ = 1;
          
        }
        else{
          grassSpreadIndex_ = aSpreads[1]*(windSpeed_+bSpreads[1])*
            (Math.pow(33-fineFuelMoisture.getMoisture(),
                      1.65))-3;
          timberSpreadIndex_ = aSpreads[1]*(windSpeed_+bSpreads[1])*
            (Math.pow(
                      33-fineFuelMoisture.getAdjustedFuelMoisture(),
                      1.65))-3;
          
          if(grassSpreadIndex_ > 99)
            grassSpreadIndex_ = 99;
          
          if(timberSpreadIndex_ > 99)
            timberSpreadIndex_ = 99;
        }
      }
    }
    
    
    
    /**
     * Gets the fine fuel spread.
     *
     * @return the grass spread index
     */
    public double getGrassSpreadIndex()
    {
      return grassSpreadIndex_;
    }
    
    
    
    /**
     * Gets the timber spread index.
     *
     * @return the timber spread index
     */
    public double getTimberSpreadIndex()
    {
      return timberSpreadIndex_;
    }
}
