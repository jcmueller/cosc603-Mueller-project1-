
/**
 * The Class FineFuelMoisture.
 * @author John Mueller
 */
public class FineFuelMoisture {
  
  /** 
   * The A and B coefficients correspond to the A and B columns in the 
   * Fine Fuel Moisture equation.  The dry wet ranges correspond to this 
   * equation as well. The drying factors are determined from this equation. 
   */
  private double[]  aCoefficients_  =  { 30, 19.2, 13.8, 22.5},
    bCoefficients_  =  { -0.1859, -0.0859, 
      -0.0579, -0.0774},
        dryWetRanges_  =  { 4.5, 12.5, 27.5},
          dryingFactors_  =  { 16, 10, 7, 5, 4, 3};
          
          /** moisture is the fine fuel moisture */
          private double  moisture_,
            dryingFactor_,
            bulbTemperatureDifference_,
            adjustedFuelMoisture_;
          
          
          private HerbState herbState_;
          
          
          
          /**
           * Calculate adjusted fuel moisture.
           *
           * @param buildUpIndex the buildup index
           */
          public void calculateAdjustedFuelMoisture(double buildUpIndex)
          {
            adjustedFuelMoisture_ = 0.9*moisture_+9.5*Math.exp((-1*buildUpIndex)/50);  
          }
          
          
          
          /**
           * Calculate.
           *
           * @param bulbTemperatureDifference the difference between wet and dry bulbs
           * @param herbState the herb state
           */
          public void calculate(double bulbTemperatureDifference, HerbState herbState)
          {
            bulbTemperatureDifference_ = bulbTemperatureDifference;
            herbState_ = herbState;
            
            if(bulbTemperatureDifference_<=dryWetRanges_[0]){
              moisture_ = aCoefficients_[0]*Math.exp(bCoefficients_[0]*bulbTemperatureDifference_);
            }
            else if(bulbTemperatureDifference_<=dryWetRanges_[1]){
              moisture_ = aCoefficients_[1]*Math.exp(bCoefficients_[1]*bulbTemperatureDifference_);
            }
            else if(bulbTemperatureDifference_<=dryWetRanges_[2]){
              moisture_ = aCoefficients_[2]*Math.exp(bCoefficients_[2]*bulbTemperatureDifference_);
            }
            else{
              moisture_ = aCoefficients_[3]*Math.exp(bCoefficients_[3]*bulbTemperatureDifference_);
            }
            
            if(moisture_ < 1)
              moisture_ = 1;
            
            herbStateCalculate();
            dryingFactorCalculate();  
          }
          
          
          
          /**
           * Drying factor calculated from the fine fuel moisture
           */
          private void dryingFactorCalculate()
          {
            if((moisture_ - dryingFactors_[0])>0)   dryingFactor_ = 0;
            else if((moisture_ - dryingFactors_[1])>0) dryingFactor_ = 1;
            else if((moisture_ - dryingFactors_[2])>0) dryingFactor_ = 2;
            else if((moisture_ - dryingFactors_[3])>0) dryingFactor_ = 3;
            else if((moisture_ - dryingFactors_[4])>0) dryingFactor_ = 4;
            else if((moisture_ - dryingFactors_[5])>0) dryingFactor_ = 5;
            else dryingFactor_ = 7;
          }
          
          
          
          /**
           * Herb state effect of the fine fuel moisture.
           */
          private void herbStateCalculate()
          {
            switch(herbState_){
              case TRANSITION:
                moisture_ += moisture_ * 0.05;
                break;
                
              case GREEN:
                moisture_ += moisture_ * 0.1;
                break;
                
              default:
                break;
            }
          }
          
          
          
          /**
           * Gets the adjusted fuel moisture.
           *
           * @return the adjusted fuel moisture
           */
          public double getAdjustedFuelMoisture()
          {
            return adjustedFuelMoisture_;
          }
          
          
          
          /**
           * Gets the drying factor.
           *
           * @return the drying factor
           */
          public double getDryingfactor()
          {
            return dryingFactor_;
          }
          
          
          
          /**
           * Gets the fine fuel moisture.
           *
           * @return the fine fuel moisture
           */
          public double getMoisture()
          {
            return moisture_;
          }  
}
