package transforms;

/**
 * trida pro praci s plochami v 3D 
 * 
 * @author PGRF FIM UHK 
 * @version 2012
 */

public class Bikubika {
	Point3D u1, u2, u3, u4;
	Kubika k1, k2, k3, k4, k5;

	/**
	 * Vytvari bikubiku
	 * 
	 * @param typ
	 *            typ krivky
	 */
	public Bikubika(int typ) {
		// 1,2,0
		k1 = new Kubika(typ);
		k2 = new Kubika(typ);
		k3 = new Kubika(typ);
		k4 = new Kubika(typ);
		k5 = new Kubika(typ);
	}

	/**
	 * inicializace pomoci 4x4 ridicich bodu
	 */
	public void init(Point3D b11, Point3D b12, Point3D b13, Point3D b14,
			Point3D b21, Point3D b22, Point3D b23, Point3D b24, Point3D b31,
			Point3D b32, Point3D b33, Point3D b34, Point3D b41, Point3D b42,
			Point3D b43, Point3D b44) {
		k1.init(b11, b12, b13, b14);
		k2.init(b21, b22, b23, b24);
		k3.init(b31, b32, b33, b34);
		k4.init(b41, b42, b43, b44);
	}

	/**
	 * vypocet souradnice bodu bikubiky podle parametru u,v z intervalu <0,1>
	 * 
	 * @param u
	 * @param v
	 * @return
	 */
	public Point3D compute(double u, double v) {
		if (u > 1)
			u = 1;
		if (u < 0)
			u = 0;
		if (v > 1)
			v = 1;
		if (v < 0)
			v = 0;

		u1 = k1.compute(u);
		u2 = k2.compute(u);
		u3 = k3.compute(u);
		u4 = k4.compute(u);
		k5.init(u1, u2, u3, u4);
		Point3D t = k5.compute(v);
		return t;
	}

}
