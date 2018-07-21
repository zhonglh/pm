package com.common.utils.encrypt;

import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import com.common.utils.Base64Kit;
import com.common.utils.SystemKit;

public class DESKit {

    private static Key    key;
    private static String KEY_STR     = "hlWdy7BkxfeftwXPHE518oPv5w6rwFM0nDU0kcmLIOz75IaaQubn50pCB7ihLNGqEX8KilImQeYTctskMLlIS5vxb6kjmUvyAQkt"; // 密钥
    private static String CHARSETNAME = "UTF-8";                                                                                               // 编码
    private static String ALGORITHM   = "DES";                                                                                                 // 加密类型

    static {
        try {
            Security.getProviders ();
            SecureRandom secureRandom = SecureRandom.getInstance ("SHA1PRNG");
            if (SystemKit.isIBMJDKVendor ()) {
                Security.addProvider ((Provider) Class.forName ("com.ibm.crypto.provider.IBMJCE").newInstance ());
            } else {
                Security.addProvider ((Provider) Class.forName ("com.sun.crypto.provider.SunJCE").newInstance ());
            }
            secureRandom.setSeed (KEY_STR.getBytes (CHARSETNAME));
            KeyGenerator generator = KeyGenerator.getInstance (ALGORITHM);
            generator.init (secureRandom);
            key = generator.generateKey ();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException (e);
        }
    }

    /**
     * 对str进行DES加密
     * 
     * @param str
     * @return
     */
    public static String encrypt(String str){
        try {
            byte[] bytes = str.getBytes (CHARSETNAME);
            Cipher cipher = Cipher.getInstance (ALGORITHM);
            cipher.init (Cipher.ENCRYPT_MODE, key);
            byte[] doFinal = cipher.doFinal (bytes);
            return Base64Kit.encode (doFinal);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return str;
    }

    /**
     * 对str进行DES解密
     * 
     * @param str
     * @return
     */
    public static String decrypt(String str){
        try {
            byte[] bytes = Base64Kit.decode (str);
            Cipher cipher = Cipher.getInstance (ALGORITHM);
            cipher.init (Cipher.DECRYPT_MODE, key);
            byte[] doFinal = cipher.doFinal (bytes);
            return new String (doFinal,CHARSETNAME);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return str;
    }

    public static void main(String[] args){
        System.out.println (DESKit.encrypt ("postgres"));
        System.out.println (DESKit.decrypt (DESKit.encrypt ("postgres")));
        System.out.println (DESKit.decrypt ("vk1m72URKEyKK86LiN5BrQ=="));
    }
}
