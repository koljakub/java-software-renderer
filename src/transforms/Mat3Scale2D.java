package transforms;

/**
 * trida pro praci s maticemi 3x3: 
 * matice zmeny meritka ve 2D 
 * @author PGRF FIM UHK 
 * @version 2012
 */
public class Mat3Scale2D extends Mat3Identity {

	/**
	 * Vytvari transformacni matici 3x3 pro zmenu meritka ve 2D
	 * 
	 * @param x
	 *            zvetseni/zmenseni na ose x
	 * @param y
	 *            zvetseni/zmenseni na ose y
	 */
	public Mat3Scale2D(double x, double y) {
		mat[0][0] = x;
		mat[1][1] = y;
	}

}
