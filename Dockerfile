FROM ubuntu

RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt install net-tools -y
RUN apt install vim -y

COPY ./jre-8u341-linux-x64.tar.gz .
COPY ./Servidor-1-16-5 ./Servidor-1-16-5

EXPOSE 25565

RUN tar -xf jre-8u341-linux-x64.tar.gz && rm jre-8u341-linux-x64.tar.gz

ENV PATH="${PATH}:/jre1.8.0_341/bin"
ENV JAVA_HOME=/jre1.8.0_341/bin

WORKDIR /Servidor-1-16-5
RUN export MINEIP=$(hostname -I)
RUN sed -i "s/server-ip=/server-ip=${MINEIP}/" server.properties

CMD bash
ENTRYPOINT java -jar /Servidor-1-16-5/spigot.jar