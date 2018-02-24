package HometaskAboutThread.TaskOne;

import java.util.HashMap;
import java.util.Map;

public class ScientificDispute {

    public static void main(String[] args) {
        Map<String, Integer> dump = new HashMap<>();
        Map<String, Integer> storageMadScientistOne = new HashMap<>();
        Map<String, Integer> storageMadScientistTwo = new HashMap<>();

        Thread henchmanOne = new MadScientist(dump,storageMadScientistOne);
        Thread henchmanTwo = new MadScientist(dump,storageMadScientistTwo);
        Thread robotsFactory = new RobotsFactory(dump);
        Thread night = new Night(dump);
        henchmanOne.setName("madScientistOne");
        henchmanTwo.setName("madScientistTwo");
        robotsFactory.setName("robotsFactory");
        night.setName("night");

        TaskUtil.dumpSize(dump);
        System.out.println(dump);

        robotsFactory.start();
        henchmanOne.start();
        henchmanTwo.start();
        night.start();
    }
}

