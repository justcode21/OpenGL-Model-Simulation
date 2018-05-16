package Models;

public class TextureModel {
	
	private int modelID;
	
	private int shineDamper;
	
	private int reflectivity;

	public TextureModel(int modelID) {
		
		this.modelID = modelID;
	}

	public int getModelID() {
		return modelID;
	}

	public int getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(int shineDamper) {
		this.shineDamper = shineDamper;
	}

	public int getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(int reflectivity) {
		this.reflectivity = reflectivity;
	}
	
	

}
