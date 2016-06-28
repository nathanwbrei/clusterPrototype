package edu.bgce.cse.clusterprototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;


public class MessageSender {

    private static Logger logger = LoggerFactory.getLogger(MessageSender.class);

    public static void main(String[] args) {

        logger.debug("debug-level messsage from bgce");
        logger.info("info-level message from bgce");
        logger.error("error-level message from bgce");
    }

}
