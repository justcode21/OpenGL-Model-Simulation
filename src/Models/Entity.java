package Models;

import org.lwjgl.util.vector.Vector3f;

public class Entity {

	private TexturedModel model;
	private Vector3f position, rotation;
	private float scale;
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale) {
		
		super();
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public void increasePosition(float dx, float dy, float dz) {
		
		position.x += dx;
		position.y += dy;
		position.z += dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		
		rotation.x += dx;
		rotation.y += dy;
		rotation.z += dz;
	}
	
	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	
	
	
	
}
