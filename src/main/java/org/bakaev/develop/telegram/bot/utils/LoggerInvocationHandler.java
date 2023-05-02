package org.bakaev.develop.telegram.bot.utils;

import org.slf4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Set;

public class LoggerInvocationHandler implements InvocationHandler {
    Logger logger;

    Set<String> classes = Utils.classes;
    //String level = Utils.level;
    boolean level = Utils.level;
    public LoggerInvocationHandler(Logger log) {
        logger = log;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        StringBuilder sb = new StringBuilder();
        for (Object o: args
             ) {
            sb.append(o);
        }
        String message = sb.toString();
        if (classes.contains(Thread.currentThread().getStackTrace()[3].getClassName()) && level){
           method.invoke(logger, args);
        }
        else {
            logger.info(args[0].toString().replaceAll("\\{}", ""));
        }

        return null;
    }
}
