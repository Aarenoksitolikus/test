<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                             http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    
    <display-name>Cafe Order System</display-name>
    
    <!-- Кодировка -->
    <filter>
        <filter-name>CharacterEncodingFilter</filter-name>
        <filter-class>ru.itis.oris.cafe.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    
    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    
    <!-- Конфигурация сессии -->
    <session-config>
        <session-timeout>30</session-timeout>
        <cookie-config>
            <http-only>true</http-only>
        </cookie-config>
    </session-config>
    
    <!-- Страницы ошибок -->
    <error-page>
        <error-code>403</error-code>
        <location>/WEB-INF/jsp/error/403.jsp</location>
    </error-page>
    
    <error-page>
        <error-code>404</error-code>
        <location>/WEB-INF/jsp/error/404.jsp</location>
    </error-page>
    
    <error-page>
        <error-code>500</error-code>
        <location>/WEB-INF/jsp/error/500.jsp</location>
    </error-page>
    
    <!-- Welcome files -->
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
    
</web-app>