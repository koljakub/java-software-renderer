package transforms;

/**
 * trida pro praci s krivkami v 3D 
 * @author PGRF FIM UHK 
 * @version 2012
 */

public class Kubika {
	/**
	 * bazova matice
	 */
	Mat4 bm = new Mat4();

	/**
	 * matice ridicich bodu
	 */
	Mat4 rb;

	/**
	 * Vytvari kubiku
	 * 
	 * @param typ
	 *            typ krivky (1,2,0 - Fergusnova, Coonsova a Bezierova kubika)
	 */
	public Kubika(int typ) {
		switch (typ) {
		case 1:// ferguson
			bm.mat[0][0] = 2;
			bm.mat[0][1] = -2;
			bm.mat[0][2] = 1;
			bm.mat[0][3] = 1;

			bm.mat[1][0] = -3;
			bm.mat[1][1] = 3;
			bm.mat[1][2] = -2;
			bm.mat[1][3] = -1;

			bm.mat[2][0] = 0;
			bm.mat[2][1] = 0;
			bm.mat[2][2] = 1;
			bm.mat[2][3] = 0;

			bm.mat[3][0] = 1;
			bm.mat[3][1] = 0;
			bm.mat[3][2] = 0;
			bm.mat[3][3] = 0;
			break;

		case 2:// coons
			bm.mat[0][0] = -1;
			bm.mat[0][1] = 3;
			bm.mat[0][2] = -3;
			bm.mat[0][3] = 1;

			bm.mat[1][0] = 3;
			bm.mat[1][1] = -6;
			bm.mat[1][2] = 3;
			bm.mat[1][3] = 0;

			bm.mat[2][0] = -3;
			bm.mat[2][1] = 0;
			bm.mat[2][2] = 3;
			bm.mat[2][3] = 0;

			bm.mat[3][0] = 1;
			bm.mat[3][1] = 4;
			bm.mat[3][2] = 1;
			bm.mat[3][3] = 0;

			bm = bm.mul(1 / 6f);
			break;

		case 0:// bezier
		default:
			bm.mat[0][0] = -1;
			bm.mat[0][1] = 3;
			bm.mat[0][2] = -3;
			bm.mat[0][3] = 1;

			bm.mat[1][0] = 3;
			bm.mat[1][1] = -6;
			bm.mat[1][2] = 3;
			bm.mat[1][3] = 0;

			bm.mat[2][0] = -3;
			bm.mat[2][1] = 3;
			bm.mat[2][2] = 0;
			bm.mat[2][3] = 0;

			bm.mat[3][0] = 1;
			bm.mat[3][1] = 0;
			bm.mat[3][2] = 0;
			bm.mat[3][3] = 0;
		}

	}

	/**
	 * inicializace pomoci zadane ctverice ridicich bodu
	 */
	public void init(Point3D b1, Point3D b2, Point3D b3, Point3D b4) {
		rb = new Mat4(b1, b2, b3, b4);
		rb = bm.mul(rb);
	}

	/**
	 * vypocet souradnice bodu bikubiky podle parametru t z intervalu <0,1>
	 * 
	 * @param t
	 * @return
	 */
	public Point3D compute(double t) {
		if (t > 1)
			t = 1;
		if (t < 0)
			t = 0;

		Point3D res = new Point3D(t * t * t, t * t, t, 1);

		res = res.mul(rb);
		res.w = 1;
		return res;
	}

}
