package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: mnijurin
 * Date: 9/14/12
 * Time: 3:48 PM
 */
public class HttpUtils {

    public List<String> getStringFromURL(String Url) throws IOException {
        System.out.println("getting strings from url " + new SimpleDateFormat("mm.ss").format(new Date().getTime()));
        URL urlGetRequest = new URL(Url);
        URLConnection urlConnection = urlGetRequest.openConnection();
        BufferedReader inputSequence = new BufferedReader(new InputStreamReader(
                urlConnection.getInputStream(), "cp1251"));

        String inputLine;
        List<String> response  = new ArrayList<String>();
        while ((inputLine = inputSequence.readLine()) != null){
            response.add(inputLine);
        }
        inputSequence.close();

        return response;
    }

}
