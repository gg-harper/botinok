package org.bakaev.develop.telegram.bot;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class Bot extends TelegramLongPollingBot {
	final private String BOT_TOKEN = "6041836637:AAFKIZ_vcEXQi7mT4dG-J4oXsG3ByGbuu8o";
	final private String BOT_NAME = "r_solo_bot";

	Logic logic;

	Bot() {
		logic = new Logic();
	}
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
		if(update.hasMessage() && update.getMessage().hasText()) {
			Message inMessage = update.getMessage();
			String chatId = inMessage.getChatId().toString();
			SendMessage outMessage = new SendMessage();
			String response = parseMessage(inMessage.getText());
			outMessage.setChatId(chatId);
			outMessage.setText(response);
			log.info("response is ", response);

			try {
				execute(outMessage);
			} catch (TelegramApiException tae) {
				throw new RuntimeException(tae);
			}
		}
	}
	public String parseMessage(String msg) {
		String response = "";
		switch (msg) {
			case "/start":
				response = "hello!";
				break;
			default:
		}
		return response;
	}
}
