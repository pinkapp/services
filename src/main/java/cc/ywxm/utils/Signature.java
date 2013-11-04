package cc.ywxm.utils;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;


/**
 * 生成签名类
 * @author HUANGDECAI
 */

public class Signature {

    /**
     * URL编码 (符合FRC1738规范)
     *
     * @param input 待编码的字符串
     * @return 编码后的字符串
     *          不支持指定编码时抛出异常。
     */
    public static String encodeUrl(String input) throws UnsupportedEncodingException {
            return URLEncoder.encode(input, CONTENT_CHARSET).replace("+", "%20").replace("*", "%2A");
    }

    /**
     * 验证签名
     * @param method
     * @param url_path
     * @param params
     * @param secret
     * @param sig
     * @return
     * @throws Exception
     */
    public static boolean verify(String method, String url_path, Map<String, String> params, String secret,String sig) throws Exception {
        return makeSig(method,url_path,params,secret).equals(sig);
    }

    /* 生成签名
     *
     * @param method HTTP请求方法 "get" / "post"
     * @param url_path CGI名字, eg: /v3/user/get_info
     * @param params URL请求参数
     * @param secret 密钥
     * @return 签名值
     */
    public static String makeSig(String method, String url_path, Map<String, String> params, String secret) throws Exception {
        Mac mac = Mac.getInstance(HMAC_ALGORITHM);

        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(CONTENT_CHARSET), mac.getAlgorithm());

        mac.init(secretKey);

        String mk = makeSource(method, url_path, params);

        byte[] hash = mac.doFinal(mk.getBytes(CONTENT_CHARSET));

        // base64
        String sig = new String(Base64Coder.encode(hash));


        return sig;
    }

    /* 生成签名所需源串
     *
     * @param method HTTP请求方法 "get" / "post"
     * @param url_path CGI名字, eg: /v3/user/get_info
     * @param params URL请求参数
     * @return 签名所需源串
     */
    public static String makeSource(String method, String url_path, Map<String, String> params) throws Exception{
        Object[] keys = params.keySet().toArray();

        Arrays.sort(keys);

        StringBuilder buffer = new StringBuilder(128);

        buffer.append(method.toUpperCase()).append("&").append(encodeUrl(url_path)).append("&");

        StringBuilder buffer2 = new StringBuilder();

        for (int i = 0; i < keys.length; i++) {
            buffer2.append(keys[i]).append("=").append(params.get(keys[i]));

            if (i != keys.length - 1) {
                buffer2.append("&");
            }
        }

        buffer.append(encodeUrl(buffer2.toString()));

        return buffer.toString();
    }

    // 编码方式
    private static final String CONTENT_CHARSET = "UTF-8";

    // HMAC算法
    private static final String HMAC_ALGORITHM = "HmacSHA1";
}
