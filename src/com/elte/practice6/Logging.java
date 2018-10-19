package com.elte.practice6;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Logging {

    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger("practice6");
        Handler handler = new FileHandler();
        logger.addHandler(handler);

        logger.severe("Severe error!");
    }
}
