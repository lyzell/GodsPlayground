package com.godsplayground;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Random;

import static spark.Spark.*;

public class GodsPlaygroundServer {

    static final Random randomGenerator = new Random();

    static final String COOKIE_IDENTIFIER = "user-identifier";

    static int x = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!!");

        get("/longpoll", (req, res) -> {
            System.out.println("==> long poll start. " + req.cookie(COOKIE_IDENTIFIER));
            Thread.sleep(500);
            //Active waiting, change to use lockers
            while(true) {
            	if (x==12) break;
            	Thread.sleep(100);
            }
            
            System.out.println("==> long poll end. " + req.cookie(COOKIE_IDENTIFIER));
            x++;
            return "" + x;
        });


        get("/hello2", (req, res) -> {
        	x++;
            System.out.println("==> Hello world2. " + req.cookie(COOKIE_IDENTIFIER)+" x:"+x);
            return "Hello World2";
        });

        get("/index.html", (req, res) -> {
            final byte[] bytes = FileUtils.readFileToByteArray(new File("/Users/piotrmilos/git/GodsPlayground/GodsPlayground/src/htmlCode/index2.html"));

            res.cookie(COOKIE_IDENTIFIER, "" + randomGenerator.nextInt(100));
            return bytes;
        });
    }
}
