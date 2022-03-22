package application;

import messageHandler.MessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    private static final String BOT_NAME="FinancialHotNews_bot";
    private static final String BOT_TOKEN= "1617955211:AAFgO0U7ZUdP-UJjYrayraRmLuyvd0Crg4w";




    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){

            MessageHandler messageHandler = new MessageHandler();
            try {
                messageHandler.messageSender(update);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

}
