package rssParser;

import org.jsoup.Jsoup;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import javax.swing.text.Document;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RssParser {

    public  RssParser (String url)throws Exception{
            URL website = new URL(url);
           /* BufferedReader in = new BufferedReader(
                    new InputStreamReader(website.openStream()));

            FileWriter fw = new FileWriter("test.xml",false);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                    inputLine=inputLine.replace("&apos;","'");
                    //inputLine=inputLine.replace("&nbsp;"," ");
                    //inputLine=inputLine.replace("&amp;","&");
                    fw.write(inputLine);
                    fw.write("\n");
            }
            fw.close();
            in.close();*/
            final Path target = Paths.get("test.xml");
            try (InputStream in = website.openStream()) {
            Files.copy(in,target, StandardCopyOption.REPLACE_EXISTING);
            }
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            CNBCxmlHandler handler = new CNBCxmlHandler();
            parser.parse(new File("test.xml"), handler);


    }
}

