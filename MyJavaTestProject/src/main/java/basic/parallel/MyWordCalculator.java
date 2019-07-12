package basic.parallel;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MyWordCalculator {

    private int counter = 0;
    private boolean currentIsBlank = true;

    // tryAdvance 会调用的“下一步”计算方法
    // 可参考for 循环的实现方式
    public MyWordCalculator calculate(Character c) {
        if (Character.isWhitespace(c)) {
            return currentIsBlank ? this : new MyWordCalculator(counter, true);
        }
        else {
            return currentIsBlank ? new MyWordCalculator(counter + 1, false) : this;
        }
    }

    // 合并两个计数器，这里“是否是空格”参数应该返回什么都没关系
    public MyWordCalculator combine(MyWordCalculator anoWordCalculator) {
        return new MyWordCalculator(this.counter + anoWordCalculator.counter, anoWordCalculator.currentIsBlank);
    }

}