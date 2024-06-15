package Model;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class Geo_API {
    public static void main(String[] args) throws IOException, GeoIp2Exception {
        String IP = "103.199.41.244";
    new Geo_API().getLocation(IP);

    }
    //lấy ra tên quốc gia dựa trênIP Address của người dùng
    public String getLocation(String IP) throws IOException, GeoIp2Exception {
        // Đường dẫn tới cơ sở dữ liệu GeoLite2-City
        String dbLocation = "F:\\GeoLite2-City_20240517\\GeoLite2-City.mmdb";

        // Tạo một đối tượng File để đại diện cho tệp cơ sở dữ liệu
        File database = new File(dbLocation);

        // Tạo một DatabaseReader để đọc từ cơ sở dữ liệu GeoLite2-City
        DatabaseReader dbr = new DatabaseReader.Builder(database).build();

        // Chuyển đổi địa chỉ IP từ chuỗi sang đối tượng InetAddress
        InetAddress ipa = InetAddress.getByName(IP);

        // Truy vấn cơ sở dữ liệu để lấy thông tin về thành phố của địa chỉ IP
        CityResponse response = dbr.city(ipa);

        // Lấy tên quốc gia từ phản hồi và in ra màn hình
        String country = response.getCountry().getName();
        System.out.println("Country: " + country);
        return country;

        // Lấy tên thành phố từ phản hồi
//        String city = response.getCity().getName();
    }

//    public String getClientIp(HttpServletRequest request) {
//        String ipAddress = request.getHeader("X-Forwarded-For");
//        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getHeader("Proxy-Client-IP");
//        }
//        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getHeader("HTTP_CLIENT_IP");
//        }
//        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getRemoteAddr();
//        }
//        return ipAddress;
//    }

}
