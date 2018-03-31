package lib;

import javafx.scene.media.AudioClip;

public class AudioUtility {

	private static final String PAINT = "sound/PAINT.mp3";
	private static final String START_ENGINE = "sound/START_ENGINE.mp3";// start																		// race
	private static final String ENGINE = "sound/ENGINE.mp3";// When acceleration
	private static final String CHANG_GEAR = "sound/CHANGE_GEAR.mp3";
	private static final String ENGINE_IDLE = "sound/engineidle.mp3"; //idle
	private static final String LONG_ENGINE = "sound/LONGENGINE.mp3";
	private static final String BACKGROUND_SONG = "sound/backgroundsong.mp3";
	public static final double[] ENGINE_SOUND_RATE_PER_GEAR = {2,1.6,1.2,0.8,1,1,1};
	private static AudioClip sound_paint;
	private static AudioClip sound_start_engine;
	private static AudioClip sound_long_engine;
	private static AudioClip sound_engine;
	private static AudioClip sound_change_gear;
	private static AudioClip sound_engine_idle;
	private static AudioClip sound_background;
	static {
		loadResource();
	}

	public static void loadResource() {
		sound_paint = new AudioClip(ClassLoader.getSystemResource(PAINT).toString());
		sound_start_engine = new AudioClip(ClassLoader.getSystemResource(START_ENGINE).toString());
		sound_engine = new AudioClip(ClassLoader.getSystemResource(ENGINE).toString());
		sound_long_engine = new AudioClip(ClassLoader.getSystemResource(LONG_ENGINE).toString());
		sound_change_gear = new AudioClip(ClassLoader.getSystemResource(CHANG_GEAR).toString());
		sound_engine_idle = new AudioClip(ClassLoader.getSystemResource(ENGINE_IDLE).toString());
		sound_background = new AudioClip(ClassLoader.getSystemResource(BACKGROUND_SONG).toString());
		sound_background.setCycleCount(AudioClip.INDEFINITE);
	}
	
	public static void playBackGround(){
		//sound_background.play();
	}
	
	public static void setBackGroundVolume(double amount){
		sound_background.setVolume(amount);
	}
	

	public static AudioClip getEngineSound() {
		return sound_engine;
	}
	
	public static AudioClip getLongEngineSound() {
		return sound_long_engine;
	}



	public static void setSound_engine(AudioClip sound_engine) {
		AudioUtility.sound_engine = sound_engine;
	}



	public static void playSound(String identifier) {
		if (identifier == "paint")
			sound_paint.play();
		if (identifier == "start_engine")
			sound_start_engine.play();
		if (identifier == "engine")
			sound_engine.play();
		if (identifier == "change_gear")
			sound_change_gear.play();
		if (identifier == "engine_idle")
			sound_engine_idle.play();
		if (identifier == "long_engine")
			sound_long_engine.play();
	}
	
	public static void playLongEngine() {
		if (sound_long_engine.isPlaying()) return;
		
		if (sound_engine_idle.isPlaying()) sound_engine_idle.stop();
		
		if (sound_engine.isPlaying()) sound_engine.stop();
		
		sound_long_engine.play();
	}

	public static void playEngine() {
		
		if (sound_engine.isPlaying())
			return;
		else if (sound_engine.getCycleCount() > 1) {
			sound_engine.setRate(5);
			sound_engine.play();
		} 
		if (sound_engine_idle.isPlaying()) sound_engine_idle.stop();
		
		if (sound_long_engine.isPlaying()) sound_long_engine.stop();
		
		sound_engine.play();
	}

	public static void changeGear() {
		if ( (!sound_engine.isPlaying() )&&sound_change_gear.isPlaying())
			return;
		else if(sound_engine.isPlaying()&& ( !sound_change_gear.isPlaying()) ){
			sound_engine.stop();
			sound_change_gear.play();
		}
		else
			playEngine();
			return;
	}
	
	public static void playEngineIdle(){
		if(sound_engine_idle.isPlaying()) return;
		
		if(sound_engine.isPlaying()){
			sound_engine.stop();
		}
		if (sound_long_engine.isPlaying()) sound_long_engine.stop();
		sound_engine_idle.play();
	}

	
	public static void stopAllSound(){
		sound_paint.stop();
		sound_change_gear.stop();
		sound_engine.stop();
		sound_engine_idle.stop();
		sound_start_engine.stop();
		sound_long_engine.stop();
	}
}
