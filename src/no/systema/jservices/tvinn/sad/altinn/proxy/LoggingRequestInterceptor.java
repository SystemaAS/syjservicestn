package no.systema.jservices.tvinn.sad.altinn.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {
	private static Logger logger = Logger.getLogger(LoggingRequestInterceptor.class.getName());

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		traceRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		traceResponse(response);
		return response;
	}

	private void traceRequest(HttpRequest request, byte[] body) throws IOException {
		logger.info("===========================request begin================================================");
		logger.info("URI         : " + request.getURI());
		logger.info("Method      : " + request.getMethod());
		logger.info("Headers     : " + request.getHeaders());
		logger.info("Request body: " + new String(body, "UTF-8"));
		logger.info("==========================request end================================================");
	}

	private void traceResponse(ClientHttpResponse response) throws IOException {
		StringBuilder inputStringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
		String line = bufferedReader.readLine();
		while (line != null) {
			inputStringBuilder.append(line);
			inputStringBuilder.append('\n');
			line = bufferedReader.readLine();
		}
		logger.info("============================response begin==========================================");
		logger.info("Status code  : " + response.getStatusCode());
		logger.info("Status text  : " + response.getStatusText());
		logger.info("Headers      : " + response.getHeaders());
		logger.debug("Response body: " + inputStringBuilder.toString());
		logger.debug("=======================response end=================================================");
	}

}
