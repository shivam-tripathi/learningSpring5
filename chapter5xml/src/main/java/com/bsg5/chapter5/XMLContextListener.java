package com.bsg5.chapter5;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

@WebListener
public class XMLContextListener implements ServletContextListener {

    private ApplicationContext buildXMLContext(ServletContext servletContext) {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setServletContext(servletContext);
        context.refresh();
        return context;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context = buildXMLContext(sce.getServletContext());
        sce.getServletContext().setAttribute("context", context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
