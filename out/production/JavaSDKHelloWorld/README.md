This is a very simple example of using the Instana Java SDK.

The Instana Java SDK is available here: https://github.com/instana/instana-java-sdk
The Java SDK docs are here: https://www.instana.com/docs/tracing/java-trace-sdk/

Please note that the Java SDK jar is included here for convenience.

The example shows how to create:
an ENTRY span to simulate a new trace
an EXIT span to simulate an exit call to a remote system
optional naming tags for type, service, endpoint and call name.

NB: the docs troubleshooting section is here: https://www.instana.com/docs/tracing/java-trace-sdk/#troubleshooting. The first two bullet points are easy to overlook. Ensure a) that the Java SDK jar file is on the application classpath and b) that the agent's confifguratio.yaml file specifies the Java package names to scan for custom instrumentation. 

The sample included with the Instana Java SDK (above) includes a more advanced use case of custom intrumentation to correlate calls between a socket client/server. 
