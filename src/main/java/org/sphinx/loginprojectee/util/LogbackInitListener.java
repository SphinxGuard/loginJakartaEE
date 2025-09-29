package org.sphinx.loginprojectee.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.util.logging.LogManager;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class LogbackInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Reset and install JUL-to-SLF4J bridge
            LogManager.getLogManager().reset();
            SLF4JBridgeHandler.install();

            // Initialize Logback normally
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            context.reset();
            new ContextInitializer(context).autoConfig();
            System.out.println("Logback initialized successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
