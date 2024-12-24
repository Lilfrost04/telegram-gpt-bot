package com.lilfrost.telegramgptbot.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {


    public TelegramBot(DefaultBotOptions options, String botToken) {
        super(options, botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            Long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();

            sendMessage(chatId, text);

        }

    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), textToSend);
        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "telegram-gpt-bot";
    }
}
