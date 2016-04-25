package transforms;

import java.util.Locale;

/**
 * trida pro praci s body ve 3D (homogeni souradnice)
 * @author PGRF FIM UHK 
 * @version 2014
 */

public class Point3D {
	public double x, y, z, w;

	/**
	 * Vytvari 3D bod v homogenich souradnicich lezici v pocatku souradnic
	 */
	public Point3D() {
		x = y = z = 0.0f;
		w = 1.0f;
	}

	/**
	 * Vytvari 3D bod v homogenich souradnicich
	 * 
	 * @param x
	 *            souradnice x
	 * @param y
	 *            souradnice y
	 * @param z
	 *            souradnice z
	 */
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 1.0f;
	}

	/**
	 * Vytvari 3D bod v homogenich souradnicich
	 * 
	 * @param x
	 *            souradnice x
	 * @param y
	 *            souradnice y
	 * @param z
	 *            souradnice z
	 * @param w
	 *            souradnice w
	 */
	public Point3D(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	/**
	 * Vytvari 3D bod v homogenich souradnicich
	 * 
	 * @param v
	 *            souradnice (x,y,z)
	 */
	public Point3D(Vec3D v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.w = 1.0f;
	}

	/**
	 * Vytvari 3D bod v homogenich souradnicich
	 * 
	 * @param p
	 *            souradnice (x,y,z,w)
	 */
	public Point3D(Point3D p) {
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
		this.w = p.w;
	}

	/**
	 * Vytvari 3D bod v homogenich souradnicich
	 * 
	 * @param p
	 *            souradnice (x,y,w)
	 */
	public Point3D(Point2D p) {
		x = p.x;
		y = p.y;
		z = 0;
		w = p.w;
	}

	/**
	 * Vytvari 3D bod v homogenich souradnicich
	 * 
	 * @param double[]
	 *            souradnice (x,y,z,w)
	 */
	public Point3D(double[] array) {
		x = array[0];
		y = array[1];
		z = array[2];
		w = array[3];
	}

	/**
	 * Nasobeni matici zprava
	 * 
	 * @param mat
	 *            matice 4x4
	 * @return nova instance Point3D
	 */
	public Point3D mul(Mat4 mat) {
		Point3D res = new Point3D();
		res.x = mat.mat[0][0] * x + mat.mat[1][0] * y + mat.mat[2][0] * z
				+ mat.mat[3][0] * w;
		res.y = mat.mat[0][1] * x + mat.mat[1][1] * y + mat.mat[2][1] * z
				+ mat.mat[3][1] * w;
		res.z = mat.mat[0][2] * x + mat.mat[1][2] * y + mat.mat[2][2] * z
				+ mat.mat[3][2] * w;
		res.w = mat.mat[0][3] * x + mat.mat[1][3] * y + mat.mat[2][3] * z
				+ mat.mat[3][3] * w;
		return res;
	}
	
	/*
	 * Transformace 3D bodu kvaternionem
	 * 
	 * @param q
	 *            kvaternion
	 * @return nova instance Point3D
	 */
	public Point3D mul(Quat q) {
		Point3D oPoint = new Point3D(this.dehomog().mul(q));
		return oPoint;
	}

	/**
	 * Pricteni vektoru
	 * 
	 * @param rhs
	 *            vektor (x,y,z,w)
	 * @return nova instance Point3D
	 */
	public Point3D add(Point3D p) {
		return new Point3D(x + p.x, y + p.y, z + p.z, w + p.w);
	}

	/**
	 * Nasobeni skalarem
	 * 
	 * @param f
	 *            skalar
	 * @return nova instance Point3D
	 */
	public Point3D mul(double f) {
		return new Point3D(x * f, y * f, z * f, w * f);
	}

	/**
	 * Dehmogenizace vektoru
	 * 
	 * @return nova instance Vec3D
	 */
	public Vec3D dehomog() {
		if (w == 0.0f)
			return new Vec3D(0,0,0);
		return new Vec3D(x / w, y / w, z / w);
	}

	/**
	 * Prevod na vektor (x,y,z), zanedbani w
	 * 
	 * @return nova instance Vec3D
	 */
	public Vec3D ignoreW() {
		return new Vec3D(x, y, z);
	}
	
	/**
	 * vypis bodu do stringu
	 * 	 
	 * @return textovy retezec
	 */
	@Override
	public String toString() {
		return String.format(Locale.US, "(%4.1f,%4.1f,%4.1f,%4.1f)",x,y,z,w);
	}
	
	/**
	 * formatovany vypis bodu do stringu
	 * @param format
	 *            String jedne slozky
	 * @return textovy retezec
	 */
	public String toString(String format) {
		return String.format(Locale.US, "("+format+","+format+","+format+","+format+")",x,y,z,w);
	}
}