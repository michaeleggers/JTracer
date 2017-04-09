package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene;

import org.junit.Test;

public class ScriptedSceneTest {
	

	@Test(expected = IllegalArgumentException.class)
	public void testScriptedScene_LookerNull() {
		new ScriptedScene("#looker[0 0 0][0 6 6] 2 2","light [0 0 0]","sphere[0 0 0] 5");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testScriptedScene_FalschesWord() {
		new ScriptedScene("looker[0 0 0][0 6 6] 2 2","licht [0 0 0]","sphere[0 0 0] 5");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testScriptedScene_ZuVieleZahlen() {
		new ScriptedScene("looker[0 0 0][0 6 6] 2 2","light [0 0 0] 8","sphere[0 0 0] 5");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testScriptedScene_ZuWenigeZahle() {
		new ScriptedScene("looker[0 0 0][0 6 6] 2 2","light [0 0]","sphere[0 0 0] 5");
	}
	@Test(expected = IllegalArgumentException.class)
	public void testScriptedScene_ZweiLooker() {
		new ScriptedScene("looker[0 0 0][0 6 6] 2 2","looker[0 0 0][0 6 6] 2 2","light [0 0 0]","sphere[0 0 0] 5");
	}
}
