package com.zzc.diablo.help.robot.wow;

import com.zzc.diablo.help.common.Action;
import com.zzc.diablo.help.robot.diablo.Bar_bingdizhen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * @auther Created by johnson.zheng
 * @pray Code is far away from bug with the animal alpaca protecting. amen!
 * @keep it simple and stupid!
 * @date 2019/9/7
 */
public class WowJump implements Action{
    private Robot robot;
    private Thread jump;

    public WowJump() throws Exception{
        this.robot = new Robot();
    }

    @Override
    public void start() {
        if (jump == null || !jump.isAlive()) {
            jump = new JumpThread();
            jump.start();
        }
    }

    @Override
    public void stop() {
        jump.interrupt();
    }

    class JumpThread extends Thread{
        @Override
        public void run() {
            try {
                while (true) {
                    robot.keyPress(KeyEvent.VK_SPACE);
                    Thread.sleep(5);
                    robot.keyRelease(KeyEvent.VK_SPACE);

                    int sleepTime = new Random().nextInt(10);
                    System.out.println("sleep time " + sleepTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
