package rssParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CNBCxmlHandler extends DefaultHandler {
    private String lastElementName, link, title,description, pubDate;


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        lastElementName = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(link!=null && title!=null && description!=null&& pubDate!=null){
           // News.news.add(new News(pubDate, title, description,link));

            News article = new News();
            article.setPubDate(pubDate);
            article.setTitle(title);
            article.setDescription(description);
            article.setLink(link);
            News.newsList.add(article);

            pubDate = null;
            title = null;
            description = null;
            link =null;
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String information = new String(ch,start,length);
        information =information.replace("\n", "").trim();


        if (!information.isEmpty()) {
            if (lastElementName.equals("link"))
                link = information;
            if (lastElementName.equals("title"))
                title = information;
            if (lastElementName.equals("description"))
                description = information;
            if (lastElementName.equals("pubDate"))
                pubDate = information;
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        super.ignorableWhitespace(ch, start, length);
    }
}
