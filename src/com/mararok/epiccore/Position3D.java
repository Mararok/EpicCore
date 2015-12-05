/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore;

public class Position3D implements Cloneable {
  public int x, y, z;

  public Position3D() {
  }

  public Position3D(int x, int y, int z) {
    this.set(x, y, z);
  }

  public void set(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double distance(Position3D other) {
    return UMath.distance(x, y, z, other.x, other.y, other.z);
  }

  public boolean isWithinSphere(Position3D sphereCenter, int radius) {
    return UMath.isPointWithinSphere(x, y, z, sphereCenter.x, sphereCenter.y, sphereCenter.z, radius);
  }

  @Override
  public Position3D clone() {
    try {
      return (Position3D) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new InternalError();
    }
  }

  @Override
  public String toString() {
    return "[" + x + "," + y + "," + z + "]";
  }
}
