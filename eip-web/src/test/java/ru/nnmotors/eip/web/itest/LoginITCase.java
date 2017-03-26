package ru.nnmotors.eip.web.itest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class LoginITCase {

	public static final String LOGIN_FORM = "action=\"login\" method=\"post\"";

	@Test
	public void simpleLogin() {
		String loginPage = new RestTemplate().getForObject("http://localhost:8080/jetty-dev/login", String.class);
		assertTrue(loginPage.contains(LOGIN_FORM));
	}

/*	@Test
	public void myTest2() throws FailingHttpStatusCodeException, MalformedURLException, IOException {

		final WebClient webClient = new WebClient(BrowserVersion.CHROME);

		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

		webClient.getCookieManager().setCookiesEnabled(true);

		login(webClient);

		webClient.close();

	}

	private void login(WebClient webClient) throws FailingHttpStatusCodeException, MalformedURLException, IOException {

		WebRequest webRequest = new WebRequest(new URL("http://localhost:8080/jetty-dev/login"));
		webRequest.setCharset(Charset.forName("UTF-8"));
		final HtmlPage loginPage = webClient.getPage(webRequest);
		System.out.println(loginPage.asXml());

		// final HtmlTextInput username = loginPage.getFirstByXPath("//body");
		final HtmlTextInput username = loginPage.getFirstByXPath("//input[@name='username']");
		final HtmlTextInput password = loginPage.getFirstByXPath("//input[@name='password']");
		final HtmlSubmitInput submit = loginPage.getFirstByXPath("//input[@type='submit']");
		username.click();
		username.type("admin");
		password.click();
		password.type("password");
		webClient.waitForBackgroundJavaScript(3000);
		HtmlPage newsList = submit.click();

		String htmlBody = newsList.getWebResponse().getContentAsString();
		System.out.println(newsList.asText());
		System.out.println(htmlBody);
		System.out.println("Base Uri 1 : " + loginPage);
		System.out.println("Base Uri 2 : " + newsList);

	}
*/
}
