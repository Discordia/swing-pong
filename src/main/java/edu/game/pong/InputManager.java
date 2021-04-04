package edu.game.pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class InputManager extends KeyAdapter {
    private Map<Integer, Boolean> keyMap = new HashMap<>();

    public boolean isKeyDown(int key) {
        return keyMap.containsKey(key) && keyMap.get(key);
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        keyMap.put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        keyMap.put(e.getKeyCode(), false);
    }
}
