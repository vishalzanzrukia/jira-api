package com.plugin.jira.api.temp;

public class TestResource {
	public static void main(String[] args) {
		java.net.URL urlLogo = TestResource.class.getResource("/img/build_1230.PNG");
		System.out.println(urlLogo);
//		Image logo = Image.getInstance(urlLogo.getPath());
	}
}
