public class Vector 
{
	private double x, y;
	
	public Vector(double x, double y, boolean isAngle)
	{
		if(!isAngle)
		{
			this.x = x;
			this.y = y;
		}
		else
		{
			this.x = x*Math.cos(y);
			this.y = x*Math.sin(y);
		}
	}
	
	public double getX() {return x;}
	public double getY() {return y;}
	public double getMag()
	{
		double hypo = Math.hypot(getX(), getY());
		if(Math.abs(Math.round(hypo)-hypo)<0.01) return (double)Math.round(hypo);
		else return hypo;
	}
	public double getAngle()
	{
		int quadrant;
		if(getX()>=0&&getY()>=0) quadrant = 1;
		else if (getX()<0&&getY()>=0) quadrant = 2;
		else if (getX()<0&&getY()<0) quadrant = 3;
		else quadrant = 4;
		double raa = Math.atan(Math.abs(getY()/getX()));
		switch(quadrant)
		{
		case 1: return raa; 
		case 2: return Math.PI-raa; 
		case 3: return Math.PI+raa;
		default: return Math.PI*2-raa;
		}
	}
	
	public void setX(double a) {x = a;}
	public void setY(double a) {y = a;}
	
	public int getAngleDeg()
	{
		return (int)Math.round(getAngle()*180/Math.PI);
	}
}
