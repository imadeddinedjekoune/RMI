/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SkyBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Alif computer
 */
public class SkyBoxShader {
    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;
    private int nbAttribs = 0 ;

    public SkyBoxShader(String vertexFile, String fragmentFile) 
    {
        vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
    }

    public void start() 
    {
        GL20.glUseProgram(programID);
    }

    public void stop() 
    {
        GL20.glUseProgram(0);
    }
    
    protected void bindAttribute(int attribute, String variableName) 
    {
        GL20.glBindAttribLocation(programID, attribute, variableName);
        nbAttribs ++ ;
    }

    public int getNbAttribs() {
        return nbAttribs;
    }
    

    protected void bindAttributes()
    {
        bindAttribute(0,"position");
    }

    private static int loadShader(String file, int type) {

        StringBuilder shaderSource = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) 
            {
                shaderSource.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Could not read file!");
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) 
        { 
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader");
            System.exit(-1);
        }
        return shaderID;
    }
    
    private int getUniformLocation(String uniformName)
    {
        return GL20.glGetUniformLocation(programID, uniformName);
    }
    
    public void loadFloat(String name, float value) 
    {
        GL20.glUniform1f(getUniformLocation(name),  value);
    }
    
    public void loadInt(String name, int value) 
    {
        GL20.glUniform1i(getUniformLocation(name),  value);
    }
    
    public void loadVector(String name, Vector3f vector)
    {
        GL20.glUniform3f(getUniformLocation(name), vector.x, vector.y, vector.z);
    }
	
    public void loadBoolean(String name, boolean value)
    {
        float toLoad = 0;

        if (value) 
        {
            toLoad = 1;
        }
        GL20.glUniform1f(getUniformLocation(name), toLoad);
    }
	
    public void loadMatrix(String name, Matrix4f matrix)
    {
        FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(getUniformLocation(name), false, matrixBuffer);
    }
}
