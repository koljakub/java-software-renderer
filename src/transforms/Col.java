package transforms;

import java.util.Locale;

/**
 * trida pro praci s barvou v modelu RGBA
 * @author PGRF FIM UHK 
 * @version 2014
 */

public class Col {

	public double r, g, b, a;

	public Col() {
		r = g = b = 0.0f;
		a = 1.0f;
	}

	public Col(Col c) {
		r = c.r;
		g = c.g;
		b = c.b;
		a = c.a;
	}

	public Col(Point3D c) {
		r = c.x;
		g = c.y;
		b = c.z;
		a = c.w;
	}

	public Col(int rgb) {
		a = 1.0f;
		r = ((rgb >> 16) & 0xffL) / 255.0f;
		g = ((rgb >> 8) & 0xffL) / 255.0f;
		b = (rgb & 0xffL) / 255.0f;
	}

	public Col(int argb, boolean isAlpha) {
		if (isAlpha)
			a = ((argb >> 24) & 0xffL) / 255.0f;
		else
			a = 1.0f;
		r = ((argb >> 16) & 0xffL) / 255.0f;
		g = ((argb >> 8) & 0xffL) / 255.0f;
		b = (argb & 0xffL) / 255.0f;
	}

	public Col(int r, int g, int b) {
		a = 1.0f;
		this.r = r / 255.0f;
		this.g = g / 255.0f;
		this.b = b / 255.0f;
	}

	public Col(int r, int g, int b, int a) {
		this.a = a / 255.0f;
		this.r = r / 255.0f;
		this.g = g / 255.0f;
		this.b = b / 255.0f;
	}

	public Col(double r, double g, double b) {
		a = 1.0f;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public Col(double r, double g, double b, double a) {
		this.a = a;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public Col addna(Col c) {
		return new Col(r + c.r, g + c.g, b + c.b);
	}

	public Col mulna(double x) {
		return new Col(r * x, g * x, b * x);
	}

	public Col add(Col c) {
		return new Col(r + c.r, g + c.g, b + c.b, a + c.a);
	}

	public Col mul(double x) {
		return new Col(r * x, g * x, b * x, a * x);
	}

	public Col mul(Col c) {
		return new Col(r * c.r, g * c.g, b * c.b, a * c.a);
	}

	public Col gamma(double gamma) {
		return new Col(Math.pow(r, gamma), Math.pow(g, gamma), Math.pow(b,
				gamma), a);
	}

	public Col saturate() {
		return new Col(Math.max(0, Math.min(r, 1)),
				Math.max(0, Math.min(g, 1)), Math.max(0, Math.min(b, 1)), a);
	}

	public int getRGB() {
		return (((int) (r * 255.0f)) << 16) | (((int) (g * 255.0f)) << 8)
				| (int) (b * 255.0f);
	}

	public int getARGB() {
		return (((int) (a * 255.0f)) << 24) | (((int) (r * 255.0f)) << 16)
				| (((int) (g * 255.0f)) << 8) | (int) (b * 255.0f);
	}

	/**
	 * vypis RGBA barvy do stringu
	 * 	 
	 * @return textovy retezec
	 */
	@Override
	public String toString() {
		return String.format(Locale.US, "(%4.1f,%4.1f,%4.1f,%4.1f)",r,g,b,a);
	}
	
	/**
	 * formatovany vypis RGBA barvy do stringu
	 * @param format
	 *            String jedne slozky
	 * @return textovy retezec
	 */
	public String toString(String format) {
		return String.format(Locale.US, "("+format+","+format+","+format+","+format+")",r,g,b,a);
	}
}
