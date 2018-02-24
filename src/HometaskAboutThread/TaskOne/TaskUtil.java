package HometaskAboutThread.TaskOne;

import java.util.Map;
import java.util.Random;

public final class TaskUtil {

    private static final int NUMBER_COMPONENTS_DEFAULT = 1;
    private static final int SEARCH_EFFICIENCY = 3;
    private static final int FACTORY_EFFICIENCY = 3;
    private static final int ASSORTMENT_DETAILS = 9;
    private static final int INITIAL_DUMP_SIZE = 20;
    public static final int DURATION_OF_COMPETITION = 100;

    private TaskUtil() {
    }

    private static String componentsRobot(int i) {
        String[] robot = {"head", "body", "leftArm", "rightArm", "leftLeg", "rightLeg", "Cpu", "Ram", "Hdd"};
        return robot[i];
    }

    public static void startingPosition(Map<String, Integer> map) {
        map.put("head", 0);
        map.put("body", 0);
        map.put("leftArm", 0);
        map.put("rightArm", 0);
        map.put("leftLeg", 0);
        map.put("rightLeg", 0);
        map.put("Cpu", 0);
        map.put("Ram", 0);
        map.put("Hdd", 0);
    }

    public static void dumpSize (Map<String, Integer>map) {
        Random random = new Random();
        for (int i = 0; i < TaskUtil.INITIAL_DUMP_SIZE; i++) {
            map.merge(componentsRobot(random.nextInt(ASSORTMENT_DETAILS)), NUMBER_COMPONENTS_DEFAULT, (a, b) -> a + b);
        }
    }

    public static void factoryWaste(Map<String, Integer> map) {
        Random random = new Random();
        for (int i = 0; i < random.nextInt(FACTORY_EFFICIENCY) + 1; i++) {
            map.merge(componentsRobot(random.nextInt(ASSORTMENT_DETAILS)), NUMBER_COMPONENTS_DEFAULT, (a, b) -> a + b);
        }
    }

    public static void HenchmanTakes(Map<String, Integer> map, Map<String, Integer> take) {
        Random random = new Random();
        if (dumpIsEmpty(map)) {
            for (int i = 0; i < random.nextInt(SEARCH_EFFICIENCY) + 1; i++) {
                if (dumpIsEmpty(map)) {
                    String numberOfDetail = randomComponentFromDump(map);
                    take.put(numberOfDetail, NUMBER_COMPONENTS_DEFAULT);
                    map.merge(numberOfDetail, take.get(numberOfDetail), (a, b) -> a - b);
                }
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " не получил ни одной детали!");
        }
    }

    private static boolean dumpIsEmpty(Map<String, Integer> map) {

        return (map.values().stream().reduce(Integer.MIN_VALUE, Integer::max) != 0);
    }

    private static String randomComponentFromDump(Map<String, Integer> map) {
        Random random = new Random();
        while (true) {
            String component = componentsRobot(random.nextInt(ASSORTMENT_DETAILS));
            if (map.containsKey(component)) {
                if (map.get(component) != 0) {

                    return component;
                }
            }
        }
    }

    public static void transferComponentsToMadScientist(Map<String, Integer> mapOne, Map<String, Integer> mapTwo) {
        mapOne.forEach((k, v) -> mapTwo.merge(k, v, (a, b) -> a + b));
        mapOne.clear();
    }

    public static boolean isCreateRobot(Map<String, Integer> map) {

        return (map.values().stream().reduce(Integer.MAX_VALUE, Integer::min) != 0);
    }

    public static void createRobot(Map<String, Integer> map) {
        while (isCreateRobot(map)) {
            map.forEach((k, v) -> map.compute(k, (a, b) -> b - 1));
        }

    }
}

