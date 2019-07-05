package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class HomeWork {

    public static HomeWork EMPTY_HOME_WORK = new HomeWork("no content", "no student");

    @NonNull
    private String content;

    @NonNull
    private String studentName;

    private int score;

    public HomeWork makeScore() {
        score = new Random().ints(60, 100).limit(1).findAny().getAsInt();
        return this;
    }

    public ScoreLevel getLevel() {
        if (score <= 80) {
            return ScoreLevel.NORMAL;
        }
        else if (score <= 90) {
            return ScoreLevel.GOOD;
        }
        else {
            return ScoreLevel.GRATE;
        }
    }

    @Override
    public String toString() {
        return String.format("%s 做的作业，内容为：%s", studentName, content);
    }

}
