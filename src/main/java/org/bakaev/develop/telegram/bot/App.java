package org.bakaev.develop.telegram.bot;
import org.bakaev.develop.telegram.bot.utils.Utils;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
	static Bot bot = new Bot();
    public static void main( String[] args )
    {
	    try {
			Utils.initProperties();
			bot.initKeyboard();
	    	TelegramBotsApi telegramBotApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotApi.registerBot(bot);
	    }
	    catch(TelegramApiException tae) {
		    tae.printStackTrace();
	    } catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
