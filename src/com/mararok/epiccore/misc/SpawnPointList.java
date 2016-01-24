/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2016 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.misc;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.mararok.epiccore.math.UMath;
import com.mararok.epiccore.math.Vector3i;

/**
 * Represents list of sets spawn positions
 */
public class SpawnPointList {
  private Vector3i[] points;
  private Random randomGenerator;

  public SpawnPointList(Random randomGenerator) {
    this.randomGenerator = randomGenerator;
  }

  public SpawnPointList(Random randomGenerator, Vector3i[] points) {
    this(randomGenerator);
    this.points = points;
  }

  public void teleport(Player player, int pointId) {
    Vector3i spawnPoint = points[pointId];
    player.teleport(new Location(player.getWorld(), spawnPoint.x, spawnPoint.y, spawnPoint.z));
  }

  /**
   * Teleports player to random position selected from available positions.
   */
  public void randomTeleport(Player player) {
    teleport(player, UMath.getRandomIntegerFromRange(randomGenerator, 0, points.length));
  }

  public void addPoint(Vector3i point) {
    if (points == null) {
      points = new Vector3i[1];
      points[0] = point;
    } else {
      points = Arrays.copyOf(points, points.length + 1);
      points[points.length - 1] = point;
    }
  }

  public void removePoint(Vector3i point) {
    int pointId = getPointId(point);
    if (pointId != -1) {
      removePoint(pointId);
    }
  }

  public boolean hasPoint(Vector3i point) {
    return getPointId(point) != -1;
  }

  /**
   * @return index of found point or -1
   */
  public int getPointId(Vector3i point) {
    if (!isEmpty()) {
      for (int i = 0; i < points.length; i++) {
        if (points[i].x == point.x && points[i].y == point.y && points[i].z == point.z) {
          return i;
        }

      }
    }
    return -1;
  }

  public void removePoint(int pointId) {
    if (isEmpty()) {
      return;
    }

    if (pointId >= 0 && pointId < points.length) {
      if (points.length == 1) {
        points = null;
      } else {
        Vector3i[] tmpPoints = new Vector3i[points.length - 1];
        int nextIndex = 0;
        for (int i = 0; i < points.length; i++) {
          if (i != pointId) {
            tmpPoints[nextIndex++] = points[i];
          }
        }

        points = tmpPoints;
      }
    }

  }

  public boolean isEmpty() {
    return points == null;
  }

}
