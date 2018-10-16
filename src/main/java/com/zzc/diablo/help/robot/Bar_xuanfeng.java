package com.zzc.diablo.help.robot;

import com.zzc.diablo.help.common.Action;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * 野蛮人
 */
@Slf4j
public class Bar_xuanfeng implements Action{
    private Robot robot;
    private Thread kuangBaoZheZhiNu;
    private Thread buff2;

    public Bar_xuanfeng() throws Exception{
        robot = new Robot();
    }


    @Override
    public void start() {
        robot.keyPress(KeyEvent.VK_3);
        if (kuangBaoZheZhiNu == null || !kuangBaoZheZhiNu.isAlive()) {
            kuangBaoZheZhiNu = new BuffThread();
            kuangBaoZheZhiNu.start();
        }

        if (buff2 == null || !buff2.isAlive()) {
            buff2 = new Buff2Thread();
            buff2.start();
        }
    }

    @Override
    public void stop() {
        robot.keyRelease(KeyEvent.VK_3);
        kuangBaoZheZhiNu.interrupt();
        buff2.interrupt();
    }

    class BuffThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    robot.keyPress(KeyEvent.VK_1);
                    Thread.sleep(5);
                    robot.keyRelease(KeyEvent.VK_1);

                    robot.keyPress(KeyEvent.VK_2);
                    Thread.sleep(5);
                    robot.keyRelease(KeyEvent.VK_2);

                    robot.mousePress(InputEvent.BUTTON3_MASK);
                    Thread.sleep(5);
                    robot.mouseRelease(InputEvent.BUTTON3_MASK);

                    Thread.sleep(1000 * 1);
                }
            } catch (Throwable throwable) {
                log.info("buffThread stop");
            }
        }
    }

    class Buff2Thread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    robot.keyPress(KeyEvent.VK_4);
                    Thread.sleep(5);
                    robot.keyRelease(KeyEvent.VK_4);

                    robot.keyPress(KeyEvent.VK_BACK_SPACE);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    Thread.sleep(5);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    robot.keyRelease(KeyEvent.VK_BACK_SPACE);

                    Thread.sleep(1000 * 60);
                }
            } catch (Throwable throwable) {
                log.info("buffThread stop");
            }
        }
    }
}
