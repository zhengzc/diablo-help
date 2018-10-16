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
public class Bar_bingdizhen implements Action{
    private Robot robot;
    private Thread tiaozhan;//跳斩
    private Thread kuangBaoZheZhiNu;

    public Bar_bingdizhen() throws Exception{
        robot = new Robot();
    }


    @Override
    public void start() {
        robot.keyPress(KeyEvent.VK_9);

        if (tiaozhan == null || !tiaozhan.isAlive()) {
            tiaozhan = new Tiaozhan();
            tiaozhan.start();
        }

        if (kuangBaoZheZhiNu == null || !kuangBaoZheZhiNu.isAlive()) {
            kuangBaoZheZhiNu = new KuangBaoZheZhiNu();
            kuangBaoZheZhiNu.start();
        }
    }

    @Override
    public void stop() {
        robot.keyRelease(KeyEvent.VK_9);
        tiaozhan.interrupt();
        kuangBaoZheZhiNu.interrupt();
    }

    class Tiaozhan extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    //施放跳斩
                    int count = 6;
                    for(int i = 0 ; i < count ; i++) {
                        robot.keyPress(KeyEvent.VK_2);
                        Thread.sleep(5);
                        robot.keyRelease(KeyEvent.VK_2);
                        Thread.sleep(2000/count);
                    }

                    Thread.sleep(100);

                    robot.keyPress(KeyEvent.VK_3);
                    Thread.sleep(5);
                    robot.keyRelease(KeyEvent.VK_3);

                    Thread.sleep(300);
                }
            } catch (Throwable throwable) {
                log.info("tiaozhan stop");
            }
        }
    }

    class KuangBaoZheZhiNu extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    robot.keyPress(KeyEvent.VK_1);
                    Thread.sleep(5);
                    robot.keyRelease(KeyEvent.VK_1);
                    Thread.sleep(200);

                    robot.keyPress(KeyEvent.VK_4);
                    Thread.sleep(5);
                    robot.keyRelease(KeyEvent.VK_4);
                    Thread.sleep(200);

                    robot.mousePress(InputEvent.BUTTON3_MASK);
                    Thread.sleep(5);
                    robot.mouseRelease(InputEvent.BUTTON3_MASK);
                    Thread.sleep(200);

                }
            } catch (Throwable throwable) {
                log.info("liedizhan stop");
            }
        }
    }
}
