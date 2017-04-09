package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Loaded scene is a ScriptedScene more or less but with the difference that it gathers its scene data via
 * a file on the harddisk.
 * 
 * @author Michi
 *
 */
class LoadedScene extends ScriptedScene {
	/**
	 * Creates a new Scene that loads its scene data from a file.
	 * 
	 * @param filename Filename of the scenefile on disk.
	 * @throws IOException If the file wasn't found.
	 */
    LoadedScene(final String filename) throws IOException {
        super(Files.lines(Paths.get(filename)).toArray(String[]::new));
    }
}