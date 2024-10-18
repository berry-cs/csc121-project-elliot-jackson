
/* Represents a rectangular hit box
 * The "position" of the box represents the coordinates of
 * the top left corner
 */

import processing.core.PApplet;

public class HitBox {
	private float locX, locY, sizeX, sizeY;
	
	public HitBox(float locX, float locY ,float sizeX, float sizeY) {
		this.locX = locX;
		this.locY = locY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public HitBox(float locX, float locY ,float size) {
		this.locX = locX;
		this.locY = locY;
		this.sizeX = size;
		this.sizeY = size;
	}
	
	/** Creates a hitbox at the specified center */
	public static HitBox newByCenter(float centerX, float centerY, float sizeX, float sizeY) {
		float locX = centerX - (sizeX/2);
		float locY = centerY - (sizeY/2);
		return new HitBox(locX, locY, sizeX, sizeY);
	}
	
	/** Getters */
	public float x() {
		return locX;
	}
	
	public float y() {
		return locY;
	}
	
	public float width() {
		return sizeX;
	}
	
	public float height() {
		return sizeY;
	}
	
	public float centerX() {
		return locX + (sizeX/2);
	}
	
	public float centerY() {
		return locY + (sizeY/2);
	}
	
	/** Mutation */
	public void addY(float y) {
		this.locY = this.locY + y;
	}
	
	/** Returns true if point is inside hitbox */
	public boolean isInside(float x, float y) {
		x = x - locX;
		y = y - locY;
		return (x > 0) && (y > 0) && (x < sizeX) && (y < sizeY);
	}
	public boolean touching(HitBox hb) {
		return hb.isInside(locX, locY) 
			|| hb.isInside(locX+sizeX, locY)
			|| hb.isInside(locX, locY+sizeY)
			|| hb.isInside(locX+sizeX, locY+sizeY);
			
	}
	
	/** Returns true if this is contained by the provided hitbox */
	public boolean containedBy(HitBox hb) {
		return hb.isInside(locX, locY) && hb.isInside(locX + sizeX, locY + sizeY);
	}
	
	public void draw(PApplet p) {
		p.noFill();
		p.rect(locX, locY, sizeX, sizeY);
	}
	
}
