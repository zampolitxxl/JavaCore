FROM openjdk:latest
WORKDIR /usr/src/app

COPY ./src/main/java .
RUN javac -sourcepath . -d out ./entryPointPackage/Task1EntryPoint.java
WORKDIR /usr/src/app/out
CMD java -classpath . entryPointPackage.Task1EntryPoint

