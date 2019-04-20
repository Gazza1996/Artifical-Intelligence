package ie.gmit.sw.ai;

//import javax.imageio.*;
//import java.awt.image.*;

public class ControlledSprite extends Sprite {

	// some private variables for sprite
	private int health = 100;
	// https://stackoverflow.com/questions/106591/what-is-the-volatile-keyword-useful-for
	private static volatile ControlledSprite single_instance = null;

	public static synchronized ControlledSprite getInstance() throws Exception {

		if (single_instance == null) {
			single_instance = new ControlledSprite();
		}

		return single_instance;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public ControlledSprite(String name, int frames, String... images) throws Exception {
		super(name, frames, images);
	}

	public ControlledSprite() {
		// TODO Auto-generated constructor stub
		super();
	}

	public void setDirection(Direction d) {
		switch (d.getOrientation()) {
		case 0:
		case 1:
			super.setImageIndex(0); // UP or DOWN
			break;
		case 2:
			super.setImageIndex(1); // LEFT
			break;
		case 3:
			super.setImageIndex(2); // LEFT
		default:
			break; // Ignore...
		}
	}
}