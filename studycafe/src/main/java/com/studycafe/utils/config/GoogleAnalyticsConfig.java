package com.studycafe.utils.config;

import org.springframework.context.annotation.Configuration;

import lombok.Value;

@Configuration
public class GoogleAnalyticsConfig {
//	@Value("${spring.security.oauth2.client.registration.google.client-id}")
//	private String clientId;
//	@Value("${spring.security.oauth2.client.registration.google.client-secret}")
//	private String clientSecret;
//
//	@Bean
//	public Analytics getAnalytics() throws IOException, GeneralSecurityException {
//		NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientId,
//				clientSecret, Collections.singleton(AnalyticsScopes.ANALYTICS_READONLY)).build();
//		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
//		return new Analytics.Builder(httpTransport, jsonFactory, credential).setApplicationName("Your Application Name")
//				.build();
//	}
}

//@Service
//public class AnalyticsService {
//	private final Analytics analytics;
//
//	public AnalyticsService(Analytics analytics) {
//		this.analytics = analytics;
//	}
//
//	public List getProfiles() throws IOException {
//		Accounts accounts = analytics.management().accounts().list().execute();
//		List accountList = accounts.getItems();
//		List profiles = new ArrayList<>();
//		for (Account account : accountList) {
//			String accountId = account.getId();
//			Webproperties webproperties = analytics.management().webproperties().list(accountId).execute();
//			List webpropertyList = webproperties.getItems();
//			for (Webproperty webproperty : webpropertyList) {
//				String webpropertyId = webproperty.getId();
//				Profiles profiles = analytics.management().profiles().list(accountId, webpropertyId).execute();
//				List profileList = profiles.getItems();
//				profiles.addAll(profileList);
//			}
//		}
//		return profiles;
//	}
//}