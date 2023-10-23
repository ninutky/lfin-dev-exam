FROM eclipse-temurin:17.0.8_7-jdk-jammy AS java-build

# 환경변수 설정
ARG APP_DIR=/app
ARG SRC_DIR=${APP_DIR}/src
ARG NEW_RELIC_DIR=${APP_DIR}/newrelic

# 소스 복사 및 빌드
COPY . ${SRC_DIR}

# newrelic 설치
COPY ./newrelic.yml ${NEW_RELIC_DIR}/newrelic.yml
ADD https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic.jar ${NEW_RELIC_DIR}/newrelic.jar

WORKDIR ${SRC_DIR}
RUN rm -rf build target
RUN ./gradlew clean bootJar --no-daemon && cp build/libs/*.jar /app

# 소스코드 삭제
RUN rm -rf ${SRC_DIR}

FROM eclipse-temurin:17.0.8_7-jre-jammy

# 환경변수 설정
ARG APP_DIR=/app
ARG SRC_DIR=${APP_DIR}/src
ARG NEW_RELIC_DIR=${APP_DIR}/newrelic

# Healthcheck를 위해 curl 명령어 추가
# timezone 설정
RUN apt update \
    && apt install -y --no-install-recommends curl \
    && apt install -yq tzdata \
    && ln -fs /usr/share/zoneinfo/Asia/Seoul /etc/localtime \
    && dpkg-reconfigure -f noninteractive tzdata \
	&& rm -rf /var/lib/apt/lists/*

ENV TZ Asia/Seoul

# Download and add the RDS certificates to the trusted certs
RUN wget -O /usr/local/share/ca-certificates/rds-ca-2019-root.crt https://s3.amazonaws.com/rds-downloads/rds-ca-2019-root.pem \
    && update-ca-certificates

# copy app directory
COPY --from=java-build ${APP_DIR} ${APP_DIR}

# 서비스 실행을 위한 유저 생성 (ubuntu)
RUN useradd -ms /bin/bash ubuntu

# /app 디렉토리의 소유권 및 권한 설정
RUN chown -R ubuntu:ubuntu ${APP_DIR}

# Set user ubuntu (root 유저 사용 금지)
USER ubuntu

# Listen Port
EXPOSE 8099

#ENTRYPOINT에서는 ARG를 곧바로 사용할 수 없음.
#ARG -> ENV -> ENTRYPOINT
ENV APP_DIR=${APP_DIR}

ARG BUILD_JAVA_OPTS=""
ENV BUILD_JAVA_OPTS=$BUILD_JAVA_OPTS

# New Relic 로그는 파일로 남기지 않고 STDOUT으로 함
ENV NEW_RELIC_LOG_FILE_NAME STDOUT
ENV NEW_RELIC_DIR=${NEW_RELIC_DIR}

# 실행
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} ${BUILD_JAVA_OPTS} -Djsse.enableSNIExtension=false -javaagent:${NEW_RELIC_DIR}/newrelic.jar -jar ${APP_DIR}/*.jar CROWD-DASHBOARD-API ${0} ${@}"]
