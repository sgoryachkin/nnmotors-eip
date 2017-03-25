package ru.nnmotors.eip.web.itest;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;

public class LoginITCase {

	public static final String LOGIN_FORM = "action=\"login\" method=\"post\"";

	@Test
	public void myTest() {

		String loginPage = new RestTemplate().getForObject("http://localhost:8080/jetty-dev/login", String.class);

		assertTrue(loginPage.contains(LOGIN_FORM));

	}

	@Test
	public void myTest2() throws FailingHttpStatusCodeException, MalformedURLException, IOException {

		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getCookieManager().setCookiesEnabled(true);
		login(webClient);
		webClient.close();

	}

	private void login(WebClient webClient) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
/*
		final HtmlPage loginPage = webClient.getPage("http://localhost:8080/jetty-dev/login");
		final HtmlSubmitInput submit = loginPage.getFirstByXPath("//input[@type='submit']");
		final HtmlTextInput username = loginPage.getFirstByXPath("//input[@name='username']");
		final HtmlTextInput password = loginPage.getFirstByXPath("//input[@name='password']");
		username.setValueAttribute("admin");
		password.setValueAttribute("password");
		webClient.waitForBackgroundJavaScript(3000);
		HtmlPage newsList = submit.click();

		String htmlBody = newsList.getWebResponse().getContentAsString();
		System.out.println(newsList.asText());
		System.out.println(htmlBody);
		System.out.println("Base Uri 1 : " + loginPage);
		System.out.println("Base Uri 2 : " + newsList);*/

	}

}
