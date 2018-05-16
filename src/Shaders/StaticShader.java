package Shaders;

import org.lwjgl.util.vector.Matrix4f;

import Camera.Camera;
import Entity.Light;
import ToolBox.ToolBox;

public class StaticShader extends ShaderProgram{

	private static final String VERTEX_SHADER = "src/Shaders/vertexShader.txt";
	private static final String FRAGMENT_SHADER = "src/Shaders/fragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColour;
	
	public StaticShader() {
		
		super(VERTEX_SHADER, FRAGMENT_SHADER);
	}


	@Override
	protected void bindAttributes() {
		
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normals");
		
	}


	@Override
	protected void getAllUniformLocations() {
		
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColour = super.getUniformLocation("lightColour");
	}

	public void loadLightSource(Light light) {
		
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColour, light.getColour());
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera) {
		
		Matrix4f matrix = ToolBox.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, matrix);
	}
	


	
}
