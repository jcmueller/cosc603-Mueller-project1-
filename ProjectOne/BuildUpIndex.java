
public class BuildUpIndex { 
	private double previousBuildUpIndex_;
	private double buildUpIndex_;
	private double percipitation_;
	public BuildUpIndex(double previousBuildUpIndex) {
		previousBuildUpIndex_ = previousBuildUpIndex;
	}
	public double getBuildUpIndex()
	{
		if(this.buildUpIndex_>0)
			return this.buildUpIndex_;
		else
			return 0;
	}
	
	private void calculate()
	{
		percipitation_ -= 0.1;
		double i;
		i = -50*(Math.log(1-(-1*Math.exp((previousBuildUpIndex_)/50)*Math.exp(-1.175*percipitation_))));
		if(i>0)
			this.buildUpIndex_ = i;
		else
			this.buildUpIndex_ = 0;
	}
	
	public void pecipitationCheck(double percipitation)
	{
		if(percipitation>=0.1)
			this.calculate();
	}
}