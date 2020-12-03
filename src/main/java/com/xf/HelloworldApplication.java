package com.xf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;


//主入口
@SpringBootApplication
public class HelloworldApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
	    SpringApplication.run(HelloworldApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);

		//解决Shiro登录后url携带jsessionid问题
		// This will set to use COOKIE only
		servletContext.setSessionTrackingModes(
				Collections.singleton(SessionTrackingMode.COOKIE)
		);
		// This will prevent any JS on the page from accessing the
		// cookie - it will only be used/accessed by the HTTP transport
		// mechanism in use
		SessionCookieConfig sessionCookieConfig =
				servletContext.getSessionCookieConfig();
		sessionCookieConfig.setHttpOnly(true);
	}
}
