/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mesh;

import Shader.ShaderProgram;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Alif computer
 */
public class AirPlain 
{
    private Mesh mesh ;
    private Texture t ;
    private float scale ;

    public AirPlain(String PathFileObj, String PathTexture, float scale) {
        this.mesh = MeshLoader.loadObj("models/"+PathFileObj);
        this.t = new Texture(Texture.loadTexture_("models/"+PathTexture));
        this.scale = scale;
        
        t.setShine(1000);
        t.setReflection(1f);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public Texture getT() {
        return t;
    }

    public void setT(Texture t) {
        this.t = t;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
    
    public void affectshader(ShaderProgram shader)
    {
        shader.start();
        shader.loadFloat("shineDamper", t.getShine());
        shader.loadFloat("reflectivity", t.getReflection());
        Matrix4f transformation = MathUtils.createTransformationMatrix(new Vector3f(0, 2f, 0), 0, 180, 0, getScale());
        shader.loadMatrix("transformationMatrix", transformation);
        shader.stop();
    }
    
}
