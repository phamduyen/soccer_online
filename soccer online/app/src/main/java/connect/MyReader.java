package connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Nara on 16/04/2016.
 */
public class MyReader {




    public static String readFromUrl(String url, String method) throws IOException {

        URL ur = new URL(url);
        HttpURLConnection httpConn = (HttpURLConnection) ur.openConnection();

        httpConn.setAllowUserInteraction(false);
        httpConn.setInstanceFollowRedirects(true);
        httpConn.setRequestMethod(method);
        httpConn.connect();
        InputStream is = httpConn.getInputStream();

        BufferedReader rd = new BufferedReader (new InputStreamReader(is, Charset.forName("UTF-8")));
        StringBuilder sb = new StringBuilder();
        int  cp;
        while (((cp = rd.read()) != -1)) {
            sb.append((char) cp);
        }

        String text = sb.toString();
        return text;
    }
}

