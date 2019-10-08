package com.siteminder.transaction.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

//import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.siteminder.transaction.model.TransactionEntity;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TransactionController {

	@SuppressWarnings("deprecation")
	@PostMapping("/sendmail")
	@Transactional
	public ResponseEntity<String> mailTransactions(@RequestBody @Valid TransactionEntity newTransaction)
			throws IOException {

		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = null;
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

		// the 1st service reachability
		post = new HttpPost(System.getenv("SENDGRID_URL"));
		post.setHeader("authorization", "Bearer " + System.getenv("SENDGRID_API_KEY"));
		post.setHeader("Content-type", "application/json");
		String body = "{\"personalizations\":[{\"to\":[{\"email\":\"" + newTransaction.getTo()
				+ "\",\"name\":\"Anindya M\"}]," + "\"subject\":\"" + newTransaction.getSubject()
				+ "\"}],\"content\": [{\"type\": \"text/plain\"," + " \"value\":\"" + newTransaction.getContent()
				+ "\"}],\"from\":{\"email\":\"" + newTransaction.getFrom() + "\"}}";
		System.out.println("body:::::::" + body);
		StringEntity stringEntity = new StringEntity(body);

		post.setEntity(stringEntity);

		CloseableHttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + System.getenv("SENDGRID_URL"));
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		client.close();

		// if the 1st service response is not 202 ie Accepted, that means the
		// service is down or not accepting the client request.
		// So, the handle will go to the 2nd service as a failover.
		if (response.getStatusLine().getStatusCode() != 202) {
			// try 2nd service gateway
			DefaultHttpClient client2 = new DefaultHttpClient();
			client2.getCredentialsProvider().setCredentials(new AuthScope("api.mailgun.net", 443),
					new UsernamePasswordCredentials("api", System.getenv("MAILGUN_API_KEY")));
			post = new HttpPost(System.getenv("MAILGUN_URL"));

			urlParameters.add(new BasicNameValuePair("from", newTransaction.getFrom()));
			urlParameters.add(new BasicNameValuePair("to", newTransaction.getTo()));
			urlParameters.add(new BasicNameValuePair("subject", newTransaction.getSubject()));
			urlParameters.add(new BasicNameValuePair("text", newTransaction.getContent()));

			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			response = client2.execute(post);
			System.out.println("\nSending 'POST' request to URL : " + System.getenv("MAILGUN_URL"));
			System.out.println("Post parameters : " + post.getEntity());
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			client2.close();

		}

		return ResponseEntity.status(response.getStatusLine().getStatusCode()).body(null);
	}

}
