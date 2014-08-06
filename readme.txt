My project contains 3 java files
Authentication.java
Client.java
Server.java
and 1 folder serverData with account.dat and message.dat in it.
account.dat contains default admin account.
message.dat contains message "Hello World." stored by admin.

In Windows Command Prompt:
Compile the source:
javac -d destDir Authentication.java Server.java Client.java 
where destDir is the destination directory to put the class files in.

Run the project:
Start the RMI registry:
start rmiregistry
Start the server:
start java -classpath classDir -Djava.rmi.server.codebase=file:classDir/ myrmi.server.Server
where classDir is the root directory of the class file tree(same as destDir).
Run the client:
java  -classpath classDir myrmi.client.Client
where classDir is the root directory of the class file tree (same as destDir).

Reference:http://docs.oracle.com/javase/6/docs/technotes/guides/rmi/hello/hello-world.html