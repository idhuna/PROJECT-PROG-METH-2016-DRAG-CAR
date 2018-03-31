package entity;

import model.CarModel;

public abstract class Car {
	
	protected int z;
	private double distance;
	public double speed;
	public double acceleration;
	protected CarModel model;
	
	

	public Car() {
		super();
		this.speed = 0;
		this.acceleration = 0;
		this.distance = 0;
	}
	
	
	public double getDistance() {
		return distance;
	}


	public void increaseDistance(){
		
		//animationTimer updates every 1/60 seconds
		//calculation will be new speed (m/1/60 s) = speed(mph) *1.60934*1000/(3600*60)
		//distance is in Meters
		this.distance += speed*1.60934/216;
	}


	public synchronized void setAcceleration(double acceleration) {
		if (speed<=0 && acceleration < 0) return;
		this.acceleration = acceleration;
	}


	public synchronized double getSpeed() {
		return speed;
	}


	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public synchronized void increaseSpeed(){
		if(speed <= 0 && this.acceleration < 0) return;
		this.speed += acceleration;
		if(this.speed < 0) this.speed = 0;
	}

	public CarModel getModel() {
		return model;
	}


	public void setModel(CarModel model) {
		this.model = model;
	}


	

}
