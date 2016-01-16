/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Extends Entity type with tracking properties changes
 * Lazy create changed properties list for memory saves
 */
public class ObservedEntity extends Entity {
  private List<PropertyEntry> changedProperties;

  public ObservedEntity(int id) {
    super(id);
  }

  /**
   * Execute when some property changes value.
   */
  protected void onChangeProperty(String name, Object newValue) {
    if (!hasAnyChangedProperties()) {
      changedProperties = new ArrayList<PropertyEntry>();
    }

    PropertyEntry propertyEntry = getChangedProperty(name);
    if (propertyEntry != null) {
      propertyEntry.value = newValue;
    } else {
      changedProperties.add(new PropertyEntry(name, newValue));
    }

  }

  /**
   * Returns new property value if property was changed
   */
  public PropertyEntry getChangedProperty(String name) {
    if (hasAnyChangedProperties()) {
      for (PropertyEntry entry : changedProperties) {
        if (entry.name.equalsIgnoreCase(name)) {
          return entry;
        }
      }
    }
    return null;
  }

  /**
   * Returns all changed properties list
   */
  public PropertyEntry[] getChangedProperties() {
    PropertyEntry[] list = null;
    if (hasAnyChangedProperties()) {
      list = new PropertyEntry[changedProperties.size()];
      int index = 0;
      for (PropertyEntry property : changedProperties) {
        list[index++] = new PropertyEntry(property.name, property.value);
      }
    }
    return list;
  }

  public void clearChanges() {
    changedProperties = null;
  }

  public boolean hasAnyChangedProperties() {
    return changedProperties != null;
  }

  public class PropertyEntry {
    public String name;
    public Object value;

    public PropertyEntry(String name, Object value) {
      this.name = name;
      this.value = value;
    }

  }
}
