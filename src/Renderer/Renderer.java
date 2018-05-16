package Renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import Models.Entity;
import Models.RawModel;
import Models.TexturedModel;
import Shaders.StaticShader;
import ToolBox.ToolBox;

public class Renderer {
	
	public Renderer(StaticShader shader) {
		
		ToolBox.createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(ToolBox.createProjectionMatrix());
		shader.stop();
		
	}
	
	public void prepare() {
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0f, 0.5f, 0.5f, 0.5f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
	}
	
	public void render(Entity entity, StaticShader shader) {
		
		prepare();
	
		TexturedModel texturedModel = entity.getModel();
		RawModel rawModel = texturedModel.getRawModel();
		
		GL30.glBindVertexArray(rawModel.getModelID());
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		Matrix4f transformationMatrix = ToolBox.createTransformationMatrix(entity.getPosition(), 
				entity.getRotation().getX(), entity.getRotation().getY(),
				entity.getRotation().getZ(), entity.getScale());
		
		shader.loadTransformationMatrix(transformationMatrix);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTextureModel().getModelID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		

		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		
	}
	
	
}
