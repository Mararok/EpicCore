/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.language;

public interface LanguageLoader {
  public Language load(String languagePrefix) throws Exception;
}
