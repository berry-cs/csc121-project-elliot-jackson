
/* Represents a rectangular hit box
 * The "position" of the box represents the coordinates of
 * the top left corner
 */

public class HitBox {
	private float locX, locY, sizeX, sizeY;
	
	public HitBox(float locX, float locY ,float sizeX, float sizeY) {
		this.locX = locX;
		this.locY = locY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
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
		return (x >= 0) && (y >= 0) && (x <= sizeX) && (y <= sizeY);
	}

	public boolean touching(HitBox hb) {
		return hb.isInside(locX, locY) 
			|| hb.isInside(locX+sizeX, locY)
			|| hb.isInside(locX, locY+sizeY)
			|| hb.isInside(locX+sizeX, locY+sizeY)
			|| this.isInside(hb.x(), hb.y())
			|| this.isInside(hb.x()+hb.width(), hb.y())
			|| this.isInside(hb.x(), hb.y()+hb.height())
			|| this.isInside(hb.x()+hb.width(), hb.y()+hb.height());
			
	}
	
	/** Returns true if this is contained by the provided hitbox */
	public boolean containedBy(HitBox hb) {
		return hb.isInside(locX, locY) && hb.isInside(locX + sizeX, locY + sizeY);
	}
}
