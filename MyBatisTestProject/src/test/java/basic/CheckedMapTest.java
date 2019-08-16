package basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author shuaifeng
 * @Since 2019/8/13
 */
public class CheckedMapTest {

    @Test
    public void testCheckedMap() {
        JSONObject obj = new JSONObject();
        obj.put("stringvalue", "lifeng");
        obj.put("intvalue", 1);

        Map strMap = new HashMap<>();

        Map checkedMap = Collections.checkedMap(strMap, String.class, String.class);
        obj.forEach((key, value) -> {
            checkedMap.put(key, value);
        });
    }

}
