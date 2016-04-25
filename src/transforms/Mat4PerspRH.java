package transforms;

/**
 * trida pro praci s maticemi 4x4:
 * matice transformace zobrazovaciho objemu pro perspektivni promitani 
 * @author PGRF FIM UHK 
 * @version 2014
 */public class Mat4PerspRH extends Mat4Identity {

	/**
	 * Vyvari transformacni matici 4x4 prp perspektivni deformaci zobrazovaciho
	 * objemu
	 * 
	 * @param alpha
	 *            zorny uhel
	 * @param k
	 *            pomer sirka/vyska okna
	 * @param zn
	 *            blizke z
	 * @param zf
	 *            vzdalene z
	 */
	public Mat4PerspRH(double alpha, double k, double zn, double zf) {
		double h = (1.0 / Math.tan(alpha / 2.0));
		double w = k * h;
		mat[0][0] = w;
		mat[1][1] = h;
		mat[2][2] = zf / (zn - zf);
		mat[3][2] = zn * zf / (zn - zf);
		mat[2][3] = -1.0;
		mat[3][3] = 0.0;
	}
}
