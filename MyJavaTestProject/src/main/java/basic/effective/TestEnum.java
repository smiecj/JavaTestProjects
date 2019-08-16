package basic.effective;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author shuaifeng
 * @Since 2019/8/16
 * 测试枚举类相关的用法
 */
public class TestEnum {

    public static void main(String[] args) {
        // 大数据统计某个人的全部性格
        EnumSet<Characteristic> charaSet = EnumSet.noneOf(Characteristic.class);

        charaSet.addAll(Stream.of(Characteristic.ACTIVE, Characteristic.CONCENTRATED).collect(Collectors.toList()));
        charaSet.addAll(Stream.of(Characteristic.INTROVERTED, Characteristic.CONCENTRATED).collect(Collectors.toList()));

        // 预期结果：打印去重之后的三种性格
        charaSet.forEach(System.out::println);

        // 保存某种性格下的所有人
        EnumMap<Characteristic, Set<String>> charaToPeopleMap = new EnumMap<Characteristic, Set<String>>(Characteristic.class);
        Set<String> activePerson = charaToPeopleMap.get(Characteristic.ACTIVE);
        if (null == activePerson) {
            activePerson = new HashSet<>();
        }
        activePerson.add("xiaoming");
        charaToPeopleMap.put(Characteristic.ACTIVE, activePerson);
    }

    /**
     * 内部类：人的性格
     */
    private enum Characteristic {
        ACTIVE, QUIET, COOPERATIVE, INTROVERTED, CONCENTRATED
    }

}
