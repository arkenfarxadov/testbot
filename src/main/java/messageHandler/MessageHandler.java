package messageHandler;

import dbHandler.DBController;
import mailing.Mailing;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import application.Bot;
import rssParser.News;
import rssParser.RssParser;


public class MessageHandler extends  Bot {
    static Update Update;
    static SendMessage mailingMessage;
    public  void messageSender(Update update) throws Exception {
        Update = update;
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        switch (update.getMessage().getText()){
            case "/start"://    todo привести этот колхоз в нормальный вид
                message.setText("Hi, my name is FinBot. Choose an option");
                message.setReplyMarkup(new Keyboard().StartKeyboard());
                execute(message);
                break;
            case "Business Latest News":
                newsOutput(message,0);
                break;

            case "Earnings Latest News":
                newsOutput(message,1);
                break;
            case "Finance Latest News":
               newsOutput(message, 2);
                break;
            case "Auto-Mailing":
                message.setText("Choose an option");
                message.setReplyMarkup(Keyboard.newKeyboard());
                execute(message);
                break;
            case "Subscribe":
                    DBController.saveSubs(update.getMessage().getChatId().toString(),
                            update.getMessage().getFrom().getUserName(),
                            update.getMessage().getFrom().getFirstName());

                break;
            case "Unsub":
                DBController.deleteSubs(Update.getMessage().getChatId().toString());
                break;
            case "Back":
                message.setReplyMarkup(new Keyboard().StartKeyboard());
                message.setText("Main Menu");
                execute(message);
                break;
            default:
                message.setText("No such a command.\nUse  /start to see more options");
                try {
                    execute(message);
                } catch (TelegramApiException e){
                    e.printStackTrace(); }
        }


    }

    public  void subscription(String id){
        SendMessage subException = new SendMessage();
        subException.setChatId(id);
        subException.setText("Thank you for the subscription!");
        try {
            execute(subException);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public  void unsub(String id){
        SendMessage subException = new SendMessage();
        subException.setChatId(id);
        subException.setText( "You are unsubscribed now!");
        try {
            execute(subException);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    public  void subDeleteExceptionExecution(String id){
        SendMessage subException = new SendMessage();
        subException.setChatId(id);
        subException.setText("You have already unsubscribed");
        try {
            execute(subException);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    public void subExceptionExecution(String id){
        SendMessage subException = new SendMessage();
        subException.setChatId(id);
        subException.setText("You have already subscribed");
        try {
            execute(subException);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void mailingExecution(){
        if(Mailing.mailingNews.size()!=0 ){
            for (int i=0;i<Mailing.mailingNews.size();i++){
                for (int j=0;j<Mailing.subs.size();j++) {
                    mailingMessage = new SendMessage();
                    mailingMessage.setChatId(Mailing.subs.get(j));
                    mailingMessage.setText(
                            Mailing.mailingNews.get(i).getPubDate() + "\n" +
                                    Mailing.mailingNews.get(i).getTitle() + "\n" +
                                    Mailing.mailingNews.get(i).getLink()
                    );
                    try {
                        execute(mailingMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
            Mailing.mailingNews.clear();
        }


    }
    private void newsOutput(SendMessage mes, int index){
        try {
            new RssParser(News.cnbcLinks[index]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 1; i< News.newsList.size(); i++){
             DBController.save(News.newsList.get(i),index);
             mes.setText(News.newsList.get(i).getPubDate()+"\n"+
                    News.newsList.get(i).getTitle()+"\n"+
                    //RssParser.news.get(i).getDescription()+"\n"+
                    News.newsList.get(i).getLink()
             );

            try {
                execute(mes);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
        News.newsList.clear();

    }


}
