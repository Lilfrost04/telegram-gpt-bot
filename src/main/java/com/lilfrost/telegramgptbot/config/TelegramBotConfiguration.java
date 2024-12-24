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

    @Value("${bot.token}")
    String botToken;
    @Value("${bot.name}")
    String botName;
    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(botToken);
    };




}
