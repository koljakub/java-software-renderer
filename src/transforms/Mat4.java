package transforms;

import java.util.Locale;

/**
 * trida pro praci s maticemi 4x4
 * 
 * @author PGRF FIM UHK 
 * @version 2014
 */

public class Mat4 {
	public double mat[][] = new double[4][4];

	/**
	 * Vytvari nulovou matici 4x4
	 */
	public Mat4() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				mat[i][j] = 0.0f;
	}

	/**
	 * Vytvari matici 4x4 ze ctyr ctyrslozkovych vektoru - po radcich
	 * 
	 * @param p1
	 *            vektor (M00, M01, M02, M03)
	 * @param p2
	 *            vektor (M10, M11, M12, M13)
	 * @param p3
	 *            vektor (M20, M21, M22, M23)
	 * @param p4
	 *            vektor (M30, M31, M32, M33)
	 */
	public Mat4(Point3D p1, Point3D p2, Point3D p3, Point3D p4) {
		mat[0][0] = p1.x;
		mat[0][1] = p1.y;
		mat[0][2] = p1.z;
		mat[0][3] = p1.w;
		mat[1][0] = p2.x;
		mat[1][1] = p2.y;
		mat[1][2] = p2.z;
		mat[1][3] = p2.w;
		mat[2][0] = p3.x;
		mat[2][1] = p3.y;
		mat[2][2] = p3.z;
		mat[2][3] = p3.w;
		mat[3][0] = p4.x;
		mat[3][1] = p4.y;
		mat[3][2] = p4.z;
		mat[3][3] = p4.w;
	}

	/**
	 * Vytvari matici 4x4
	 * 
	 * @param m
	 *            Matice 4x4
	 */
	public Mat4(Mat4 m) {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				mat[i][j] = m.mat[i][j];
	}

	/**
	 * Vytvari matici 4x4
	 * 
	 * @param m
	 *            array
	 */
	public Mat4(double[] m) {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				mat[i][j] = m[i * 4 + j];
	}

	/**
	 * Vytvari matici 4x4
	 * 
	 * @param m
	 *            array
	 */
	public Mat4(double[][] m) {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				mat[i][j] = m[i][j];
	}

	/**
	 * Scitani matic 4x4
	 * 
	 * @param m
	 *            Matice 4x4
	 * @return nova instance Mat4
	 */
	public Mat4 add(Mat4 m) {
		Mat4 hlp = new Mat4();
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				hlp.mat[i][j] = mat[i][j] + m.mat[i][j];
		return hlp;
	}

	/**
	 * Nasobeni matice 4x4 skalarem
	 * 
	 * @param d
	 *            skalar
	 * @return nova instance Mat4
	 */
	public Mat4 mul(double d) {
		Mat4 hlp = new Mat4();
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				hlp.mat[i][j] = mat[i][j] * d;
		return hlp;
	}

	/**
	 * Nasobeni matici 4x4 zprava
	 * 
	 * @param m
	 *            matice 4x4
	 * @return nova instance Mat4
	 */
	public Mat4 mul(Mat4 m) {
		Mat4 hlp = new Mat4();
		double sum;
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				sum = 0.0f;
				for (int k = 0; k < 4; k++)
					sum += mat[i][k] * m.mat[k][j];
				hlp.mat[i][j] = sum;
			}
		return hlp;
	}

	
	/**
	 * vypis matice do stringu
	 * 	 
	 * @return textovy retezec
	 */
	@Override
	public String toString() {
		return String.format(Locale.US, "{%4.1f,%4.1f,%4.1f,%4.1f},\n"+"{%4.1f,%4.1f,%4.1f,%4.1f},\n"+"{%4.1f,%4.1f,%4.1f,%4.1f},\n"+"{%4.1f,%4.1f,%4.1f,%4.1f}",
				mat[0][0], mat[0][1], mat[0][2], mat[0][3],
				mat[1][0], mat[1][1], mat[1][2], mat[1][3],
				mat[2][0], mat[2][1], mat[2][2], mat[2][3],
				mat[3][0], mat[3][1], mat[3][2], mat[3][3]);
	}
	
	/**
	 * formatovany vypis matice do stringu
	 * @param format
	 *            String jedne slozky
	 * @return textovy retezec
	 */
	public String toString(String format) {
		return String.format(Locale.US, "{{"+format+","+format+","+format+","+format+"},"+
				"{"+format+","+format+","+format+","+format+"},\n"+
				"{"+format+","+format+","+format+","+format+"},\n"+
				"{"+format+","+format+","+format+","+format+"}\n",
				mat[0][0], mat[0][1], mat[0][2], mat[0][3],
				mat[1][0], mat[1][1], mat[1][2], mat[1][3],
				mat[2][0], mat[2][1], mat[2][2], mat[2][3],
				mat[3][0], mat[3][1], mat[3][2], mat[3][3]);
	}
	
	/**
	 * vypis matice do stringu
	 * 
	 * @return jednoradkovy textovy retezec
	 */
	public String string() {
		return String.format("[" + new Point3D(mat[0]).toString() + ";"
				+ new Point3D(mat[1]).toString() + ";"
				+ new Point3D(mat[2]).toString() + ";"
				+ new Point3D(mat[3]).toString() + "]");
	}

	/**
	 * formatovamy vypis matice do stringu
	 * 
	 * @param format
	 *            String jedne slozky
	 * @return jednoradkovy textovy retezec
	 */
	public String string(String format) {
		return String.format("[" + new Point3D(mat[0]).toString(format) + ";"
				+ new Point3D(mat[1]).toString(format) + ";"
				+ new Point3D(mat[2]).toString(format) + ";"
				+ new Point3D(mat[3]).toString(format) + "]");
	}

	/**
	 * vypis matice do stringu
	 * 
	 * @return viceradkovy textovy retezec
	 */
	public String string2() {
		return String.format("\n|"
				+ String.format("%4.1f;%4.1f;%4.1f;%4.1f", mat[0][0],
						mat[0][1], mat[0][2], mat[0][3])
				+ "|\n|"
				+ String.format("%4.1f;%4.1f;%4.1f;%4.1f", mat[1][0],
						mat[1][1], mat[1][2], mat[1][3])
				+ "|\n|"
				+ String.format("%4.1f;%4.1f;%4.1f;%4.1f", mat[2][0],
						mat[2][1], mat[2][2], mat[2][3])
				+ "|\n|"
				+ String.format("%4.1f;%4.1f;%4.1f;%4.1f", mat[3][0],
						mat[3][1], mat[3][2], mat[3][3]) + "|\n\n");
	}

	/**
	 * formatovamy vypis matice do stringu
	 * 
	 * @param format
	 *            String jedne slozky
	 * @return viceradkovy textovy retezec
	 */
	public String string2(String format) {
		return String.format("\n|"
				+ String.format(format + ";" + format + ";" + format + ";"
						+ format, mat[0][0], mat[0][1], mat[0][2], mat[0][3])
				+ "|\n|"
				+ String.format(format + ";" + format + ";" + format + ";"
						+ format, mat[1][0], mat[1][1], mat[1][2], mat[1][3])
				+ "|\n|"
				+ String.format(format + ";" + format + ";" + format + ";"
						+ format, mat[2][0], mat[2][1], mat[2][2], mat[2][3])
				+ "|\n|"
				+ String.format(format + ";" + format + ";" + format + ";"
						+ format, mat[3][0], mat[3][1], mat[3][2], mat[3][3])
				+ "|\n\n");

	}
}
