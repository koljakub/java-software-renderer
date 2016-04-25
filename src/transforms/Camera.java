package transforms;

/**
 * Trida pro nastaveni pohledove transformace
 * 
 * @author PGRF FIM UHK 
 * @version 2012
 */
public class Camera {
	double azimuth, radius, zenith;

	boolean firstPerson; // true -> 1st person, 0 -> 3rd person

	Vec3D eye, eyeVector, pos;

	Mat4 view;

	void computeMatrix() {
		eyeVector = new Vec3D((double) (Math.cos(azimuth) * Math.cos(zenith)),
				(double) (Math.sin(azimuth) * Math.cos(zenith)), (double) Math
						.sin(zenith));
		if (firstPerson) {
			eye = new Vec3D(pos);
			view = new Mat4ViewRH(pos, eyeVector.mul(radius), new Vec3D(
					(double) (Math.cos(azimuth) * Math
							.cos(zenith + Math.PI / 2)), (double) (Math
							.sin(azimuth) * Math.cos(zenith + Math.PI / 2)),
					(double) Math.sin(zenith + Math.PI / 2)));
		} else {
			eye = pos.add(eyeVector.mul(-1 * radius));
			view = new Mat4ViewRH(eye, eyeVector.mul(radius), new Vec3D(
					(double) (Math.cos(azimuth) * Math
							.cos(zenith + Math.PI / 2)), (double) (Math
							.sin(azimuth) * Math.cos(zenith + Math.PI / 2)),
					(double) Math.sin(zenith + Math.PI / 2)));
		}
	}

	public Camera() {
		azimuth = zenith = 0.0f;
		radius = 1.0f;
		pos = new Vec3D(0.0f, 0.0f, 0.0f);
		firstPerson = true;
		computeMatrix();
	}

	public void addAzimuth(double ang) {
		azimuth += ang;
		computeMatrix();
	}

	public void addRadius(double dist) {
		if (radius + dist < 0.1f)
			return;
		radius += dist;
		computeMatrix();
	}

	public void mulRadius(double scale) {
		if (radius * scale < 0.1f)
			return;
		radius *= scale;
		computeMatrix();
	}

	public void addZenith(double ang) {
		if (Math.abs(zenith + ang) <= Math.PI / 2) {
			zenith += ang;
			computeMatrix();
		}
	}

	public void setAzimuth(double ang) {
		azimuth = ang;
		computeMatrix();
	}

	public void setRadius(double dist) {
		radius = dist;
		computeMatrix();
	}

	public void setZenith(double ang) {
		zenith = ang;
		computeMatrix();
	}

	public void backward(double speed) {
		forward((-1) * speed);
	}

	public void forward(double speed) {
		pos = pos.add(new Vec3D(
				(double) (Math.cos(azimuth) * Math.cos(zenith)), (double) (Math
						.sin(azimuth) * Math.cos(zenith)), (double) Math
						.sin(zenith)).mul(speed));
		computeMatrix();
	}

	public void left(double speed) {
		right((-1) * speed);
	}

	public void right(double speed) {
		pos = pos.add(new Vec3D((double) Math.cos(azimuth - Math.PI / 2),
				(double) Math.sin(azimuth - Math.PI / 2), 0.0f).mul(speed));
		computeMatrix();

	}

	public void down(double speed) {
		pos.z -= speed;
		computeMatrix();
	}

	public void up(double speed) {
		pos.z += speed;
		computeMatrix();
	}

	public void move(Vec3D dir) {
		pos = pos.add(dir);
		computeMatrix();
	}

	public void setPosition(Vec3D apos) {
		pos = new Vec3D(apos);
		computeMatrix();
	}

	public boolean getFirstPerson() {
		return firstPerson;
	}

	public void setFirstPerson(boolean fp) {
		firstPerson = fp;
		computeMatrix();
	}

	public Vec3D getEye() {
		return eye;
	}

	public Vec3D getEyeVector() {
		return eyeVector;
	}

	public Vec3D getPosition() {
		return pos;
	}

	public Mat4 getViewMatrix() {
		return view;
	}
	public double getAzimuth() {
		return azimuth;
	}
	public double getZenith() {
		return zenith;
	}
}
