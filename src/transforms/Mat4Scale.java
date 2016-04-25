package transforms;

/**
 * trida pro praci s maticemi 4x4:
 * matice transformace zmeny meritka  
 * @author PGRF FIM UHK 
 * @version 2014
 */
public class Mat4Scale extends Mat4Identity {

	/**
	 * Vytvari transformacni matici 4x4 pro zmenu meritka ve 3D
	 * 
	 * @param x
	 *            zvetseni/zmenseni na ose x
	 * @param y
	 *            zvetseni/zmenseni na ose y
	 * @param z
	 *            zvetseni/zmenseni na ose z
	 */
	public Mat4Scale(double x, double y, double z) {
		mat[0][0] = x;
		mat[1][1] = y;
		mat[2][2] = z;
	}

}