package transforms;

/**
 * trida pro praci s maticemi 4x4:
 * matice rotace kolem osy z  
 * @author PGRF FIM UHK 
 * @version 2014
 */
public class Mat4RotZ extends Mat4Identity {

	/**
	 * Vytvari transformacni matici 4x4 pro rotaci kolem osy Z ve 3D
	 * 
	 * @param alpha
	 *            uhel rotace v radianech
	 */
	public Mat4RotZ(double alpha) {
		mat[0][0] = (double) Math.cos(alpha);
		mat[1][1] = (double) Math.cos(alpha);
		mat[1][0] = (double) -Math.sin(alpha);
		mat[0][1] = (double) Math.sin(alpha);
	}
}