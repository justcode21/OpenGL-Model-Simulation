package Renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Camera.Camera;
import Entity.Light;
import Models.Entity;
import Models.TexturedModel;
import Shaders.StaticShader;



public class MasterRenderer {
	
	private StaticShader shader = new StaticShader();
	Renderer renderer = new Renderer(shader);
	
	HashMap<TexturedModel, List<Entity> > entities = new HashMap<TexturedModel, List<Entity>>(); 
	
	public void render(Light sun, Camera camera) {
		
		renderer.prepare();
		shader.start();
		shader.loadLightSource(sun);
		shader.loadViewMatrix(camera);
		renderer.render(entities);
		shader.stop();
		entities.clear();
	}
	
	public void processEntity(Entity entity) {
		
		TexturedModel texturedModel = entity.getModel();
		List<Entity>batch = entities.get(texturedModel);
		if(batch != null)
			batch.add(entity);
		else {
			List<Entity>newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(texturedModel, newBatch);
		}
	}
	
	public void cleanUp() {
		
		shader.cleanUp();
	}
}

