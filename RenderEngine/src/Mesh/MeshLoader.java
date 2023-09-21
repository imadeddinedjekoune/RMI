/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mesh;


import SkyBox.TextureData;
import Utils.BufferUtilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
/**
 *
 * @author Alif computer
 */
public class MeshLoader 
{
    public static Mesh loadStatic(float pos[],float textureCoord[],float normals[])
    {
        int vaoID = createVAO();
        storeDataInAttributeList(0, 3, pos);
        storeDataInAttributeList(1, 2, textureCoord);
        storeDataInAttributeList(2, 3, normals);
        unbindVAO();
        return new Mesh(vaoID, pos.length/3);
    }
    
    public static Mesh loadStatic(float pos[])
    {
        int vaoID = createVAO();
        storeDataInAttributeList(0, 3, pos);
        unbindVAO();
        return new Mesh(vaoID, pos.length/3);
    }
    
    public static Mesh loadStaticGUI(float pos[])
    {
        int vaoID = createVAO();
        storeDataInAttributeList(0, 2, pos);
        unbindVAO();
        return new Mesh(vaoID, pos.length/2);
    }
    
    
    
    

    
    public static Mesh loadObj (String fileName)
    {
        FileReader fr = null;
        try {
            fr = new FileReader(new File("res/"+fileName+".obj"));
        } catch (FileNotFoundException e) 
        {
            System.err.println("Couldn't load file!");
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fr);
        String line;
        List<Vector3f> vertices = new ArrayList<Vector3f>();
        List<Vector2f> textures = new ArrayList<Vector2f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();

        ArrayList<Vector3f> orderedVertices = new ArrayList<>();
        ArrayList<Vector2f> orderedTextures = new ArrayList<>();
        ArrayList<Vector3f> orderedNormals = new ArrayList<>();
        
        
        try {
            while (true)
            {
                line = reader.readLine();
                String[] currentLine = line.split(" ");
                if (line.startsWith("v ")){
                    Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]),
                                    Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    vertices.add(vertex);
                }else if (line.startsWith("vt ")){
                    Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]), 
                            Float.parseFloat(currentLine[2]));
                    textures.add(texture);
                }else if (line.startsWith("vn ")){
                    Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]),
                                    Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    normals.add(normal);
                }else if (line.startsWith("f ")){
                    break;
                }
                
            }
            
            while(line != null)
            {
                if (!line .startsWith("f "))
                {
                    line = reader.readLine();
                    continue;
                }
                String [] currentLine = line.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[2].split("/");
                String[] vertex3 = currentLine[3].split("/");
                
                orderedVertices.add(vertices.get(Integer.parseInt(vertex1[0])-1));
                orderedVertices.add(vertices.get(Integer.parseInt(vertex2[0])-1));
                orderedVertices.add(vertices.get(Integer.parseInt(vertex3[0])-1));
                
                orderedTextures.add(textures.get(Integer.parseInt(vertex1[1])-1));
                orderedTextures.add(textures.get(Integer.parseInt(vertex2[1])-1));
                orderedTextures.add(textures.get(Integer.parseInt(vertex3[1])-1));
                
                orderedNormals.add(normals.get(Integer.parseInt(vertex1[2])-1));
                orderedNormals.add(normals.get(Integer.parseInt(vertex2[2])-1));
                orderedNormals.add(normals.get(Integer.parseInt(vertex3[2])-1));
                
                line = reader.readLine();
            }
            
            reader.close();
        } catch(Exception e) {
                e.printStackTrace();
        }

        float verts[] = new float[orderedVertices.size()*3];
        int cpt = 0 ;
        for (Vector3f v : orderedVertices)
        {
            verts[cpt + 0] = v.x;
            verts[cpt + 1] = v.y;
            verts[cpt + 2] = v.z;
            cpt = cpt + 3 ;
        }
        
        float text[] = new float[orderedTextures.size()*2];
        cpt = 0 ;
        for (Vector2f v : orderedTextures)
        {
            text[cpt + 0] = v.x;
            text[cpt + 1] = v.y;
            cpt = cpt + 2 ;
        }
        
        float norm[] = new float[orderedNormals.size()*3];
        cpt = 0 ;
        for (Vector3f v : orderedNormals)
        {
            norm[cpt + 0] = v.x;
            norm[cpt + 1] = v.y;
            norm[cpt + 2] = v.z;
            cpt = cpt + 3 ;
        }
        
        return loadStatic(verts, text,norm);
    }
    

    private static int createVAO() 
    {
        int vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }
    
    private static void unbindVAO()
    {
        GL30.glBindVertexArray(0);
    }
    
    private static void storeDataInAttributeList(int location , int size , float data[])
    {
        int vboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = BufferUtilities.storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(location, size, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
    
    public static int loadCubeMap(String[] textureFiles)
    {
        int texID = GL11.glGenTextures();
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texID);

        for (int i = 0; i<textureFiles.length;i++){
            TextureData data = decodeTextureFile("res/" + textureFiles[i] + ".png");
            GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X+i, 0, GL11.GL_RGBA, data.getWidth(), data.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data.getBuffer());
        }
        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);

        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        return texID;
    }
    
    public static TextureData decodeTextureFile(String fileName) 
    {
        int width = 0;
        int height = 0;
        ByteBuffer buffer = null;
        try {
            FileInputStream in = new FileInputStream(fileName);
            PNGDecoder decoder = new PNGDecoder(in);
            width = decoder.getWidth();
            height = decoder.getHeight();
            buffer = ByteBuffer.allocateDirect(4 * width * height);
            decoder.decode(buffer, width * 4, Format.RGBA);
            buffer.flip();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Tried to load texture " + fileName + ", didn't work");
            System.exit(-1);
        }
        return new TextureData(buffer, width, height);
    }

   
    
}
