package Model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
public class IP2LocationAPI {
    private static final String API_KEY = "03690BB94F98D9618614F6E0E2EB9627";

    public static void main(String[] args) {
        String ipAddress = "8.8.8.8";
        try {
            String country = getCountryByIP(ipAddress);
            System.out.println("Country: " + country);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCountryByIP(String ipAddress) throws Exception {
        String url = "https://api.ip2location.com/v2/?ip=" + ipAddress + "&key=" + API_KEY + "&package=WS25&format=json";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        // Đóng các stream
        in.close();
        connection.disconnect();

        // Xử lý dữ liệu JSON nhận được
        JSONObject jsonResponse = new JSONObject(content.toString());
        return jsonResponse.getString("country_name");
    }
}
