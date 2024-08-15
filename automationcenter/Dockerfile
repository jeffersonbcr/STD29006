FROM gradle:jdk17-alpine

COPY . /automationcenter

WORKDIR /automationcenter

RUN apk --no-cache add curl

RUN gradle installDist

CMD ["build/install/automationcenter/bin/automationcenter"]