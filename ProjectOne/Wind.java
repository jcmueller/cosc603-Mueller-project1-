
public class Wind
{
	private double grassSpreadIndex_;
	private double timberSpreadIndex_;
	private double windSpeed_;
	private double[] aSpreads	=	{0.01312,0.009184};
	private double[] bSpreads	=	{6, 14.4};
	private FineFuelMoisture fineFuelMoisture_;
  public Wind(double windSpeed) {
	  windSpeed_ = windSpeed;
  }
  public void calculate(FineFuelMoisture fineFuelMoisture)
  {
	  fineFuelMoisture_ = fineFuelMoisture;
	  if(fineFuelMoisture_.getMoisture()>33){
		  timberSpreadIndex_ = 1;
		  if(windSpeed_<14){
			  grassSpreadIndex_ = aSpreads[0]*(windSpeed_+bSpreads[0])*(Math.pow(33-fineFuelMoisture.getMoisture(), 1.65))-3;
			  if(grassSpreadIndex_ < 0)
				  grassSpreadIndex_ = 1;
		  }
		  else{
			  grassSpreadIndex_ = aSpreads[1]*(windSpeed_+bSpreads[1])*(Math.pow(33-fineFuelMoisture.getMoisture(), 1.65))-3;
		  }
	  }
	  else{
		  if(windSpeed_<14){
			  grassSpreadIndex_ = aSpreads[0]*(windSpeed_+bSpreads[0])*(Math.pow(33-fineFuelMoisture.getMoisture(), 1.65))-3;
			  timberSpreadIndex_ = aSpreads[0]*(windSpeed_+bSpreads[0])*(Math.pow(33-fineFuelMoisture.getAdjustedFuelMoisture(), 1.65))-3;
			  if(grassSpreadIndex_ < 0)
				  grassSpreadIndex_ = 1;
			  if(timberSpreadIndex_ < 1)
				  timberSpreadIndex_ = 1;
		  }
		  else{
			  grassSpreadIndex_ = aSpreads[1]*(windSpeed_+bSpreads[1])*(Math.pow(33-fineFuelMoisture.getMoisture(), 1.65))-3;
			  timberSpreadIndex_ = aSpreads[1]*(windSpeed_+bSpreads[1])*(Math.pow(33-fineFuelMoisture.getAdjustedFuelMoisture(), 1.65))-3;
			  if(grassSpreadIndex_ > 99)
				  grassSpreadIndex_ = 99;
			  if(timberSpreadIndex_ > 99)
				  timberSpreadIndex_ = 99;
		  }
	  }
  }
  public double getGrassSpreadIndex()
  {
	  return grassSpreadIndex_;
  }
  public double getTimberSpreadIndex()
  {
	  return timberSpreadIndex_;
  }
}
