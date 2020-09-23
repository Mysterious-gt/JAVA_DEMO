package encode;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

class AESEnCode {
    public static void main(String[] args) {
        try {
            // 指定使用AES加密
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // 使用KeyGenerator生成key，参数与获取cipher对象的algorithm必须相同
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            // 指定生成的密钥长度为128
            keyGenerator.init(128);
            Key key = keyGenerator.generateKey();
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = cipher.doFinal("I am a girl".getBytes());
            String password=Base64.getEncoder().encodeToString(bytes);
            System.out.println("AES加密： " + password);

            // 由于AES加密在CBC模式下是需要有一个初始向量数组byte[] initializeVector ,
            // 而解密的时候也需要同样的初始向量，因此需要使用加密时的参数初始化解密的cipher，否则会出错
            byte[] initializeVector = cipher.getIV();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initializeVector);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            // 上面三步操作可以用此操作代替 cipher.init(Cipher.DECRYPT_MODE, key, cipher.getParameters());
            bytes = cipher.doFinal(bytes);
            System.out.println("AES解密： " + new String(bytes));
        } catch (Exception e) {
            System.out.println("exception");
        }
    }
}