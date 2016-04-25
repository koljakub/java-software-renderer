package transforms;

/**
 * trida pro praci s maticemi 3x3: 
 * matice translace ve 2D 
 * @author PGRF FIM UHK 
 * @version 2012
 */
public class Mat3Transl2D extends Mat3Identity {

	/**
	 * Vytvari transformacni matici 3x3 pro translaci ve 2D
	 * 
	 * @param x
	 *            posunuti na ose x
	 * @param y
	 *            posunuti na ose y
	 */
	public Mat3Transl2D(double x, double y) {
		mat[2][0] = x;
		mat[2][1] = y;
	}

}