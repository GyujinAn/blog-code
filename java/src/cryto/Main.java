package cryto;

public class Main {

    public static void main(String[] args) {
        String param = "hello";
        AES256 aes256 = new AES256();

        try {
            String encrypt = aes256.encrypt(param);
            System.out.println(encrypt);
            String decrypt = aes256.decrypt(encrypt);
            System.out.println(decrypt);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
