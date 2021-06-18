package com.cleafy.elasticsearch6.plugins.http.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.rest.RestRequest;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class LoggerUtils {

    public static void logRequest(final RestRequest request, Class<?> klass) {
        String addr = getAddress(request).getAddress().getHostAddress();
        String t = "Authorization:{}, type: {}, Host:{}, Path:{}, {}:{}, Request-IP:{}, " +
                "Client-IP:{}, X-Client-IP{}";
        Logger log = LogManager.getLogger(klass);

        log.info(t,
                request.header("Authorization"),
                request.method(),
                request.header("Host"),
                request.path(),
                addr,
                request.header("X-Client-IP"),
                request.header("Client-IP"));
    }

    public static void logUnAuthorizedRequest(final RestRequest request, Class<?> klass) {
        String addr = getAddress(request).getAddress().getHostAddress();
        String t = "UNAUTHORIZED type:{}, address:{}, path:{}, request:{}, content:{}";
        Logger log = LogManager.getLogger(klass);

        log.error(t,
                request.method(), addr, request.path(), request.params(),
                request.content().utf8ToString());
    }

    private static InetSocketAddress getAddress(RestRequest request) {
        return request.getHttpChannel().getRemoteAddress();
    }
    
    public static void log (String msg) {
    	 Logger log = LogManager.getLogger(LoggerUtils.class);
    	 log.error(msg);
    	 log.error(msg);
    	 log.error(msg);
    }
}
