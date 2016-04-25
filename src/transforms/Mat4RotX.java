package transforms;

/**
 * trida pro praci s maticemi 4x4:
 * matice rotace kolem osy x  
 * @author PGRF FIM UHK 
 * @version 2014
 */
public class Mat4RotX extends Mat4Identity {

	/**
	 * Vytvari transformacni matici 4x4 pro rotaci kolem osy X ve 3D
	 * 
	 * @param alpha
	 *            uhel rotace v radianech
	 */
	public Mat4RotX(double alpha) {
		mat[1][1] = (double) Math.cos(alpha);
		mat[2][2] = (double) Math.cos(alpha);
		mat[2][1] = (double) -Math.sin(alpha);
		mat[1][2] = (double) Math.sin(alpha);
	}
}