package com.sreejesh.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
				
		

		from("timer://myTimer?repeatCount=1")
		.setHeader("startTime", simple("${date:now:yyyyMMdd-HH:mm:ss.SSS}"))
		.loop(1000)
		.setHeader("Header-CamelLoopIndex", simple("${exchangeProperty.CamelLoopIndex}"))
		.setBody(simple("Body-CamelLoopIndex:${exchangeProperty.CamelLoopIndex}"))
		.log("***** STEP-10:${body.class.name}")
		.log("***** STEP-20:${body}")
		.removeHeaders("kafka*")
		.to("kafka:test-topic?brokers=localhost:9092")
		.end()
		;
		
		
		from("kafka:test-topic?brokers=localhost:9092&maxPollRecords=1")
		.log("***** STEP-100:${body.class.name}")
		.log("***** STEP-110:${body}")
		.removeHeaders("kafka*")
		.to("kafka:test-topic-2?brokers=localhost:9092")
		;
		
		
		from("kafka:test-topic-2?brokers=localhost:9092&maxPollRecords=1")
		.log("***** STEP-200:${body.class.name}")
		.log("***** STEP-210:${body}")
		.log("***** STEP-300:\n\nbody:${body}\nstartTime:${header.startTime}\nendTime:${date:now:yyyyMMdd-HH:mm:ss.SSS}")
		;
		
	

	}

}
