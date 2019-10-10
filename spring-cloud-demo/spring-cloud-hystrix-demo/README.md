mvn spring-boot:run

http://127.0.0.1:8080/

cd /var; python -m SimpleHTTPServer 9091

## http://127.0.0.1:8080/actuator
{
	"_links" : {
		"self" : {
			"href" : "http://127.0.0.1:8080/actuator",
			"templated" : false
		},
		"archaius" : {
			"href" : "http://127.0.0.1:8080/actuator/archaius",
			"templated" : false
		},
		"auditevents" : {
			"href" : "http://127.0.0.1:8080/actuator/auditevents",
			"templated" : false
		},
		"beans" : {
			"href" : "http://127.0.0.1:8080/actuator/beans",
			"templated" : false
		},
		"caches" : {
			"href" : "http://127.0.0.1:8080/actuator/caches",
			"templated" : false
		},
		"caches-cache" : {
			"href" : "http://127.0.0.1:8080/actuator/caches/{cache}",
			"templated" : true
		},
		"health" : {
			"href" : "http://127.0.0.1:8080/actuator/health",
			"templated" : false
		},
		"health-component" : {
			"href" : "http://127.0.0.1:8080/actuator/health/{component}",
			"templated" : true
		},
		"health-component-instance" : {
			"href" : "http://127.0.0.1:8080/actuator/health/{component}/{instance}",
			"templated" : true
		},
		"conditions" : {
			"href" : "http://127.0.0.1:8080/actuator/conditions",
			"templated" : false
		},
		"configprops" : {
			"href" : "http://127.0.0.1:8080/actuator/configprops",
			"templated" : false
		},
		"env-toMatch" : {
			"href" : "http://127.0.0.1:8080/actuator/env/{toMatch}",
			"templated" : true
		},
		"env" : {
			"href" : "http://127.0.0.1:8080/actuator/env",
			"templated" : false
		},
		"info" : {
			"href" : "http://127.0.0.1:8080/actuator/info",
			"templated" : false
		},
		"loggers-name" : {
			"href" : "http://127.0.0.1:8080/actuator/loggers/{name}",
			"templated" : true
		},
		"loggers" : {
			"href" : "http://127.0.0.1:8080/actuator/loggers",
			"templated" : false
		},
		"heapdump" : {
			"href" : "http://127.0.0.1:8080/actuator/heapdump",
			"templated" : false
		},
		"threaddump" : {
			"href" : "http://127.0.0.1:8080/actuator/threaddump",
			"templated" : false
		},
		"metrics-requiredMetricName" : {
			"href" : "http://127.0.0.1:8080/actuator/metrics/{requiredMetricName}",
			"templated" : true
		},
		"metrics" : {
			"href" : "http://127.0.0.1:8080/actuator/metrics",
			"templated" : false
		},
		"scheduledtasks" : {
			"href" : "http://127.0.0.1:8080/actuator/scheduledtasks",
			"templated" : false
		},
		"httptrace" : {
			"href" : "http://127.0.0.1:8080/actuator/httptrace",
			"templated" : false
		},
		"mappings" : {
			"href" : "http://127.0.0.1:8080/actuator/mappings",
			"templated" : false
		},
		"refresh" : {
			"href" : "http://127.0.0.1:8080/actuator/refresh",
			"templated" : false
		},
		"features" : {
			"href" : "http://127.0.0.1:8080/actuator/features",
			"templated" : false
		},
		"hystrix.stream" : {
			"href" : "http://127.0.0.1:8080/actuator/hystrix.stream",
			"templated" : false
		}
	}
}
