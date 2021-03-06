package org.springframework.security.oauth2.provider.code;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.security.oauth2.provider.BaseClientDetails;

public class TestSubdomainRedirectResolver
{
	private final TestSubdomainRedirectResolver resolver = new TestSubdomainRedirectResolver();
	private final BaseClientDetails client = new BaseClientDetails();

	{
		client.setAuthorizedGrantTypes(Collections.singleton("authorization_code"));
	}


	@Test
	public void testRedirectWatchdox() throws Exception 
	{
		Set<String> redirectUris = new HashSet<String>(Arrays.asList("watchdox.com"));
		client.setRegisteredRedirectUri(redirectUris);
		String requestedRedirect = "http://anywhere.watchdox.com/something";
		assertEquals(requestedRedirect, resolver.resolveRedirect(requestedRedirect, client));
	}

	@Test
	public void testRedirectBadWatchdox() throws Exception 
	{
		Set<String> redirectUris = new HashSet<String>(Arrays.asList("http.*(watchdox.com).*"));
		client.setRegisteredRedirectUri(redirectUris);
		String requestedRedirect = "http://anywhere.google.com/something";
		assertEquals(requestedRedirect, resolver.resolveRedirect(requestedRedirect, client));
	}
}
