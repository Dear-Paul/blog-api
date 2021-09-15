
FROM openjdk:16
LABEL maintainer ="blogs.net"
ADD target/blog-0.0.1-SNAPSHOT.jar blog.jar
ENTRYPOINT ["java", "-jar", "blog.jar"]
