package org.bakaev.develop.telegram.bot;
import org.bakaev.develop.telegram.bot.utils.LoggerInvocationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {
	private static final Logger log = LoggerFactory.getLogger(Bot.class);
	final private String BOT_TOKEN = "6041836637:AAFKIZ_vcEXQi7mT4dG-J4oXsG3ByGbuu8o";
	final private String BOT_NAME = "r_solo_bot";

	ReplyKeyboardMarkup replyKeyboardMarkup;

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
			User from = inMessage.getFrom();
			String chatId = inMessage.getChatId().toString();
			SendMessage outMessage = new SendMessage();
			String response = parseMessage(inMessage.getText(), from);
			outMessage.setChatId(chatId);
			outMessage.setText(response);
			outMessage.setReplyMarkup(replyKeyboardMarkup);
			LoggerInvocationHandler handler = new LoggerInvocationHandler(log);
			Logger logger = (Logger) Proxy.newProxyInstance(Bot.class.getClassLoader(), new Class[] {Logger.class}, handler);
			Exception e = new Exception("Test exception");
			logger.info("response is {} \n {}", response, e);
			try {
				execute(outMessage);
			} catch (TelegramApiException tae) {
				log.error("error: {}", tae);
			}
		}
	}
	public String parseMessage(String msg, User user) {
		String response = "";
		switch (msg) {
			case "/start":
				response = "hello!";
				break;
			case "/hello":
				response = "Hello, " + user.getUserName();
				break;
			case "Weather":
				response = "Weather is fine";
				break;
			default:
		}
		return response;
	}

	void initKeyboard() {
		replyKeyboardMarkup = new ReplyKeyboardMarkup();
		replyKeyboardMarkup.setResizeKeyboard(true);
		replyKeyboardMarkup.setOneTimeKeyboard(false);
		List<KeyboardRow> keyboard = new ArrayList<>();
		KeyboardRow keyboardRow = new KeyboardRow();
		keyboard.add(keyboardRow);
		keyboardRow.add(new KeyboardButton("Weather"));
		replyKeyboardMarkup.setKeyboard(keyboard);
	}
}
