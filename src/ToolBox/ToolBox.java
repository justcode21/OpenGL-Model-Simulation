package ToolBox;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import Camera.Camera;

public class ToolBox {
	
	private static final float FOV = 70f;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000f; 
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx,
			       float ry, float rz, float scale) {
		
		
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rx), new Vector3f(1, 0, 0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(ry), new Vector3f(0, 1, 0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rz), new Vector3f(0, 0, 1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
		return matrix;
		
	}
	
	public static Matrix4f createViewMatrix(Camera camera) {
		
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.rotate((float)Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(camera.getRoll()), new Vector3f(0, 0, 1), matrix, matrix);
		Vector3f cameraPosition = camera.getPosition(); 
		Vector3f negetiveCamera = new Vector3f(-cameraPosition.getX(), -cameraPosition.getY(), -cameraPosition.getZ()); 
		Matrix4f.translate(negetiveCamera, matrix, matrix);
		return matrix;
		
	}
	
	public static Matrix4f createProjectionMatrix() {
		
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		
		float aspectRatio = (float)Display.getWidth() / (float)Display.getHeight();
		float y_scale = (float)((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		
		matrix = new Matrix4f();
		matrix.m00 = x_scale;
		matrix.m11 = y_scale;
		matrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		matrix.m23 = -1;
		matrix.m32 = -((2 * FAR_PLANE * NEAR_PLANE) / frustum_length);
		matrix.m33 = 0;
		return matrix;
		
	}

}
