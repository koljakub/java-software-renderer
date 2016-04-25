package transforms;

import java.util.Locale;

/**
 * trida pro praci s 1D vektory 
 * @author PGRF FIM UHK 
 * @version 2014
 */

public class Vec1D {
	public double x;

	/**
	 * Vytvoreni instance 1D vektoru (0)
	 */
	public Vec1D() {
		x = 0.0f;
	}

	/**
	 * Vytvoreni instance 1D vektoru (x)
	 * 
	 * @param x
	 *            souradnice x
	 */
	public Vec1D(double x) {
		this.x = x;
	}

	/**
	 * Vytvoreni instance 1D vektoru (x)
	 * 
	 * @param v
	 *            souradnice (x)
	 */
	public Vec1D(Vec1D v) {
		x = v.x;
	}

	/**
	 * Pricteni vektoru
	 * 
	 * @param v
	 *            vektor (x)
	 * @return nova instance Vec1D
	 */
	public Vec1D add(Vec1D v) {
		return new Vec1D(x + v.x);
	}

	/**
	 * Nasobeni skalarem
	 * 
	 * @param d
	 *            skalar
	 * @return nova instance Vec1D
	 */
	public Vec1D mul(double d) {
		return new Vec1D(x * d);
	}

	/**
	 * Otoceni vektoru
	 * 
	 * @return nova instance Vec1D
	 */
	public Vec1D reversed() {
		return new Vec1D(-x);
	}

	/**
	 * vypis bodu do stringu
	 * 	 
	 * @return textovy retezec
	 */
	@Override
	public String toString() {
		return String.format(Locale.US, "(%4.1f)",x);
	}
	
	/**
	 * formatovamy vypis bodu do stringu
	 * @param format
	 *            String jedne slozky
	 * @return textovy retezec
	 */
	public String toString(String format) {
		return String.format(Locale.US, "("+format+")",x);
	}
}
