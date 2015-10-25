package com.hohman.stickwars.util;

public class RGB {

	protected float red, green, blue;

	public RGB(float red, float green, float blue) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public RGB() {
		super();
	}

	public float getRed() {
		return red;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public float getGreen() {
		return green;
	}

	public void setGreen(float green) {
		this.green = green;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(float blue) {
		this.blue = blue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(blue);
		result = prime * result + Float.floatToIntBits(green);
		result = prime * result + Float.floatToIntBits(red);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RGB other = (RGB) obj;
		if (Float.floatToIntBits(blue) != Float.floatToIntBits(other.blue))
			return false;
		if (Float.floatToIntBits(green) != Float.floatToIntBits(other.green))
			return false;
		if (Float.floatToIntBits(red) != Float.floatToIntBits(other.red))
			return false;
		return true;
	}
	
	
	
}
