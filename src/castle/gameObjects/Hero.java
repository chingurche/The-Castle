package castle.gameObjects;


import castle.GameObject;
import castle.Renderer;
import castle.Hierarchy;
import castle.Vector2;

public class Hero extends GameObject {
    private int health = 3;

    public int getHealth() { return health; }

    public Hero(Vector2 position){
        this.position = position;
        this.renderer = new Renderer("Hr", "\u001B[32m");
    }

    public void move(Vector2 direction)
    {
        Vector2 newPosition = position.add(direction);

        if (!newPosition.isValid()) { return; }

        var hierarchy = Hierarchy.getInstance();

        GameObject other = hierarchy.getObjectOn(newPosition);

        if (other != null && Interactable.class.isAssignableFrom(other.getClass())) {
            Interactable otherInteractable = (Interactable) other;
            otherInteractable.interact(this);
            hierarchy.removeObject(other);
        }

        position = newPosition;
    }

    public void takeDamage() {
        health--;
    }
}
