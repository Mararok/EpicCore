/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.math;

public class Vector3i implements Cloneable {
  public int x, y, z;

  public Vector3i() {
  }

  public Vector3i(int x, int y, int z) {
    this.set(x, y, z);
  }

  public void set(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double distance(Vector3i other) {
    return UMath.distance(x, y, z, other.x, other.y, other.z);
  }

  public boolean isWithinSphere(Vector3i sphereCenter, int radius) {
    return UMath.isPointWithinSphere(x, y, z, sphereCenter.x, sphereCenter.y, sphereCenter.z, radius);
  }

  @Override
  public Vector3i clone() {
    try {
      return (Vector3i) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new InternalError();
    }
  }

  @Override
  public String toString() {
    return "[" + x + "," + y + "," + z + "]";
  }
}
