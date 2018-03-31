package entity;


import model.Mini;


public class MyCar extends Car {
	
	private Gear gear;

	private int position;
	
	public MyCar(){
		super();
		this.position = 1;
		this.gear = new Gear();
		this.z = 0;
		this.model = new Mini();
		this.model.setZ(z);
		
		
	}
	

	public synchronized Gear getGear() {
		return gear;
	}



	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	

}
