FROM hseeberger/scala-sbt:8u222_1.3.5_2.13.1

ENV UI_TYPE=full

RUN apt-get update && \
    apt-get install -y sbt libxrender1 libxtst6 libxi6

ADD . /malefiz
WORKDIR /malefiz
RUN sbt compile

CMD sbt run