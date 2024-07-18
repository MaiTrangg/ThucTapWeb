package util;

import java.util.Random;
import java.util.UUID;

public class UniqueStringGenerator {

    public static String generateRandomPassword() {
        // Độ dài của password
        int passwordLength = 6;
        // Mảng chứa các ký tự cho password
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();

        // Tạo một đối tượng Random
        Random random = new Random();

        // Tạo password ngẫu nhiên có độ dài 6 ký tự
        for (int i = 0; i < passwordLength; i++) {
            // Chọn một ký tự ngẫu nhiên từ mảng characters
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            // Thêm ký tự này vào password
            password.append(randomChar);
        }

        return password.toString();
    }

	public static String generateUniqueString() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        // Tạo và in ra một chuỗi không trùng
        String uniqueString = generateUniqueString();
        System.out.println("Unique String: " + uniqueString);
    }



}
