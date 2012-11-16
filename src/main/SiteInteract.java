package main;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * User: mnijurin
 * Date: 11/15/12
 * Time: 3:02 PM
 */
public class SiteInteract {

    public void fetchImageFromUrl(String url) throws Exception {
        HttpUtils httpUtils = new HttpUtils();
        List<String> webPageSourceCode = httpUtils.getStringsFromURL(url);

        String imageUrl = "";
        String fileName = "";

        for (String line : webPageSourceCode) {
            List<String> strings = Regex.regexIt(line, ".*(\\/yowidget\\/wcam1.+\\/(.+\\.jpg)):.*");
            imageUrl = strings != null ? strings.get(0) : "";
            fileName = strings != null ? strings.get(1) : "";

            if (!"".equals(imageUrl)) {
                break;
            }
        }

        System.out.println("imageUrl = " + imageUrl + " file name = " + fileName);

        if (!"".equals(imageUrl) && !"".equals(fileName)) {
            URL urlGetRequest = new URL("http://www.karakol-ski.kg" + imageUrl);

            URLConnection conn = urlGetRequest.openConnection();
            InputStream in = conn.getInputStream();
            String contentType = conn.getHeaderField("Content-Type");
            if (!"image/jpeg".equals(contentType)) {
                // hack: assuming it's mime if not a raw image
                int one = in.read();
                if (one == -1) {
                    // stop??
                }
                int two = in.read();
                while (two != -1 && !(two == 10 && one == 10)) {
                    one = two;
                    two = in.read();
                }
            }
// if it was mime, we've stripped off the mime headers
// and should now get the image
            inputStream2Disk(in, new SimpleDateFormat("yyyy.MM.dd_hh:mm:ss").format(new Date().getTime()) + "_" + fileName);
        }
    }

    private void inputStream2Disk(InputStream in, String fileName) throws Exception {
        File outputFile = new File(fileName);
        OutputStream out = new FileOutputStream(outputFile);
        byte buf[] = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0)
            out.write(buf, 0, len);
        out.close();
        in.close();
    }
}
