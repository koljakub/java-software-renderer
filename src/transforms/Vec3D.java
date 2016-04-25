package transforms;

import java.util.Locale;

/**
 * trida pro praci s 3D vektory
 * 
 * @author PGRF FIM UHK 
 * @version 2012
 */

public class Vec3D {
	public double x, y, z;

	/**
	 * Vytvoreni instance 3D vektoru (0,0,0)
	 */
	public Vec3D() {
		x = y = z = 0.0f;
	}

	/**
	 * Vytvoreni instance 3D vektoru (x,y,z)
	 * 
	 * @param x
	 *            souradnice x
	 * @param y
	 *            souradnice y
	 * @param z
	 *            souradnice z
	 */
	public Vec3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Vytvoreni instance 3D vektoru (x,y,z)
	 * 
	 * @param avec
	 *            souradnice (x,y,z)
	 */
	public Vec3D(Vec3D vec) {
		x = vec.x;
		y = vec.y;
		z = vec.z;
	}

	/**
	 * Vytvoreni instance 3D vektoru (x,y,z)
	 * 
	 * @param point
	 *            souradnice (x,y,z,w)
	 */
	public Vec3D(Point3D point) {
		x = point.x;
		y = point.y;
		z = point.z;
	}

	/**
	 * Vytvari 3D bod v homogenich souradnicich
	 * 
	 * @param double[] souradnice (x,y,z)
	 */
	public Vec3D(double[] array) {
		x = array[0];
		y = array[1];
		z = array[2];
	}

	/**
	 * Pricteni vektoru
	 * 
	 * @param v
	 *            vektor (x,y,z)
	 * @return nova instance Vec3D
	 */
	public Vec3D add(Vec3D v) {
		return new Vec3D(x + v.x, y + v.y, z + v.z);
	}

	/**
	 * Nasobeni skalarem
	 * 
	 * @param d
	 *            skalar
	 * @return nova instance Vec3D
	 */
	public Vec3D mul(double d) {
		return new Vec3D(x * d, y * d, z * d);
	}

	/**
	 * Nasobeni matici zprava
	 * 
	 * @param m
	 *            matice 3x3
	 * @return nova instance Vec3D
	 */
	public Vec3D mul(Mat3 m) {
		Vec3D res = new Vec3D();
		res.x = m.mat[0][0] * x + m.mat[1][0] * y + m.mat[2][0] * z;
		res.y = m.mat[0][1] * x + m.mat[1][1] * y + m.mat[2][1] * z;
		res.z = m.mat[0][2] * x + m.mat[1][2] * y + m.mat[2][2] * z;
		return res;
	}

	/**
	 * Transformace vektoru quaternionem
	 * 
	 * @param q
	 *            kvaternion
	 * @return nova instance Vec3D
	 */
	public Vec3D mul(Quat q) {
		Quat p = new Quat(0, x, y, z);
		p = q.mulR(p).mulR(q.inv());
		Vec3D oVec = new Vec3D(p.i, p.j, p.k);
		return oVec;
	}

	/**
	 * Nasobeni vektorem po slozkach
	 * 
	 * @param v
	 *            vektor (x,y,z)
	 * @return nova instance Vec3D
	 */
	public Vec3D mul(Vec3D v) {
		return new Vec3D(x * v.x, y * v.y, z * v.z);
	}

	/**
	 * Skalarni soucin vektoru
	 * 
	 * @param rhs
	 *            vektor (x,y,z)
	 * @return nova instance Vec3D
	 */
	public double dot(Vec3D rhs) {
		return x * rhs.x + y * rhs.y + z * rhs.z;
	}

	/**
	 * Vektorovy soucin vektoru
	 * 
	 * @param v
	 *            vektor (x,y,z)
	 * @return nova instance Vec3D
	 */
	public Vec3D cross(Vec3D v) {
		return new Vec3D(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y
				* v.x);
	}

	/**
	 * Normalizace vektoru
	 * 
	 * @return nova instance Vec3D
	 */
	public Vec3D normalized() {
		double len = length();
		if (len == 0.0f)
			return new Vec3D(0, 0, 0);
		return new Vec3D(x / len, y / len, z / len);
	}

	/**
	 * Otoceni vektoru
	 * 
	 * @return nova instance Vec3D
	 */
	public Vec3D reversed() {
		return new Vec3D(-x, -y, -z);
	}

	/**
	 * Velikost vektoru
	 * 
	 * @return velikost
	 */
	public double length() {
		return (double) Math.sqrt((double) (x * x + y * y + z * z));
	}

	/**
	 * vypis vektoru do stringu
	 * 
	 * @return textovy retezec
	 */
	public String toString() {
		return String.format(Locale.US, "(%4.1f,%4.1f,%4.1f)", x, y, z);
	}

	/**
	 * formatovany vypis vektoru do stringu
	 * 
	 * @param format
	 *            String jedne slozky
	 * @return textovy retezec
	 */
	public String toString(String format) {
		return String.format(Locale.US, "(" + format + "," + format + "," + format + ")",
				x, y, z);
	}
}