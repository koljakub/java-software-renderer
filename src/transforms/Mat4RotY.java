package transforms;

/**
 * trida pro praci s maticemi 4x4:
 * matice rotace kolem osy y  
 * @author PGRF FIM UHK 
 * @version 2014
 */
public class Mat4RotY extends Mat4Identity {

	/**
	 * Vytvari transformacni matici 4x4 pro rotaci kolem osy y ve 3D
	 * 
	 * @param alpha
	 *            uhel rotace v radianech
	 */
	public Mat4RotY(double alpha) {
		mat[0][0] = (double) Math.cos(alpha);
		mat[2][2] = (double) Math.cos(alpha);
		mat[2][0] = (double) Math.sin(alpha);
		mat[0][2] = (double) -Math.sin(alpha);
	}
}