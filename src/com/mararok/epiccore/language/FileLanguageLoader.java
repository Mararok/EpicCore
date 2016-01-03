/**
 * EpicWar
 * The MIT License
 * Copyright (C) 2015 Mararok <mararok@gmail.com>
 */
package com.mararok.epiccore.language;

import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.configuration.file.YamlConfiguration;

public class FileLanguageLoader implements LanguageLoader {
  private Path basePath;

  public FileLanguageLoader(Path languagesBasePath) {
    this.basePath = languagesBasePath;
  }

  @Override
  public Language load(String languagePrefix) throws Exception {
    Path langPath = basePath.resolve(languagePrefix + ".yml");
    if (Files.exists(langPath, LinkOption.NOFOLLOW_LINKS)) {
      try {
        Reader file = Files.newBufferedReader(langPath, Charset.defaultCharset());
        return parseLanguageFile(file);
      } catch (Exception e) {
        throw new Exception("Can't read language file " + langPath, e);
      }
    } else {
      throw new Exception("Language file " + langPath + " not exists");
    }
  }

  private Language parseLanguageFile(Reader file) {
    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

    String name = config.getString("name", "EMPTY");
    String prefix = config.getString("prefix", "EMPTY");
    String author = config.getString("author", "EMPTY");
    String version = config.getString("version", "EMPTY");

    Set<String> paths = config.getKeys(true);

    paths.remove("name");
    paths.remove("prefix");
    paths.remove("author");
    paths.remove("version");

    Map<String, String> strings = new HashMap<String, String>();
    for (String path : paths) {
      strings.put(path, config.getString(path));
    }

    return new Language(name, prefix, author, version, strings);
  }

}
