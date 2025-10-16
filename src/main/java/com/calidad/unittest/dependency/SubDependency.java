package com.calidad.unittest.dependency;

public class SubDependency {

	public String getClassName() {
		return this.getClass().getSimpleName();
	}
	public int addTwo(int i) {
		return i + 2;
	}

	public Integer addTwo(Integer i) {
		return i + 2;
	}
}
