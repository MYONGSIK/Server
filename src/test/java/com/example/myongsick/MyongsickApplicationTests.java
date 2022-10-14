package com.example.myongsick;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyongsickApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void jasypt(){
        String url = "jdbc:h2:tcp://localhost/~/myongsik";
        String userName = "sa";
        String password = "my_db_passwod";

        System.out.println("password = " + jasyptEncrypt(password));
        System.out.println("url = " + jasyptEncrypt(url));
        System.out.println("userName = " + jasyptEncrypt(userName));
    }

    private String jasyptEncrypt(String input) {
        String key = "Myongsik2key";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.encrypt(input);
    }

}
