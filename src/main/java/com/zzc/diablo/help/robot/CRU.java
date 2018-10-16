package com.zzc.diablo.help.robot;

import com.zzc.diablo.help.common.ActionSupport;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * 圣教军
 */
@Slf4j
public class CRU extends ActionSupport{
    public CRU() throws AWTException {
        super();
    }

    @Override
    public void start() {
        robot.keyPress(KeyEvent.VK_9);
        start(new BuffThread());
        start(new MainSpellThread());
        start(new Main2SpellThread());
    }

    @Override
    public void stop() {
        robot.keyRelease(KeyEvent.VK_9);
        super.stop();
    }

    class BuffThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    mouseClick(InputEvent.BUTTON3_MASK);
                    click(KeyEvent.VK_4);
                    Thread.sleep(2500);
                }
            } catch (Throwable throwable) {
                log.info("buffThread stop");
            }
        }
    }

    class MainSpellThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    click(KeyEvent.VK_2);
                    click(KeyEvent.VK_1);
                    Thread.sleep(2000);
                }
            } catch (Throwable throwable) {
                log.info("MainSpellThread stop");
            }
        }
    }

    class Main2SpellThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    click(KeyEvent.VK_3);
                    castMouseLeft();
                    Thread.sleep(500);
                }
            } catch (Throwable throwable) {
                log.info("Main2SpellThread stop");
            }
        }
    }
}
