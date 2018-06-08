package clientapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;


public class ClientAPI {
    private HttpClient httpClient;

    public ClientAPI() {
        httpClient = HttpClientBuilder.create().build();
    }

    public static void main(String[] args) {
        System.out.println((new ClientAPI()).get("api/exhibits"));
    }

    public String get(String path) {
        StringBuilder answer = new StringBuilder();

        try {
            HttpGet request = new HttpGet(Constant.HOST + path);
            request.setHeader("Accept", "application/json");
            HttpResponse response = httpClient.execute(request);

            int responseCode = response.getStatusLine().getStatusCode();

            System.out.println("**GET** request Url: " + request.getURI());
            System.out.println("Response Code: " + responseCode);
            System.out.println("Content:");
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

            String line;
            while ((line = rd.readLine()) != null) {
                answer.append(line);
            }
        } catch (UnsupportedOperationException | IOException e) {
            e.printStackTrace();
        }
        return answer.toString();
    }

    public String post(String path, Map<String, Object> params) {
        StringBuilder answer = new StringBuilder();
        HttpPost post = new HttpPost(Constant.HOST + path);
        post.setHeader("Accept", "application/json");

        try {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            for (String key : params.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, (String) params.getOrDefault(key, "")));

            }
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = httpClient.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            System.out.println("**POST** request Url: " + post.getURI());
            System.out.println("Parameters : " + nameValuePairs);
            System.out.println("Response Code: " + responseCode);
            System.out.println("Content:");
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String line;
            while ((line = rd.readLine()) != null) {
                answer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer.toString();
    }

}