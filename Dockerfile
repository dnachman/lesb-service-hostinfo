FROM openjdk:8-jdk-alpine
VOLUME /tmp
ENV JAVA_OPTS=
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","$JAVA_OPTS", "-cp","app:app/lib/*","com.logicalenigma.springboot.servicehostinfo.ServiceHostinfoApplication"]