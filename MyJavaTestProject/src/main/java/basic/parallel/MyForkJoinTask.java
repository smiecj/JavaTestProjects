package basic.parallel;

import lombok.AllArgsConstructor;
import model.Student;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
public class MyForkJoinTask extends RecursiveTask<Integer> {

    private Student[] students;

    private int start;

    private int end;

    public static void main(String[] args) {
        Student xiaoming = new Student("xiaoming", 87);
        Student xiaohong = new Student("xiaohong", 90);
        Student xiaoqiang = new Student("xiaoqiang", 100);
        Student xiaofeng = new Student("xiaofeng", 100);

        Student[] students = new Student[]{xiaoming, xiaohong, xiaoqiang, xiaofeng};
        MyForkJoinTask myForkJoinTask = new MyForkJoinTask(students, 0, students.length - 1);
        int sumOfGrade = new ForkJoinPool().invoke(myForkJoinTask);
        System.out.println("学生的总成绩为：" + sumOfGrade);
    }

    @Override
    protected Integer compute() {
        // 判断终止条件
        if (start + 1 >= end) {
            return students[start].getScore() + students[end].getScore();
        }

        // 拆分任务
        int mid = (start + end) / 2;
        MyForkJoinTask leftTask = new MyForkJoinTask(students, start, mid);
        leftTask.fork();
        MyForkJoinTask rightTask = new MyForkJoinTask(students, mid + 1, end);

        int leftTaskResult = leftTask.join();
        int rightTaskResult = rightTask.compute();

        // 完成：返回求和的结果
        return leftTaskResult + rightTaskResult;
    }
}