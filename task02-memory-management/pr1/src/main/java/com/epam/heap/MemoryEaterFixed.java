package com.epam.heap;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * User: Andrei_Pimenau
 * Date: 3/3/14
 */
public class MemoryEaterFixed {
    private static final Logger logger = Logger.getLogger(MemoryEaterFixed.class);
    private static final String ATTEMPT = "Attempt ";
    private static final String FREE_MEMORY = ". Free memory: ";

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        List v = new LinkedList();
        for (int j = 0; j < 200; j++) {
            byte b[] = new byte[1048576];
            v.add(b);
            Runtime rt = Runtime.getRuntime();
            StringBuilder sb = new StringBuilder(ATTEMPT).append(i++).append(FREE_MEMORY).append(rt.freeMemory());
            logger.info(sb.toString());
//            Thread.sleep(100);
        }
    }
}
