package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Data
@AllArgsConstructor
public class Student {

    private String name;

    private int score;

    public HomeWork doHomeWork() {
        System.out.println(String.format("学生%s正在写作业！当前时间为：%s", name, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));

        Random r = new Random();
        int randomSleepTime = r.ints(2, 10).findFirst().getAsInt();
        try {
            Thread.sleep(randomSleepTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return HomeWork.EMPTY_HOME_WORK;
        }
        System.out.println(String.format("学生%s作业写完了！当前时间为：%s", name, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
        return new HomeWork(String.format("%s 的 作业，内容为：啦啦啦", name), name);
    }

    /*public boolean play() {
        System.out.println(String.format("学生%s正在玩！当前时间为：%s", name, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
        return true;
    }*/

}
