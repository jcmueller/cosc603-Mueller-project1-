
public class FineFuelMoisture {
	private double[] aCoefficients_ 	= 	{ 30, 19.2, 13.8, 22.5};
	private double[] bCoefficients_ 	= 	{ -0.1859, -0.0859, -0.0579, -0.0774};
	private double[] cCoefficients_ 	= 	{ 4.5, 12.5, 27.5};
	private double[] dryingFactors_ 	= 	{ 16, 10, 7, 5, 4, 3};
	private double moisture_;
	private double dryingFactor_;
	private double bulbTemperatureDifference_;
	private double adjustedFuelMoisture_;
	private HerbState herbState_;
  public FineFuelMoisture() {
  }
  public void calculateAdjustedFuelMoisture(double buildUpIndex)
  {
	  adjustedFuelMoisture_ = 0.9*moisture_+9.5*Math.exp((-1*buildUpIndex)/50);  
  }
  public void calculate(double bulbTemperatureDifference, HerbState herbState)
  {
	  bulbTemperatureDifference_ = bulbTemperatureDifference;
	  herbState_ = herbState;
	  
	  if(bulbTemperatureDifference_<=cCoefficients_[0]){
		  moisture_ = aCoefficients_[0]*Math.exp(bCoefficients_[0]*bulbTemperatureDifference_);
	  }
	  else if(bulbTemperatureDifference_<=cCoefficients_[1]){
		  moisture_ = aCoefficients_[1]*Math.exp(bCoefficients_[1]*bulbTemperatureDifference_);
	  }
	  else if(bulbTemperatureDifference_<=cCoefficients_[2]){
		  moisture_ = aCoefficients_[2]*Math.exp(bCoefficients_[2]*bulbTemperatureDifference_);
	  }
	  else{
		  moisture_ = aCoefficients_[3]*Math.exp(bCoefficients_[3]*bulbTemperatureDifference_);
	  }
	  
	  if(moisture_ < 1)
		  moisture_ = 1;
	  
	  herbStateHelper();
	  dryingFactor();  
  }
  private void dryingFactor()
  {
	  if((moisture_ - dryingFactors_[0])>0){
		  dryingFactor_ = 0;
	  }
	  else if((moisture_ - dryingFactors_[1])>0){
		  dryingFactor_ = 1;
	  }
	  else if((moisture_ - dryingFactors_[2])>0){
		  dryingFactor_ = 2;
	  }
	  else if((moisture_ - dryingFactors_[3])>0){
		  dryingFactor_ = 3;
	  }
	  else if((moisture_ - dryingFactors_[4])>0){
		  dryingFactor_ = 4;
	  }
	  else if((moisture_ - dryingFactors_[5])>0){
		  dryingFactor_ = 5;
	  }
	  else{
		  dryingFactor_ = 7;
	  }
  }
  private void herbStateHelper()
  {
	  switch(herbState_){
	  case TRANSITION:
		  moisture_ += moisture_ * 0.05;
		  break;
	  case GREEN:
		  moisture_ += moisture_ * 0.1;
		  break;
	  default:
		  ;
		  break;
	  }
  }
  public double getAdjustedFuelMoisture()
  {
	  return adjustedFuelMoisture_;
  }
  public double getDryingfactor()
  {
	  return dryingFactor_;
  }
  public double getMoisture()
  {
	  return moisture_;
  }  
}
