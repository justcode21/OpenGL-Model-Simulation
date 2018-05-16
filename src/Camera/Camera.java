package Camera;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	
	private Vector3f position = new Vector3f(0, 0, 0);
	private int yaw;
	private int pitch;
	private int roll;
	
	public void move() {
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
			position.z -= 0.02;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
			position.z += 0.02;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
			position.x += 0.02;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
			position.x -= 0.02;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
			position.y += 0.02;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
			position.y -= 0.02;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public int getYaw() {
		return yaw;
	}
	public void setYaw(int yaw) {
		this.yaw = yaw;
	}
	public int getPitch() {
		return pitch;
	}
	public void setPitch(int pitch) {
		this.pitch = pitch;
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	
	

}
