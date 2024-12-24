package com.lilfrost.telegramgptbot.config;

import com.lilfrost.telegramgptbot.service.TelegramBot;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@Data
@PropertySource("/application.properties")
public class TelegramBotConfiguration {


    @Bean
    public TelegramBot telegramBot(@Value("${bot.token}") String botToken, TelegramBotsApi telegramBotsApi) {
        DefaultBotOptions botOptions = new DefaultBotOptions();
        TelegramBot bot =  new TelegramBot(botOptions, botToken);

        try {
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        return bot;
    }

    @Bean
    public TelegramBotsApi TelegramBotsApi() {
        try {
            return new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
