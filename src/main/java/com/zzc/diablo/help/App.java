package com.zzc.diablo.help;

import com.melloware.jintellitype.JIntellitype;
import com.zzc.diablo.help.common.Action;
import com.zzc.diablo.help.robot.diablo.Bar_bingdizhen;
import com.zzc.diablo.help.robot.diablo.Bar_xuanfeng;
import com.zzc.diablo.help.robot.diablo.CRU;
import com.zzc.diablo.help.robot.wow.WowJump;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);
    private static Action action;

    public static void main(String[] args) throws Exception{
        log.info("app start");

        if (args.length == 0) {
            action = new Bar_xuanfeng();
        } else if ("1".equalsIgnoreCase(args[0])) {
            action = new Bar_bingdizhen();
        } else if ("2".equalsIgnoreCase(args[0])) {
            action = new CRU();
        } else if ("3".equalsIgnoreCase(args[0])) {
            action = new WowJump();
        }

        JIntellitype jIntellitype = JIntellitype.getInstance();
        //jIntellitype.registerHotKey(100, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, (int)'T');
        jIntellitype.registerHotKey(100, JIntellitype.MOD_CONTROL, 112);//ctrl+F1
        jIntellitype.registerHotKey(200, JIntellitype.MOD_CONTROL, 113);//ctrl+F2


        jIntellitype.addHotKeyListener(arg0 -> {
            log.info("event id is {}", arg0);//打印参数
            try {
                if (arg0 == 100) {
                    action.start();
                } else if (arg0 == 200) {
                    action.stop();
                }

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });

        while (true) {
            System.out.print("输入quit退出程序：");
            Scanner n = new Scanner(System.in);
            if ("quit".equalsIgnoreCase(n.nextLine())) {
                break;
            }
        }

        jIntellitype.cleanUp();
        log.info("app exit");
    }
}
