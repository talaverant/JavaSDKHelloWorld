package com.instana.sample;

import com.instana.sdk.annotation.Span;
import com.instana.sdk.annotation.Span.Type;
import com.instana.sdk.support.SpanSupport;
import java.util.concurrent.TimeUnit;

//Brief example of using the Instana Java SDK
//Use case: we have some custom Java code that processes some arbitrary batches of items
//Since we are using custom code (as opposed to Quartz, etc.), we cannot discover our services automatically
//But we can use the Java SDK to explicitly create traces/calls in our custom code thru method annotations
//Full docs: https://www.instana.com/docs/tracing/java-trace-sdk/ - plz read - there are other prereqs required
//
public class JavaSDKHelloWorld {
    public static void main(String[] args) {
        System.out.println("Starting");
        doBatch();
    }

   public static void doBatch() {

      //If this JVM is a long running daemon process, these sleeps are not required
      //sleep time to let the agent discover/instrument the JVM
      try {Thread.sleep(45000);} catch (InterruptedException ie) {}

      //Process a "batch" of 5 items. Each item processed will represent a new trace
      int i = 0; while (i < 5) {doItem(); i++;}

      //sleep time to ensure last batch of traces is sent by agent to the backend
      try {Thread.sleep(45000);} catch (InterruptedException ie) {}

   }

   //We are annotating the execution of this method as an ENTRY span; this creates a new trace
   //We are also optionally tagging its tech type, service, endpoint and call name
   @Span(type = Type.ENTRY, value = "Item Processor")
   public static void doItem() {
      SpanSupport.annotate(Type.ENTRY, "Item Processor", "tags.batch.job", "JoseBatchJob");
      SpanSupport.annotate(Type.ENTRY, "Item Processor", "tags.service", "JoseServiceName");
      SpanSupport.annotate(Type.ENTRY, "Item Processor", "tags.endpoint", "JoseEndpointName");
      SpanSupport.annotate(Type.ENTRY, "Item Processor", "tags.call.name", "JoseCallName");


      //simulate some processing time
   	  try { Thread.sleep((long)(Math.random() * 500));} catch (Exception e) {}

   	  //Make a simulated exit call to some other system
      doExit(); 
   }

   //We are annotating the execution of this method as an EXIT span
   //It will appear as an EXIT call in the trace started in the doItem() method above
   @Span(type = Type.EXIT, value = "ExitCall", capturedStackFrames = 5)
   public static void doExit() {
      SpanSupport.annotate(Type.EXIT, "ExitCall", "tags.call.name", "JoseExitCallName");
   	  //simulate some exit call time
   	  try { Thread.sleep((long)(Math.random() * 100));} catch (Exception e) {} 
   }     
}