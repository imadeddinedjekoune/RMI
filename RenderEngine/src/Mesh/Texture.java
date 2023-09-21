/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mesh;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import org.lwjgl.opengl.GL12;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.TextureLoader;


/**
 *
 * @author Alif computer
 */
public class Texture 
{
    private int id ;
    private float shine = 1 ;
    private float reflection = 0 ;
    
    public Texture (int id)
    {
        this.id = id;
    }
    
    
    public int getId() {
        return id;
    }

    public float getShine() {
        return shine;
    }

    public void setShine(float shine) {
        this.shine = shine;
    }

    public float getReflection() {
        return reflection;
    }

    public void setReflection(float reflection) {
        this.reflection = reflection;
    }

    
    
    public void bind ()
    {
        glBindTexture(GL_TEXTURE_2D, id);
    }
    
    public void unbind ()
    {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
    
    public void Activate (int n)
    {
        glActiveTexture(GL_TEXTURE0+n);
    }
    
    
    public static BufferedImage loadImage(String name)
    {
        try {
            BufferedImage image = ImageIO.read(new File("./res/textures/"+name));
            return image;
        } catch (IOException e) {
            System.out.println("Could not load texture: " + "./res/textures/"+name);
        }
        return null;
    }
    
    
    public static int loadTexture (BufferedImage image){
        
        if (image == null) {
            return 0;
        }

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 8 ); //4 for RGBA, 3 for RGB

        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                buffer.put((byte) (pixel & 0xFF));               // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
            }
        }

        buffer.flip(); 

        int textureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureID);

        //setup wrap mode
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        //setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        //Send texel data to OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer); //GL_RGBA8 was GL_RGB8A

        return textureID;
    }
    
    public static int loadTexture_(String fileName){
        org.newdawn.slick.opengl.Texture texture = null;
        try{
                texture = TextureLoader.getTexture("PNG",
                                new FileInputStream("res/textures/" + fileName + ".png"));
                GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, 0f);
        } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Tried to load texture " + fileName + ".png , didn't work");
                System.exit(-1);
        }
        return texture.getTextureID();
    }
	
    
    public static int loadTexture (String name){
        BufferedImage image = loadImage(name);
        if (image == null) {
            return 0;
        }

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 8 ); //4 for RGBA, 3 for RGB

        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                buffer.put((byte) (pixel & 0xFF));               // Blue component
                buffer.put((byte) ((pixel & 0xff000000) >>> 24));    // Alpha component. Only for RGBA
            }
        }

        buffer.flip(); 

        int textureID = GL11.glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureID);

        //Send texel data to OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA8, GL_UNSIGNED_BYTE, buffer);
        

        return textureID;
    }
}
