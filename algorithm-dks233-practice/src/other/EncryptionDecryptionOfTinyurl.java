package other;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * leetcode535. TinyURL的加密与解密
 * 参考文档：https://leetcode.cn/problems/encode-and-decode-tinyurl/solution/pythonjavatypescriptgo-mo-ni-by-himymben-yjxt/
 *
 * @author dks233
 * @create 2022-09-12-19:22
 */
@SuppressWarnings("ALL")
public class EncryptionDecryptionOfTinyurl {
    private volatile AtomicInteger id = new AtomicInteger();
    private Map<Integer, String> map = new HashMap<>();

    public String encode(String longUrl) {
        int curId = id.incrementAndGet();
        map.put(curId, longUrl);
        return String.format("http://tinyurl.com/%d", curId);
    }

    public String decode(String shortUrl) {
        String[] strs = shortUrl.split("/");
        return map.get(Integer.parseInt(strs[strs.length - 1]));
    }
}
