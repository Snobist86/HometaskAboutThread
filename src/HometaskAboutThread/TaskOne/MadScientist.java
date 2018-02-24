package HometaskAboutThread.TaskOne;

import java.util.HashMap;
import java.util.Map;

public class MadScientist extends Thread {

    private final Map<String, Integer> mapOne;
    private final Map<String, Integer> mapTwo;

    public MadScientist(Map<String, Integer> mapOne, Map<String, Integer> mapTwo) {
        this.mapOne = mapOne;
        this.mapTwo = mapTwo;
    }

    @Override
    public void run() {
        int robotCount = 0;
        TaskUtil.startingPosition(mapTwo);
        Map<String, Integer> pickingOfDetails = new HashMap<>();

        for (int i = 0; i < TaskUtil.DURATION_OF_COMPETITION; i++) {

            synchronized (mapOne) {
                try {
                    System.out.println(Thread.currentThread().getName() + " ждёт ночи");
                    mapOne.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TaskUtil.HenchmanTakes(mapOne, pickingOfDetails);

            }
            System.out.println("приспешник " + Thread.currentThread().getName() + " собрал: " + pickingOfDetails);
            TaskUtil.transferComponentsToMadScientist(pickingOfDetails, mapTwo);
            System.out.println("у " + Thread.currentThread().getName() + " в наличии: " + mapTwo);

            if (TaskUtil.isCreateRobot(mapTwo)) {
                TaskUtil.createRobot(mapTwo);
                robotCount = ++robotCount;
                System.out.println(Thread.currentThread().getName() + "построил 1 робота");
            }
        }
        System.out.println(Thread.currentThread().getName() + " построил роботов: " + robotCount);
    }
}

