package HometaskAboutThread.TaskOne;

import java.util.Map;

public class RobotsFactory extends Thread{

    private final Map<String, Integer> map;

    public RobotsFactory(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        for (int i = 0; i < TaskUtil.DURATION_OF_COMPETITION; i++) {

            synchronized (map) {
                try {
                    map.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TaskUtil.factoryWaste(map);
                System.out.println("сейчас на свалке: " + map);
            }
        }
    }
}
