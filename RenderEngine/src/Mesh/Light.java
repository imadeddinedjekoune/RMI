/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mesh;

import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Alif computer
 */
public class Light 
{
    private Vector3f position;
    private Vector3f colour;
    public Light(Vector3f position, Vector3f colour) 
    {
        this.position = position;
        this.colour = colour;
    }
    public Vector3f getPosition() 
    {
        return position;
    }
    public void setPosition(Vector3f position) 
    {
        this.position = position;
    }
    public Vector3f getColour() 
    {
        return colour;
    }
    public void setColour(Vector3f colour) 
    {
        this.colour = colour;
    }
}
