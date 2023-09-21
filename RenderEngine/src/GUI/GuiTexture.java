package GUI;

import org.lwjgl.util.vector.Vector2f;

public class GuiTexture 
{
    public boolean visible ;
    public int id ;
    private int texture;
    private Vector2f position;
    private Vector2f scale;

    public GuiTexture(int id , boolean b  , int texture, Vector2f position, Vector2f scale) {
        this.texture = texture;
        this.position = position;
        this.scale = scale;
        this.id = id ;
        this.visible = b ;
    }

    public int getTexture() {
        return texture;
    }
    public Vector2f getPosition() {
        return position;
    }
    public Vector2f getScale() {
        return scale;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public void setScale(Vector2f scale) {
        this.scale = scale;
    }
    
}
