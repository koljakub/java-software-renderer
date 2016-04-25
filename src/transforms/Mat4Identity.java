package transforms;

/**
 * trida pro praci s maticemi 4x4:
 * jednotkova matice
 * @author PGRF FIM UHK 
 * @version 2014
 */
public class Mat4Identity extends Mat4 {

	/**
	 * Vytvari jednotkovou matici 4x4
	 */
	public Mat4Identity() {
		for (int i = 0; i < 4; i++)
			mat[i][i] = 1.0f;
	}
}
