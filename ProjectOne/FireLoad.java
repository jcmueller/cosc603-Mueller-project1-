
public class FireLoad {
	private double fireLoad_;
  public FireLoad() {
  }
  public void calculate(double buildUpIndex,double grassSpreadIndex, double timberSpreadIndex)
  {
	if(timberSpreadIndex > 0 && buildUpIndex > 0){
		
	}
	else{ 
		fireLoad_ = 0;
	}
  }
  public double getFireLoad()
  {
	  return fireLoad_;
  }
}
