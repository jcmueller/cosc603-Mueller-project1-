
public class FireDangerSystemFacade {
	private boolean isSnowOnGround_;
	private double bulbTemperatureDifference_;
	private double percipitation_;
	private double grassSpreadIndex_;
	private double timberSpreadIndex_;
	private double fireLoadRating_;
	private double buildUpIndexTotal_;
	private BuildUpIndex buildUpIndex_;
	private FineFuelMoisture fineFuelMoisture_ = new FineFuelMoisture();
	private FireLoad fireLoad_ = new FireLoad;
	private HerbState herbState_;
	private Wind   wind_;
  public FireDangerSystemFacade
  (double dryBulbTemperature, double wetBulbTemperature, boolean isSnowOnGround, double percipitation, double windSpeed, double previousBuildUpIndex, HerbState herbState) 
  {
	  bulbTemperatureDifference_ = dryBulbTemperature + wetBulbTemperature;
	  isSnowOnGround_ = isSnowOnGround;
	  percipitation_ = percipitation;
	  wind_ = new Wind(windSpeed);
	  buildUpIndex_ = new BuildUpIndex(previousBuildUpIndex);
	  herbState_ = herbState;
  }
    public void calculateIndex()
    {
    	if(!isSnowOnGround_){
    	fineFuelMoisture_.calculate(bulbTemperatureDifference_, herbState_);
    	buildUpIndex_.pecipitationCheck(percipitation_);
    	buildUpIndexTotal_ = buildUpIndex_.getBuildUpIndex()+fineFuelMoisture_.getDryingfactor();
    	fineFuelMoisture_.calculateAdjustedFuelMoisture(buildUpIndexTotal_);
    		if(fineFuelMoisture_.getAdjustedFuelMoisture()>33&&fineFuelMoisture_.getMoisture()>33){
            	grassSpreadIndex_ = 1;
            	timberSpreadIndex_ = 1;	
    		}
    		else
    		{
    		wind_.calculate(fineFuelMoisture_);
    	   	grassSpreadIndex_ = wind_.getGrassSpreadIndex();
        	timberSpreadIndex_ = wind_.getTimberSpreadIndex();
        	fireLoad_.calculate(buildUpIndexTotal_, grassSpreadIndex_, timberSpreadIndex_);
    		}
    	}
    	else{
        	grassSpreadIndex_ = 0;
        	timberSpreadIndex_ = 0;	
        	buildUpIndex_.pecipitationCheck(percipitation_);
        	buildUpIndexTotal_ = buildUpIndex_.getBuildUpIndex();
    	}
    		
    }
    public void displayIndex()
    {
    	
    }
    
}
