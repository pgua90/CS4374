package Utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utilities {

    public static boolean areAllNull(Object... fields){
        System.out.println("in areAllNull");
        boolean areAllNull = true;
        for(Object field : fields){
            if(field != null){
                areAllNull = false;
                break;
            }
        }
        return areAllNull;
    }

    String encrypt(String passwordInput){
        String hash = passwordInput;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashPassword = passwordEncoder.encode(hash);
        System.out.println(hashPassword);
        return hashPassword;
    }
}

