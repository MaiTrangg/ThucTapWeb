package util;

import java.util.UUID;

public class UniqueStringGenerator {



	public static String generateUniqueString() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        // Tạo và in ra một chuỗi không trùng
        String uniqueString = generateUniqueString();
        System.out.println("Unique String: " + uniqueString);
    }



}
