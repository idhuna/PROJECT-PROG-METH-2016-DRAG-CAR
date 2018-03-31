package entity;


public class Gear {
	private static final int[] maxSpeedPerGear = {0,40,60,80,120,140,160};
	private static final int[] minSpeedPerGear = {0,0,10,20,30,40,45};
	private static final double[] AccelPerGear = {0,0.3,0.2,0.1,0.05,0.04,0.02};
	private static final double[] AccelRPM = {1,0.8,0.6,0.4,0.2,0.1,0.05};
	private static final double thresholdAccel = 0.0025;
	private static final double noKeyAccel = -0.55;
	

	private boolean TooMuchRPM;
	public int currentGear;
	public int currentMaxSpeed;
	public double RPM;
	public double preRPM;
	
	
	public Gear(){
		this.currentGear = 0;
		this.RPM = 9;
		this.currentMaxSpeed = maxSpeedPerGear[currentGear];
		this.TooMuchRPM = false;
	}
	
	public int getMaxSpeed(){
		return maxSpeedPerGear[currentGear];
	}
	
	public int getMinSpeed(){
		return minSpeedPerGear[currentGear];
	}
	
	public double getAcceleration(){
		return AccelPerGear[currentGear];
	}
	
	
	
	public double getRPM() {
		return RPM/100;
	}



	public synchronized String shiftGear(){
		if (currentGear >= 6) {
			return "";
		}
		else{
			this.currentGear++;
			RPM = RPM/5;
			if (RPM <= 9) RPM = 9;
			if (RPM < 50){
				
				return "Too Soon";	
			}
			else if (RPM < 60){
				return "Good";
			}
			else if(RPM < 70){
				return "Perfect";
			}
			else{
				return "Too Late";
			}
		}
	}
	public synchronized void decreaseGear(){
		if (currentGear <= 1) {
			return ;
		}
		else{
			this.currentGear--;
			RPM = RPM*5;
			if (RPM <= 0) RPM = 0;
		}
	}
	public int getCurrentGear() {
		return currentGear;
	}
	
	public synchronized void updateGear(){
		this.checkRPM();
		this.increaseRPM();
	}

	public void decreaseRPM(){
		if(this.RPM <= 9) this.RPM = 9;
		else this.RPM -= AccelRPM[currentGear];
	}
	
	public double decreaseAcceleration(){
		return noKeyAccel;
	}


	public void increaseRPM(){
		if (!TooMuchRPM){
			this.preRPM = RPM;
			this.RPM += AccelRPM[currentGear];
		}
		else{
			this.preRPM = RPM;
			this.RPM -= AccelRPM[currentGear];
		}
	}
	
	public void checkRPM(){
		double check = this.RPM + AccelRPM[currentGear];
		if (check >90){
			this.TooMuchRPM = true;
		}
		else if (check >85 && this.TooMuchRPM == true){
			return;
		}
		else{
			this.TooMuchRPM = false;
		}
	}
	
	public double getThresholdAccel() {
		return thresholdAccel;
	}

}
