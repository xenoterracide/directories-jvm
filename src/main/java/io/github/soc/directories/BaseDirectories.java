package io.github.soc.directories;

import static io.github.soc.directories.Util.*;

public final class BaseDirectories {

	static final String homeDir;

	// xdg base directories
	static final String cacheDir;
	static final String configDir;
	static final String dataDir;
	static final String runtimeDir;

	// xdg user directories
	static final String desktopDir;
	static final String documentsDir;
	static final String downloadDir;
	static final String musicDir;
	static final String picturesDir;
	static final String publicDir;
	static final String templatesDir;
	static final String videosDir;

	// derived
	static final String executablesDir;
	static final String fontsDir;

  static {
    switch (operatingSystem) {
      case LIN:
        homeDir        = System.getenv("HOME");
        cacheDir       = defaultIfNullOrEmpty(System.getenv("XDG_CACHE_HOME"),  homeDir + "/.cache/");
        configDir      = defaultIfNullOrEmpty(System.getenv("XDG_CONFIG_HOME"), homeDir + "/.config/");
        dataDir        = defaultIfNullOrEmpty(System.getenv("XDG_DATA_HOME"),   homeDir + "/.local/share/");
        runtimeDir     = System.getenv("XDG_RUNTIME_DIR");
        desktopDir     = defaultIfNullOrEmpty(runXDGUserDir("DESKTOP"),         homeDir + "/Desktop/");
        documentsDir   = defaultIfNullOrEmpty(runXDGUserDir("DOCUMENTS"),       homeDir + "/Documents/");
        downloadDir    = defaultIfNullOrEmpty(runXDGUserDir("DOWNLOAD"),        homeDir + "/Downloads/");
        musicDir       = defaultIfNullOrEmpty(runXDGUserDir("MUSIC"),           homeDir + "/Music/");
        picturesDir    = defaultIfNullOrEmpty(runXDGUserDir("PICTURES"),        homeDir + "/Pictures/");
        publicDir      = defaultIfNullOrEmpty(runXDGUserDir("PUBLICSHARE"),     homeDir + "/Public/");
        templatesDir   = defaultIfNullOrEmpty(runXDGUserDir("TEMPLATES"),       homeDir + "/Templates/");
        videosDir      = defaultIfNullOrEmpty(runXDGUserDir("VIDEOS"),          homeDir + "/Videos/");
        executablesDir = defaultIfNullOrEmpty(System.getenv("XDG_DATA_HOME"),   homeDir + "/.local/bin/");
        fontsDir       = defaultIfNullOrEmpty(System.getenv("XDG_DATA_HOME"),   homeDir + "/.local/share/fonts/");
        break;
      case MAC:
        homeDir        = System.getenv("HOME");
  		  cacheDir       = homeDir + "/Library/Caches/";
  		  configDir      = homeDir + "/Library/Preferences/";
  		  dataDir        = homeDir + "/Library/Application Support/";
  		  runtimeDir     = null;
  		  desktopDir     = homeDir + "/Desktop/";
  		  documentsDir   = homeDir + "/Documents/";
  		  downloadDir    = homeDir + "/Downloads/";
  		  musicDir       = homeDir + "/Music/";
  		  picturesDir    = homeDir + "/Pictures/";
  		  publicDir      = null;
  		  templatesDir   = null;
  		  videosDir      = homeDir + "/Movies/";
  		  executablesDir = null;
  		  fontsDir       = homeDir + "/Library/Fonts/";
        break;
      case WIN:
        homeDir        = null;
        dataDir        = runPowerShellCommand("LocalApplicationData");
        configDir      = dataDir;
        cacheDir       = dataDir + "/cache";
        runtimeDir     = null;
        desktopDir     = runPowerShellCommand("Desktop");
        documentsDir   = runPowerShellCommand("Documents");
        downloadDir    = runPowerShellCommand("Downloads");
        musicDir       = runPowerShellCommand("Music");
        picturesDir    = runPowerShellCommand("Pictures");
        publicDir      = runPowerShellCommand("Public");
        templatesDir   = runPowerShellCommand("Templates");
        videosDir      = runPowerShellCommand("Videos");
        executablesDir = null;
        fontsDir       = null;
        break;
      default:
        throw new UnsupportedOperatingSystemException("Base directories are not supported on " + operatingSystemName);
    }
  }
}