package applicationTests;

import application.Bot;
import org.junit.Assert;
import org.junit.Test;

public class BotTests {
    @Test
    public  void returnName(){
        Bot bot = new Bot();
        Assert.assertEquals("FinancialHotNews_bot",bot.getBotUsername() );
    }
}
