package cn.iocoder.yudao.module.common.utils.wecom;

import cn.iocoder.yudao.module.common.exception.AesException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author Daniel Qian
 * @date 14/10/19
 */
public class SHA1 {

    /**
     * 串接arr参数，生成sha1 digest.
     */
    public static String gen(String... arr) {
        if (StringUtils.isAnyEmpty(arr)) {
            throw new IllegalArgumentException("非法请求参数，有部分参数为空 : " + Arrays.toString(arr));
        }

        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (String a : arr) {
            sb.append(a);
        }
        return DigestUtils.sha1Hex(sb.toString());
    }

    /**
     * 用&串接arr参数，生成sha1 digest.
     */
    public static String genWithAmple(String... arr) {
        if (StringUtils.isAnyEmpty(arr)) {
            throw new IllegalArgumentException("非法请求参数，有部分参数为空 : " + Arrays.toString(arr));
        }

        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String a = arr[i];
            sb.append(a);
            if (i != arr.length - 1) {
                sb.append('&');
            }
        }
        return DigestUtils.sha1Hex(sb.toString());
    }


    public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws AesException {
        try {
            String[] array = new String[]{token, timestamp, nonce, encrypt};
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < 4; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.ComputeSignatureError);
        }
    }
}
