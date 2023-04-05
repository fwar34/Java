package com.feng;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class Main {
    static Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) throws IntrospectionException {
        Person person = new Person();
        person.setName("XiaoMing");
        log.info(person.hello());
        try {
            new Person();
        } catch (Exception e) {
            log.error("Exception", e);
        }

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
            System.out.println(descriptor.getName());
            System.out.println(" " + descriptor.getReadMethod());
            System.out.println(" " + descriptor.getWriteMethod());
        }
   }
}
