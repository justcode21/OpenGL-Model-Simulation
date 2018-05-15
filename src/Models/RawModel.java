package Models;

public class RawModel {

	private int modelID;
	private int vertexCount;
	
	public RawModel(int modelID, int vertexCount){
		
		this.modelID = modelID;
		this.vertexCount = vertexCount;
		
	}

	public int getModelID() {
		
		return modelID;
	}
	
	public int getVertexCount() {
		
		return vertexCount;
		
	}
	
	
}
