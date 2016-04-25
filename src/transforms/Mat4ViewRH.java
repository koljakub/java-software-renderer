package transforms;

/**
 * trida pro praci s maticemi 4x4:
 * matice pohledove transformace  
 * @author PGRF FIM UHK 
 * @version 2014
 */
public class Mat4ViewRH extends Mat4Identity {

	/**
	 * Vytvari transformacni matici 4x4 pro pohledovou transformaci ve 3D
	 * 
	 * @param e
	 *            vektor pozice pozorovatele
	 * @param v
	 *            vektor pohledu
	 * @param u
	 *            up vektor
	 */
	public Mat4ViewRH(Vec3D e, Vec3D v, Vec3D u) {
		Vec3D x, y, z;
		z = v.mul(-1.0f).normalized();
		x = u.cross(z).normalized();
		y = z.cross(x);
		mat[0][0] = x.x;
		mat[1][0] = x.y;
		mat[2][0] = x.z;
		mat[3][0] = -e.dot(x);
		mat[0][1] = y.x;
		mat[1][1] = y.y;
		mat[2][1] = y.z;
		mat[3][1] = -e.dot(y);
		mat[0][2] = z.x;
		mat[1][2] = z.y;
		mat[2][2] = z.z;
		mat[3][2] = -e.dot(z);

	}
}
