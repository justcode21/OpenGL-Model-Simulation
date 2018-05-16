package Shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class ShaderProgram {
	
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	ShaderProgram(String vertexShader, String fragmentShader){
		
		vertexShaderID = loadShader(vertexShader, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentShader, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		bindAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		getAllUniformLocations();
	
	}
	
	public void start() {
		
		GL20.glUseProgram(programID);
	}
	
	public void stop() {
		
		GL20.glUseProgram(0);
		
	}
	
	protected abstract void getAllUniformLocations();
	
	protected int getUniformLocation(String uniformName) {
		
		return GL20.glGetUniformLocation(programID, uniformName);
	}
	
	protected abstract void bindAttributes();
	
	protected void bindAttribute(int attribute, String variableName) {
		
		GL20.glBindAttribLocation(programID, attribute, variableName);
	}
	
	protected void loadFloat(int location, float value) {
		
		GL20.glUniform1f(location, value);
	}
	
	protected void loadVector(int location, Vector3f value) {
		
		GL20.glUniform3f(location, value.x, value.y, value.z);
	}
	
	protected void loadBoolean(int location, boolean value) {
		
		float result = 0;
		if(value)
			result = 1;
		
		GL20.glUniform1f(location, result);
	}
	
	protected void loadMatrix(int location, Matrix4f matrix) {
		
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(location, false, matrixBuffer);

	}
	
	private static int loadShader(String file, int type) {
		
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null)
				shaderSource.append(line).append("\n");
			reader.close();
		}catch(IOException e){
			System.err.println("Could not read file");
			e.printStackTrace();
			System.exit(-1);
		}
		
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			
			String shaderType = type == GL20.GL_VERTEX_SHADER ? "vertexShader" :
										"fragmentShader";
			System.out.println(GL20.glGetProgramInfoLog(shaderID, 500));
			System.err.println("Could not compile shader " + shaderType);
			System.exit(-1);
		}
		
		return shaderID;
	}
	
	public void cleanUp() {
		
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		GL20.glDeleteProgram(programID);
	
	}

}
