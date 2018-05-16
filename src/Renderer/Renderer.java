package Renderer;

import java.util.HashMap;
import java.util.List;

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
	
	private StaticShader shader;
	
	public Renderer(StaticShader shader) {
		
		this.shader = shader;
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		ToolBox.createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(ToolBox.createProjectionMatrix());
		shader.stop();
		
	}
	
	public void prepare() {
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
	}
	
	private void prepareTexturedModel(TexturedModel texturedModel) {
		
		RawModel model = texturedModel.getRawModel();
		GL30.glBindVertexArray(model.getModelID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		shader.loadShineVariables(texturedModel.getTextureModel().getShineDamper(),
				  texturedModel.getTextureModel().getReflectivity());;

		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTextureModel().getModelID());
	}
	
	private void prepareEntity(Entity entity) {

		Matrix4f transformationMatrix = ToolBox.createTransformationMatrix(entity.getPosition(), 
				entity.getRotation().getX(), entity.getRotation().getY(),
				entity.getRotation().getZ(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		
	}
	
	private void unbindTexturedModel() {
		
		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	public void render(HashMap<TexturedModel, List<Entity>> entities) {
	
		for(TexturedModel texturedModel : entities.keySet()) {
			
			prepareTexturedModel(texturedModel);
			List<Entity>batch = entities.get(texturedModel);
			for(Entity entity : batch) {
				prepareEntity(entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, texturedModel.getRawModel().getVertexCount(),
						            GL11.GL_UNSIGNED_INT, 0);
			}
			unbindTexturedModel();
		}
	}	
}
