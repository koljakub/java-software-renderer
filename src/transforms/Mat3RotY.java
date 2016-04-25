package transforms;

/**
 * trida pro praci s maticemi 3x3: 
 * matice rotace kolem osy y 
 * @author PGRF FIM UHK 
 * @version 2012
 */
public class Mat3RotY extends Mat3Identity {

	/**
	 * Vytvari transformacni matici 3x3 pro rotaci kolem osy y ve 3D
	 * 
	 * @param alpha
	 *            uhel rotace v radianech
	 */
	public Mat3RotY(double alpha) {
		mat[0][0] = (double) Math.cos(alpha);
		mat[2][2] = (double) Math.cos(alpha);
		mat[2][0] = (double) Math.sin(alpha);
		mat[0][2] = (double) -Math.sin(alpha);
	}
}