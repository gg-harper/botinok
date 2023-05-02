package org.bakaev.develop.telegram.bot.utils;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {
    public static Set<String> classes = Set.of("org.bakaev.develop.telegram.bot.Bot");
    public static boolean level = true;
    public static void initProperties() throws IOException {
        Properties  properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/application.properties"));
        classes = Arrays.stream(properties.getProperty("logger.classes").split(",")).collect(Collectors.toSet());
        level = Boolean.parseBoolean(properties.getProperty("logger.extended"));
    }

}
