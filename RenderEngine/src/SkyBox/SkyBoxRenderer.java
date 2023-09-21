/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SkyBox;

import Mesh.Camera;
import Mesh.MathUtils;
import Mesh.Mesh;
import Mesh.MeshLoader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

/**
 *
 * @author Alif computer
 */
public class SkyBoxRenderer {
    private static final float SIZE = 500f;

    private static final float[] VERTICES = {        
        -SIZE,  SIZE, -SIZE,
        -SIZE, -SIZE, -SIZE,
        SIZE, -SIZE, -SIZE,
         SIZE, -SIZE, -SIZE,
         SIZE,  SIZE, -SIZE,
        -SIZE,  SIZE, -SIZE,

        -SIZE, -SIZE,  SIZE,
        -SIZE, -SIZE, -SIZE,
        -SIZE,  SIZE, -SIZE,
        -SIZE,  SIZE, -SIZE,
        -SIZE,  SIZE,  SIZE,
        -SIZE, -SIZE,  SIZE,

         SIZE, -SIZE, -SIZE,
         SIZE, -SIZE,  SIZE,
         SIZE,  SIZE,  SIZE,
         SIZE,  SIZE,  SIZE,
         SIZE,  SIZE, -SIZE,
         SIZE, -SIZE, -SIZE,

        -SIZE, -SIZE,  SIZE,
        -SIZE,  SIZE,  SIZE,
         SIZE,  SIZE,  SIZE,
         SIZE,  SIZE,  SIZE,
         SIZE, -SIZE,  SIZE,
        -SIZE, -SIZE,  SIZE,

        -SIZE,  SIZE, -SIZE,
         SIZE,  SIZE, -SIZE,
         SIZE,  SIZE,  SIZE,
         SIZE,  SIZE,  SIZE,
        -SIZE,  SIZE,  SIZE,
        -SIZE,  SIZE, -SIZE,

        -SIZE, -SIZE, -SIZE,
        -SIZE, -SIZE,  SIZE,
         SIZE, -SIZE, -SIZE,
         SIZE, -SIZE, -SIZE,
        -SIZE, -SIZE,  SIZE,
         SIZE, -SIZE,  SIZE
    };

    private static String[] Text = {"Skybox/posx", "Skybox/negx", "Skybox/posy", "Skybox/negy", "Skybox/posz", "Skybox/negz"};
    private static String[] TEXTURE_FILES = {"Skybox/right", "Skybox/left", "Skybox/top", "Skybox/bottom", "Skybox/back", "Skybox/front"};
    private Mesh cube;
    private int textureID;
    private SkyBoxShader shader;

    public int getTextureID() {
        return textureID;
    }
    
    

    public SkyBoxRenderer()
    {
        cube = MeshLoader.loadStatic(VERTICES);
        textureID = MeshLoader.loadCubeMap(TEXTURE_FILES);
        shader = new SkyBoxShader("./res/shaders/skyboxVertexShader.txt", "./res/shaders/skyboxFragmentShader.txt");
        shader.start();
        shader.loadMatrix("projectionMatrix", MathUtils.createProjectionMatrix());
        shader.stop();
    }

    public void render(Camera camera ){
        shader.start();
        shader.loadMatrix("viewMatrix", MathUtils.createViewMatrix(camera));
        GL30.glBindVertexArray(cube.getVao());
        GL20.glEnableVertexAttribArray(0);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, textureID);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, cube.getVertsCount());
        GL30.glBindVertexArray(0);
        shader.stop();
    }
}
