package basic.parallel;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MySplitter implements Spliterator<Character> {

    private String currentStr;
    private int currentPos;

    public MySplitter(String str) {
        currentStr = str;
        currentPos = 0;
    }

    // 对分区后的字符串进行单词数量的计算
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(currentStr.charAt(currentPos++));
        return currentPos < currentStr.length();
    }

    // 拆分。基本算法：对半拆分
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = currentStr.length() - currentPos;
        if (currentSize <= 4) {
            return null;
        }
        // 将右边的部分拆分出去
        for (int splitPos = currentSize / 2 + currentPos; splitPos < currentStr.length(); splitPos++) {
            if (Character.isWhitespace(currentStr.charAt(splitPos))) {
                MySplitter newSplitter = new MySplitter(currentStr.substring(currentPos, splitPos));
                currentPos = splitPos;
                return newSplitter;
            }
        }
        return null;
    }

    // 返回当前分解器需要处理的子元素大小，不会对最后计算的正确性产生影响
    @Override
    public long estimateSize() {
        return currentStr.length() - currentPos;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }

    public static void main(String[] args) {
        MySplitter splitter = new MySplitter("This is my word");
        Stream<Character> stream = StreamSupport.stream(splitter, true);
        MyWordCalculator wordCalculator = stream.reduce(new MyWordCalculator(0, true), MyWordCalculator::calculate, MyWordCalculator::combine);
        System.out.println("统计出来的单词总数为：" + wordCalculator.getCounter());
    }
}