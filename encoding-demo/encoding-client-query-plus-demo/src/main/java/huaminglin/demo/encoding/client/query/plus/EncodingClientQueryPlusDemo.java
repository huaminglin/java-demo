package huaminglin.demo.encoding.client.query.plus;


import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

public class EncodingClientQueryPlusDemo {

    private static void buildAndVerifyEncoded() {
        System.out.println("\nbuildAndVerifyEncoded():");
        try {
            UriComponentsBuilder.newInstance()
                    .scheme("http").host("127.0.0.1").port(8080)
                    .path("/junit-5?p1=a+b c")
                    .build(true);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();

//            Exception in thread "main" java.lang.IllegalArgumentException: Invalid character '?' for PATH in "/junit-5?p1=a+b c"
//            at org.springframework.web.util.HierarchicalUriComponents.verifyUriComponent(HierarchicalUriComponents.java:415)
//            at org.springframework.web.util.HierarchicalUriComponents.access$200(HierarchicalUriComponents.java:52)
//            at org.springframework.web.util.HierarchicalUriComponents$FullPathComponent.verify(HierarchicalUriComponents.java:882)
//            at org.springframework.web.util.HierarchicalUriComponents.verify(HierarchicalUriComponents.java:379)
//            at org.springframework.web.util.HierarchicalUriComponents.<init>(HierarchicalUriComponents.java:144)
//            at org.springframework.web.util.UriComponentsBuilder.buildInternal(UriComponentsBuilder.java:403)
//            at org.springframework.web.util.UriComponentsBuilder.build(UriComponentsBuilder.java:391)
//            at huaminglin.demo.encoding.client.query.plus.EncodingClientQueryPlusDemo.builderEncoder(EncodingClientQueryPlusDemo.java:13)
//            at huaminglin.demo.encoding.client.query.plus.EncodingClientQueryPlusDemo.main(EncodingClientQueryPlusDemo.java:24)
//            org.springframework.web.util.HierarchicalUriComponents#HierarchicalUriComponents(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.springframework.web.util.HierarchicalUriComponents.PathComponent, org.springframework.util.MultiValueMap<java.lang.String,java.lang.String>, boolean)
//                if (encoded) { verify(); }
        }
        try {
            UriComponentsBuilder.newInstance()
                    .scheme("http").host("127.0.0.1").port(8080)
                    .path("/junit-5")
                    .queryParam("p1", "a+b c")
                    .build(true);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();

//            java.lang.IllegalArgumentException: Invalid character ' ' for QUERY_PARAM in "a+b c"
//            at org.springframework.web.util.HierarchicalUriComponents.verifyUriComponent(HierarchicalUriComponents.java:415)
        }
        {
            String preEncodedUrl = "http://127.0.0.1:8080/junit+5?p1=a+b%20c&p2=a?b";
            boolean encoded = true; // Set to true, UriComponentsBuilder will verify the url is already encoded correctly.

            if (UriComponentsBuilder.fromHttpUrl(preEncodedUrl).build(encoded).toUriString().equals(preEncodedUrl)) {
                System.out.println("UriComponentsBuilder verifies that the url is already encoded correctly.");
                System.out.println("Note: \"+\" in the query string is a valid encoded url.");
            }
        }
    }

    private static void buildUrlWithoutEncoding() {
        System.out.println("\nbuildUrlWithoutEncoding():");
        UriComponents component = UriComponentsBuilder.newInstance()
                .scheme("http").host("127.0.0.1").port(8080)
                .path("/junit-5{p1}?p2=a+b c")
                .queryParam("p3", "{p1}")
                .uriVariables(Collections.singletonMap("p1", "a+b c"))
                .build(false);
        System.out.println(component.toUriString());
        // http://127.0.0.1:8080/junit-5a+b c?p2=a+b c?p3=a+b c
        System.out.println("Note: URI variable is expanded without encoding");
    }

    private static void buildWithFullEncoding() {
        System.out.println("\nbuildWithFullEncoding():");
        UriComponents components = UriComponentsBuilder.newInstance()
                .scheme("http").host("127.0.0.1").port(8080)
                .path("/junit-5{p1}?p2=a+b c")
                .queryParam("p3", "{p1}")
                .uriVariables(Collections.singletonMap("p1", "a+b c"))
                .build(false);
        System.out.println(components.toUriString());
        // http://127.0.0.1:8080/junit-5a+b c?p2=a+b c?p3=a+b c

        components = components.encode();
        System.out.println(components.toUriString());
        // http://127.0.0.1:8080/junit-5a+b%20c%3Fp2=a+b%20c?p3=a+b%20c
        System.out.println("Note: '+' is allowed in the encoded URL.");
        // Try to check org.springframework.web.util.HierarchicalUriComponents.EncodeState
        // But HierarchicalUriComponents is not public.
    }

    private static void buildWithTemplateEncodingAndImplicitExpand() {
        System.out.println("\nbuildWithTemplateEncodingAndImplicitExpand():");
        UriComponents component = UriComponentsBuilder.newInstance()
                .scheme("http").host("127.0.0.1").port(8080)
                .path("/junit-5{p1}?p2=a+b c")
                .queryParam("p3", "{p1}")
                .uriVariables(Collections.singletonMap("p1", "a+b c"))
                .encode()
                .build(false);
        System.out.println(component.toUriString());
        // http://127.0.0.1:8080/junit-5a%2Bb%20c%3Fp2=a+b%20c?p3=a%2Bb%20c
        System.out.println("Note: URI variable is expanded with encoding");
        System.out.println("Note: the \"+\" in the URI variable is encoded, but '+' outside URI variable is not encoded.");
    }

    private static void buildAndImplicitExpand() {
        System.out.println("\nbuildAndImplicitExpand():");
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http").host("127.0.0.1").port(8080)
                .path("/junit-5{p1}?p2=a+b c")
                .queryParam("p3", "{p1}")
                .build(Collections.singletonMap("p1", "a+b c"));
        System.out.println(uri);
        // http://127.0.0.1:8080/junit-5a%2Bb%20c%3Fp2=a+b%20c?p3=a%2Bb%20c
        System.out.println("Note: URI variable is expanded with encoding");
        System.out.println("Note: the \"+\" in the URI variable is encoded, but '+' outside URI variable is not encoded.");
    }

    private static void buildWithTemplateEncodingNoExapnd() {
        System.out.println("\nbuildWithTemplateEncodingNoExapnd():");
        UriComponents component = UriComponentsBuilder.newInstance()
                .scheme("http").host("127.0.0.1").port(8080)
                .path("/junit-5{p1}?p2=a+b c")
                .queryParam("p3", "{p1}")
                .encode()
                .build(false);
        System.out.println(component.toUriString());
        // http://127.0.0.1:8080/junit-5{p1}%3Fp2=a+b%20c?p3={p1}
        System.out.println("Note: URI variable is not encoded.");
    }

    private static void expandWithoutEncoding() {
        System.out.println("\nexpandWithoutEncoding():");
        UriComponents component = UriComponentsBuilder.newInstance()
                .scheme("http").host("127.0.0.1").port(8080)
                .path("/{p1}")
                .build(false);
        System.out.println(component.toUriString());
        // http://127.0.0.1:8080/{p1}

        System.out.println(component.expand(Collections.singletonMap("p1", "a+b c")));
        // http://127.0.0.1:8080/a+b c

        System.out.println(component.expand(Collections.singletonMap("p1", "{p2}")));
        // http://127.0.0.1:8080/{p2}

        System.out.println(component.expand(Collections.singletonMap("p1", "{p2}"))
                .expand(Collections.singletonMap("p2", "a+b c")));
        // http://127.0.0.1:8080/a+b c
    }

    private static void expandWithEncoding() {
        System.out.println("\nexpandWithEncoding():");
        UriComponents component = UriComponentsBuilder.newInstance()
                .scheme("http").host("127.0.0.1").port(8080)
                .path("/{p1}")
                .encode()
                .build(false);
        System.out.println(component.toUriString());
        // http://127.0.0.1:8080/{p1}

        System.out.println(component.expand(Collections.singletonMap("p1", "a+b c")));
        // http://127.0.0.1:8080/a%2Bb%20c

        System.out.println(component.expand(Collections.singletonMap("p1", "{p2}")));
        // http://127.0.0.1:8080/%7Bp2%7D
    }

    public static void main(String[] args) {
        buildAndVerifyEncoded();

        buildUrlWithoutEncoding();

        buildWithFullEncoding();

        buildWithTemplateEncodingAndImplicitExpand();

        buildAndImplicitExpand();

        buildWithTemplateEncodingNoExapnd();

        expandWithoutEncoding();

        expandWithEncoding();
    }

}
