package rssParser;

import application.Bot;
import dbHandler.DBController;
import messageHandler.MessageHandler;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;

public class News extends MessageHandler {
    final static String cnbcBusinessLink = "https://www.cnbc.com/id/10001147/device/rss/rss.html";
    final static String cnbcEarningsLink= "https://www.cnbc.com/id/15839135/device/rss/rss.html";
    final static String cnbcFinanceLink="https://www.cnbc.com/id/10000664/device/rss/rss.html";

    public static String[] cnbcLinks = new String[]{cnbcBusinessLink, cnbcEarningsLink,cnbcFinanceLink};
    public static ArrayList<News> newsList = new ArrayList<>();


    private  String pubDate;
    private String title;
    private String description;


    /*public News(String pubDate, String title, String description, String link) {
        this.pubDate = pubDate;
        this.title = title;
        this.description = description;
        this.link = link;
    }*/
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    private String link;


}
