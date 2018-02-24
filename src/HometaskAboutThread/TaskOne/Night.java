package HometaskAboutThread.TaskOne;

import java.util.Map;

public class Night extends Thread {

    private static final int DURATION_OF_NIGHT = 100;
    private final Map<String, Integer> map;

    public Night(Map<String, Integer> map){
        this.map = map;
    }

    @Override
    public void run() {
        for (int i = 1; i <= TaskUtil.DURATION_OF_COMPETITION; i++) {
            try {
                Thread.sleep(DURATION_OF_NIGHT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (map) {
                System.out.println();
                System.out.println(i + " ночь");
                map.notifyAll();
            }
        }
    }
}
