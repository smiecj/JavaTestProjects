package basic;

import model.HomeWork;
import model.ScoreLevel;
import model.Student;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {

    public static void main(String[] args) {
        Student xiaoming = new Student("小明", 90);
        Student xiaohong = new Student("小红", 100);
        Student xiaolan = new Student("小兰", 96);

        List<Student> studentList = Stream.of(xiaoming, xiaohong, xiaolan).collect(Collectors.toList());

        // 场景1：所有学生写完作业，作业写完之后被批改
        List<CompletableFuture<HomeWork>> homeWorkFutures = studentList.stream().map(student -> CompletableFuture.supplyAsync(() -> student.doHomeWork())).collect(Collectors.toList());
        List<HomeWork> homeWorks = homeWorkFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        homeWorks.forEach(System.out::println);

        // 场景2：作业写完之后，每个作业进行批改，批改完成之后再次拿去评等级
        System.out.println("开始给每一个学生的成绩打分！");
        List<CompletableFuture<ScoreLevel>> scoreLevelFutures = studentList.stream().map(student -> CompletableFuture.supplyAsync(() -> student.doHomeWork()))
                .map(future -> future.thenApply(HomeWork::makeScore))
                .map(future -> future.thenCompose(homeWork -> CompletableFuture.supplyAsync(() -> homeWork.getLevel()))).collect(Collectors.toList());

        List<ScoreLevel> levels = scoreLevelFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        levels.forEach(System.out::println);

        // 场景3：所有的作业评分之后进行合并，求总分
        // 场景感觉不太好，先不写了
    }


}
