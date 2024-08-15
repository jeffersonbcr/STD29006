FROM gradle:jdk17-alpine

COPY . /device

WORKDIR /device

RUN gradle installDist

ENV DEVICE_TYPE_ENV=$DEVICE_TYPE

ENTRYPOINT ["app/build/install/app/bin/app"]