/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mesh;

/**
 *
 * @author Alif computer
 */
public class Mesh 
{
    private int vao ;
    private int vertsCount ;

    public Mesh(int vao, int vertsCount) {
        this.vao = vao;
        this.vertsCount = vertsCount;
    }

    public int getVao() {
        return vao;
    }

    public void setVao(int vao) {
        this.vao = vao;
    }

    public int getVertsCount() {
        return vertsCount;
    }

    public void setVertsCount(int vertsCount) {
        this.vertsCount = vertsCount;
    }
    
    
    
    
}
