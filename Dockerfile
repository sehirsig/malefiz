FROM hseeberger/scala-sbt:8u222_1.3.5_2.13.1

ENV DISPLAY 127.0.0.1:0.0

RUN apt-get update && \
    apt-get install -y sbt libxrender1 libxtst6 libxi6

WORKDIR /malefiz
ADD . /malefiz

#RUN sbt compile

CMD sbt run