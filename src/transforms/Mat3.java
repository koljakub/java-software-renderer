package transforms;

import java.util.Locale;

/**
 * trida pro praci s maticemi 3x3 
 * 
 * @author PGRF FIM UHK 
 * @version 2012
 */
public class Mat3 {
	public double mat[][] = new double[4][4];

	/**
	 * Vytvari nulovou matici 4x4
	 */
	public Mat3() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				mat[i][j] = 0.0f;
	}

	/**
	 * Vytvari matici 3x3 ze tri trislozkovych vektoru - po radcich
	 * 
	 * @param v1
	 *            vektor (M00, M01, M02)
	 * @param v2
	 *            vektor (M10, M11, M12)
	 * @param v3
	 *            vektor (M20, M21, M22)
	 */
	public Mat3(Vec3D v1, Vec3D v2, Vec3D v3) {
		mat[0][0] = v1.x;
		mat[0][1] = v1.y;
		mat[0][2] = v1.z;
		mat[1][0] = v2.x;
		mat[1][1] = v2.y;
		mat[1][2] = v2.z;
		mat[2][0] = v3.x;
		mat[2][1] = v3.y;
		mat[2][2] = v3.z;
	}


	/**
	 * Vytvari matici 3x3 jako klon matice
	 * 
	 * @param m
	 *            Matice 3x3
	 */
	public Mat3(Mat3 m) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				mat[i][j] = m.mat[i][j];
	}

	/**
	 * Vytvari matici 3x3
	 * 
	 * @param m
	 *            array
	 */
	public Mat3(double[] m) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				mat[i][j] = m[i * 3 + j];
	}

	/**
	 * Vytvari matici 3x3
	 * 
	 * @param m
	 *            array
	 */
	public Mat3(double[][] m) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				mat[i][j] = m[i][j];
	}
	
	/**
	 * Vytvari matici 3x3 jako klon casti matice matice 4x4
	 * 
	 * @param m
	 *            Matice 4x4
	 */
	public Mat3(Mat4 m) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				mat[i][j] = m.mat[i][j];
	}
	
	
	/**
	 * Pricteni matice 3x3
	 * 
	 * @param m
	 *            Matice 3x3
	 * @return nova instance Mat3
	 */
	public Mat3 add(Mat3 m) {
		Mat3 hlp = new Mat3();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				hlp.mat[i][j] = mat[i][j] + m.mat[i][j];
		return hlp;
	}

	/**
	 * Nasobeni matice 3x3 skalarem
	 * 
	 * @param d
	 *            skalar
	 * @return nova instance Mat3
	 */
	public Mat3 mul(double d) {
		Mat3 hlp = new Mat3();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				hlp.mat[i][j] = mat[i][j] * d;
		return hlp;
	}

	/**
	 * Prinasobeni matice 3x3 zprava
	 * 
	 * @param m
	 *            matice 3x3
	 * @return nova instance Mat3
	 */
	public Mat3 mul(Mat3 m) {
		Mat3 hlp = new Mat3();
		double sum;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				sum = 0.0f;
				for (int k = 0; k < 3; k++)
					sum += mat[i][k] * m.mat[k][j];
				hlp.mat[i][j] = sum;
			}
		return hlp;
	}

	
	/**
	 * Transponovani matice 3x3
	 * 
	 * @return nova instance Mat3
	 */
	public Mat3 transpose() {
		Mat3 hlp = new Mat3();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				hlp.mat[i][j] = mat[j][i];
		return hlp;
	}

	/**
	 * Determinant matice 3x3
	 * 
	 * @return determinant
	 */
	public double det() {
	       return  mat[0][0]*(mat[1][1]*mat[2][2] - mat[2][1]*mat[1][2]) - 
	               mat[0][1]*(mat[1][0]*mat[2][2] - mat[2][0]*mat[1][2]) +
	               mat[0][2]*(mat[1][0]*mat[2][1] - mat[2][0]*mat[1][1]);
	   }

	/**
	 * Inverzni matice 3x3
	 * 
	 * @return nova instance Mat3
	 */
	public Mat3 inverse() {
		Mat3 hlp = new Mat3();
		double det = 1.0/det();
		       
		hlp.mat[0][0] = det*(mat[1][1]*mat[2][2] - mat[1][2]*mat[2][1]);
		hlp.mat[0][1] = det*(mat[0][2]*mat[2][1] - mat[0][1]*mat[2][2]);
		hlp.mat[0][2] = det*(mat[0][1]*mat[1][2] - mat[0][2]*mat[1][1]);
		       
		hlp.mat[1][0] = det*(mat[1][2]*mat[2][0] - mat[1][0]*mat[2][2]);
		hlp.mat[1][1] = det*(mat[0][0]*mat[2][2] - mat[0][2]*mat[2][0]);
		hlp.mat[1][2] = det*(mat[0][2]*mat[1][0] - mat[0][0]*mat[1][2]);
		       
		hlp.mat[2][0] = det*(mat[1][0]*mat[2][1] - mat[1][1]*mat[2][0]);
		hlp.mat[2][1] = det*(mat[0][1]*mat[2][0] - mat[0][0]*mat[2][1]);
		hlp.mat[2][2] = det*(mat[0][0]*mat[1][1] - mat[0][1]*mat[1][0]);
		               
		return hlp;
	}


	/**
	 * vypis matice do stringu
	 * 	 
	 * @return textovy retezec
	 */
	@Override
	public String toString() {
		return String.format(Locale.US, "{%4.1f,%4.1f,%4.1f},\n"+"{%4.1f,%4.1f,%4.1f},\n"+"{%4.1f,%4.1f,%4.1f}",
				mat[0][0], mat[0][1], mat[0][2],
				mat[1][0], mat[1][1], mat[1][2],
				mat[2][0], mat[2][1], mat[2][2]);
	}
	
	/**
	 * formatovany vypis matice do stringu
	 * @param format
	 *            String jedne slozky
	 * @return textovy retezec
	 */
	public String toString(String format) {
		return String.format(Locale.US, "{{"+format+","+format+","+format+"},"+
				"{"+format+","+format+","+format+"},\n"+
				"{"+format+","+format+","+format+"}\n",
				mat[0][0], mat[0][1], mat[0][2],
				mat[1][0], mat[1][1], mat[1][2],
				mat[2][0], mat[2][1], mat[2][2]);
	}
}
