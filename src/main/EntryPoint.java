package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: mnijurin
 * Date: 11/15/12
 * Time: 2:53 PM
 */
public class EntryPoint {
    public static void main(String[] args) {

        final SiteInteract siteInteract = new SiteInteract();
        final Date todayDate = new Date();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_kk:mm:ss");

        try {
            if (todayDate.after(dateFormat.parse(new SimpleDateFormat("yyyy.MM.dd").format(new Date()) + "_05:00:00")) &&
                    todayDate.before(dateFormat.parse(new SimpleDateFormat("yyyy.MM.dd").format(new Date()) + "_18:30:00"))) {
                siteInteract.fetchImageFromUrl("http://www.karakol-ski.kg/yowidget/gen_pic.php");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        javax.swing.Timer timer = new Timer(40000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("next timer iteration " + new SimpleDateFormat("kk.mm.ss").format(new Date().getTime()));

                try {
                    if (todayDate.after(dateFormat.parse(new SimpleDateFormat("yyyy.MM.dd").format(new Date()) + "_06:00:00")) &&
                            todayDate.before(dateFormat.parse(new SimpleDateFormat("yyyy.MM.dd").format(new Date()) + "_18:30:00"))) {
                        siteInteract.fetchImageFromUrl("http://www.karakol-ski.kg/yowidget/gen_pic.php");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        );
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();

        while (true) ;
    }
}
