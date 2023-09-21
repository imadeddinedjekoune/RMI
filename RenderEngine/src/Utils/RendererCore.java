package Utils;


import Mesh.Mesh;
import Shader.ShaderProgram;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Alif computer
 */
public class RendererCore 
{
    public static void initRenderer()
    {
        GL11.glEnable(GL13.GL_MULTISAMPLE);  
        GL11.glEnable(GL11.GL_CULL_FACE);
	GL11.glCullFace(GL11.GL_BACK);
    }
    public static void prepareRenderer()
    {
        GL11.glClearColor(0.3f, 0.3f, 0.3f, 1);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
	GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		
    }
    
    public static void Render(Mesh m , int nbAttribs)
    {
        GL30.glBindVertexArray(m.getVao());
        
        
        int i ;
        
        for (i = 0 ; i < nbAttribs ; i++ )
        {
            GL20.glEnableVertexAttribArray(i);
        }
        
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, m.getVertsCount());
        
        for (i = 0 ; i < nbAttribs ; i++ )
        {
            GL20.glDisableVertexAttribArray(i);
        }
        
        GL30.glBindVertexArray(0);
    }
}
