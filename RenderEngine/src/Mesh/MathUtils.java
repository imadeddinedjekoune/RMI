/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mesh;

/**
 *
 * @author Alif computer
 */

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;


public class MathUtils 
{
    private static final float FOV = 70; 
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
    
    
    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale){
        Matrix4f matrix = new Matrix4f();

        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float)Math.toRadians(rx), new Vector3f(1,0,0), matrix, matrix);
        Matrix4f.rotate((float)Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
        Matrix4f.rotate((float)Math.toRadians(rz), new Vector3f(0,0,1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
        return matrix;
    }
    
    public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) 
    {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
        return matrix;
    }
    
    
    public static Matrix4f toMatrix (float [][] arr)
    {
        Matrix4f mat = new Matrix4f();
        mat.m00 = arr[0][0];
        mat.m01 = arr[0][1];
        mat.m02 = arr[0][2];
        mat.m03 = arr[0][3];
        
        mat.m10 = arr[1][0];
        mat.m11 = arr[1][1];
        mat.m12 = arr[1][2];
        mat.m13 = arr[1][3];
        
        mat.m20 = arr[2][0];
        mat.m21 = arr[2][1];
        mat.m22 = arr[2][2];
        mat.m23 = arr[2][3];
        
        mat.m30 = arr[3][0];
        mat.m31 = arr[3][1];
        mat.m32 = arr[3][2];
        mat.m33 = arr[3][3];
        
        return mat ;
    }

    public static Matrix4f createViewMatrix(Camera camera)
    {
        Matrix4f viewMatrix = new Matrix4f();

        viewMatrix.setIdentity();
        Matrix4f.rotate((float)Math.toRadians(camera.getPitch()), new Vector3f(1,0,0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float)Math.toRadians(camera.getYaw()), new Vector3f(0,1,0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float)Math.toRadians(camera.getRoll()), new Vector3f(0,0,1), viewMatrix, viewMatrix); 
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
        return viewMatrix;
    }
    
    public static Matrix4f createProjectionMatrix()
    {
        Matrix4f projectionMatrix;
        float aspectRatio = (float) Display.getWidth()/(float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV /2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
        
        return projectionMatrix;
    }
    
}

