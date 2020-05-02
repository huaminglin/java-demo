# How RestTemplate construct request URL

## Run the application and check the log

mvn exec:java

18:24:29.215 [huaminglin.demo.encoding.client.EncodingClientRestTemplateDemo.main()] DEBUG org.springframework.web.client.RestTemplate - HTTP GET http://127.0.0.1:8080/path/a+b%20c?name=a+b%20c&p2=a+b%20c
18:24:29.224 [huaminglin.demo.encoding.client.EncodingClientRestTemplateDemo.main()] DEBUG org.springframework.web.client.RestTemplate - Accept=[text/plain, application/json, application/*+json, */*]
18:24:29.243 [huaminglin.demo.encoding.client.EncodingClientRestTemplateDemo.main()] DEBUG org.springframework.web.client.RestTemplate - Response 200 OK
18:24:29.244 [huaminglin.demo.encoding.client.EncodingClientRestTemplateDemo.main()] DEBUG org.springframework.web.client.RestTemplate - Reading to [java.lang.String] as "text/plain;charset=UTF-8"
hello: a+b c

Note:
"http://127.0.0.1:8080/path/a+b%20c?name=a+b%20c&p2=a+b%20c" is the final request URL;
"+" is not encoded even by URI variable.

## org.springframework.web.client.RestTemplate and UriTemplateHandler

	private UriTemplateHandler uriTemplateHandler;

	public <T> T execute(String url, HttpMethod method, @Nullable RequestCallback requestCallback,
			@Nullable ResponseExtractor<T> responseExtractor, Map<String, ?> uriVariables)
			throws RestClientException {

		URI expanded = getUriTemplateHandler().expand(url, uriVariables);
		return doExecute(expanded, method, requestCallback, responseExtractor);
	}


	private static DefaultUriBuilderFactory initUriTemplateHandler() {
		DefaultUriBuilderFactory uriFactory = new DefaultUriBuilderFactory();
		uriFactory.setEncodingMode(EncodingMode.URI_COMPONENT);  // for backwards compatibility..
		return uriFactory;
	}

org.springframework.web.util.DefaultUriBuilderFactory.DefaultUriBuilder.build(java.util.Map<java.lang.String,?>)
		@Override
		public URI build(Map<String, ?> uriVars) {
			if (!defaultUriVariables.isEmpty()) {
				Map<String, Object> map = new HashMap<>();
				map.putAll(defaultUriVariables);
				map.putAll(uriVars);
				uriVars = map;
			}
			if (encodingMode.equals(EncodingMode.VALUES_ONLY)) {
				uriVars = UriUtils.encodeUriVariables(uriVars);
			}
			UriComponents uric = this.uriComponentsBuilder.build().expand(uriVars);
			return createUri(uric);
		}
		@Override
		public URI build(Object... uriVars) {
			if (ObjectUtils.isEmpty(uriVars) && !defaultUriVariables.isEmpty()) {
				return build(Collections.emptyMap());
			}
			if (encodingMode.equals(EncodingMode.VALUES_ONLY)) {
				uriVars = UriUtils.encodeUriVariables(uriVars);
			}
			UriComponents uric = this.uriComponentsBuilder.build().expand(uriVars);
			return createUri(uric);
		}

Note: uriFactory.setEncodingMode(EncodingMode.URI_COMPONENT);  // for backwards compatibility..

## Extract DefaultUriBuilderFactory.EncodingMode.java to understand enum EncodingMode(TEMPLATE_AND_VALUES VALUES_ONLY URI_COMPONENT NONE) better

## Conclusion: RestTemplate doesn't support to encode reserved characters.
