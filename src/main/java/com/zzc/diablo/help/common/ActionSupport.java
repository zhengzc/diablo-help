package com.zzc.diablo.help.common;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ActionSupport implements Action {
    public Robot robot;
    public List<Thread> threadList = new ArrayList<>();

    public ActionSupport() throws AWTException {
        robot = new Robot();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        threadList.forEach((Thread t) -> {
            t.interrupt();
        });
    }

    public void start(Thread thread) {
        if (!thread.isAlive()) {
            threadList.add(thread);
            thread.start();
        }
    }

    /**
     * 模拟键盘点击
     * @param keyEvent
     * @throws Exception
     */
    public void click(int keyEvent) throws Exception{
        robot.keyPress(keyEvent);
        Thread.sleep(5);
        robot.keyRelease(keyEvent);
    }

    /**
     * 模拟鼠标点击
     * @param inputEvent
     * @throws Exception
     */
    public void mouseClick(int inputEvent) throws Exception{
        robot.mousePress(inputEvent);
        Thread.sleep(5);
        robot.mouseRelease(inputEvent);
    }

    /**
     * 施放左键技能
     */
    public void castMouseLeft() throws Exception{
        robot.keyPress(KeyEvent.VK_SPACE);
        Thread.sleep(10);
        mouseClick(InputEvent.BUTTON1_MASK);
        robot.keyRelease(KeyEvent.VK_SPACE);
    }
}
