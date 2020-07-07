package com.feng;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {
    static Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("XiaoMing");
        log.info(person.hello());
        try {
            new Person();
        } catch (Exception e) {
            log.error("Exception", e);
        }
   }
}
