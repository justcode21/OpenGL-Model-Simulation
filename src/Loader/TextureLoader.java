package Loader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class TextureLoader {
	
	private static List<Integer>textures = new ArrayList<Integer>();
	
	public static int loadTexture(String fileName) {
		
		Texture texture = null;
		
		try {
			texture = org.newdawn.slick.opengl.
					
					  TextureLoader.getTexture("PNG", new FileInputStream("res/" + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int textureID = texture.getTextureID(); 
		textures.add(textureID);
		return textureID;
		
	}
	
	public static void cleanUp() {
		
		for(int texture : textures)
			GL11.glDeleteTextures(texture);
		
	}

}
