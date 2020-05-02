# How to encode '+' character with UriComponentsBuilder

## UriComponentsBuilder: boolean encodeTemplate

encodeTemplate can be set through UriComponentsBuilder: UriComponentsBuilder encode(Charset charset)
this.encodeTemplate = true; this.charset = charset;

Note: It seems it's the only way to set this.encodeTemplate to true;
and it's the only way to trigger encodeTemplate() on build().

## HierarchicalUriComponents: HierarchicalUriComponents encodeTemplate(Charset charset)

// Remember the charset to encode URI variables later..
		this.variableEncoder = value -> encodeUriComponent(value, charset, Type.URI);
Note: Type.URI is the key; this.variableEncoder will be used for expand() on URI variable.
URI { @Override public boolean isAllowed(int c) { return isUnreserved(c); } };

boolean isUnreserved(int c) {
			return (isAlpha(c) || isDigit(c) || '-' == c || '.' == c || '_' == c || '~' == c);
}

"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.web.util.HierarchicalUriComponents.encodeTemplate(HierarchicalUriComponents.java:256)
	  at org.springframework.web.util.UriComponentsBuilder.buildInternal(UriComponentsBuilder.java:406)
	  at org.springframework.web.util.UriComponentsBuilder.build(UriComponentsBuilder.java:391)
	  at huaminglin.demo.encoding.client.query.plus.EncodingClientQueryPlusDemo.buildWithTemplateEncoding(EncodingClientQueryPlusDemo.java:99)
	  at huaminglin.demo.encoding.client.query.plus.EncodingClientQueryPlusDemo.main(EncodingClientQueryPlusDemo.java:113)
Note: encodeTemplate() creates a new instance of HierarchicalUriComponents;
this.variableEncoder encodes value as Type.URI, which will also encode reserved keywords.

## org.springframework.web.util.HierarchicalUriComponents.EncodeState TEMPLATE_ENCODED

FULLY_ENCODED: URI vars expanded first and then each URI component encoded by quoting only illegal characters within that URI component.
TEMPLATE_ENCODED: URI template encoded first by quoting illegal characters only, and then URI vars encoded more strictly when expanded, by quoting both illegal chars and chars with reserved meaning.

## Conclusion: Only URI variable can be encoded more strictly

So use URI variable and encodeTemplate=true to encode '+' in the query string.

## expand() and encode()

1. If it is FULLY_ENCODED, there is nothing to expand.

2. variableEncoder is used to expand, if variableEncoder is not set, there is no encode for the expanded value.

3. this.variableEncoder is set only by encodeTemplate();
it means UriComponentsBuilder encode() is required to expand() with encoding.

