package EngineStarter;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Camera.Camera;
import Entity.Light;
import Loader.OBJLoader;
import Loader.TextureLoader;
import Loader.VAOLoader;
import Models.Entity;
import Models.RawModel;
import Models.TextureModel;
import Models.TexturedModel;
import Renderer.DisplayManager;
import Renderer.MasterRenderer;

public class GameLoop {
	
	public static void main(String [] args) throws Exception{
	
		DisplayManager.startDisplay();
		
		//Create constants
		Camera camera = new Camera();
		Light lightSource = new Light(new Vector3f(0, 0, -25), new Vector3f(1, 1, 1));
		
		//Create the dragon Model
		RawModel rawModel = OBJLoader.loadObjModel("dragon");
		TextureModel textureModel = new TextureModel(TextureLoader.loadTexture("stallTexture"));
		TexturedModel staticModel = new TexturedModel(rawModel, textureModel);
		Entity dragon = new Entity(staticModel, new Vector3f(10, -5, -40), new Vector3f(0, 0, 0), 1);
		staticModel.getTextureModel().setShineDamper(10);
		staticModel.getTextureModel().setReflectivity(1);
		
		//Create the stall model
		rawModel = OBJLoader.loadObjModel("stall");
		textureModel = new TextureModel(TextureLoader.loadTexture("stallTexture"));
		staticModel = new TexturedModel(rawModel, textureModel);
		Entity stall = new Entity(staticModel, new Vector3f(-10, -5, -40), new Vector3f(0, 0, 0), 1);
		staticModel.getTextureModel().setShineDamper(10);
		staticModel.getTextureModel().setReflectivity(1);
		
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested()) {
			dragon.increaseRotation(0, 1, 0);
			stall.increaseRotation(0, 1, 0);
			camera.move();
			renderer.processEntity(dragon);
			renderer.processEntity(stall);
			renderer.render(lightSource, camera);
			DisplayManager.updateDisplay();
		}
		
		renderer.cleanUp();
		VAOLoader.cleanUp();
		TextureLoader.cleanUp();
		DisplayManager.stopDisplay();
	}

}
