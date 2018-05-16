package Loader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import Models.RawModel;

public class VAOLoader {
	
	private static List<Integer>vaos = new ArrayList<Integer>();
	private static List<Integer>vbos = new ArrayList<Integer>();
	
	
	public static RawModel loadToVAO(float [] vertices, float [] textures, float [] normals, int [] indices) {
		
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInVertexArrayAttributes(0, 3, vertices);
		storeDataInVertexArrayAttributes(1, 2, textures);
		storeDataInVertexArrayAttributes(2, 3, normals);
		
		unbindVAO();
		return new RawModel(vaoID, indices.length);
		
	}
	
	private static int createVAO() {
		
		int vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		vaos.add(vaoID);
		return vaoID;
		
	}
	
	private static void bindIndicesBuffer(int[] indices) {
		
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = getIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

	}
	
	private static void storeDataInVertexArrayAttributes(int attributeNumber, int coordinateSize, float[] data) {
		
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = getFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT,
									false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	
	}
	
	private static FloatBuffer getFloatBuffer(float[] data) {
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
		
	}
	
	private static IntBuffer getIntBuffer(int[] data) {
		
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
		
	}
	
	private static void unbindVAO() {
		
		GL30.glBindVertexArray(0);
	}
	
	public static void cleanUp() {
		
		for(int vao : vaos)
			GL30.glDeleteVertexArrays(vao);
		
		for(int vbo : vbos)
			GL15.glDeleteBuffers(vbo);
		
	}

}
