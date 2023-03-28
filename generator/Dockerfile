FROM ubuntu:focal

LABEL maintainer="local" 

# Required for install
RUN apt-get update && apt-get install -y curl libunwind8 

# Install Java JDK
RUN	apt-get install -y software-properties-common && \
	apt-get install wget 

RUN wget -O java.deb https://cdn.azul.com/zulu/bin/zulu11.41.23-ca-jdk11.0.8-linux_amd64.deb && \
	apt-get install -y java-common && \
	apt-get install -y libasound2 && \
	apt-get install -y libx11-6 && \
	apt-get install -y libxext6 && \
	apt-get install -y libxi6 && \
	apt-get install -y libxrender1 && \
	apt-get install -y libxtst6 && \
	apt-get install -y libfontconfig1 && \
	dpkg -i java.deb

# NodeJS && Java
RUN curl -sL https://deb.nodesource.com/setup_19.x | bash - && \
	apt-get update && apt-get install -y nodejs && \
	npm install npm@latest -g

# Autorest
RUN npm install -g autorest@latest
RUN autorest --reset --allow-no-input --java --use:@autorest/java@4.0.1 --azure-validator --preview

# Set the locale to UTF-8
RUN apt-get clean && apt-get update && apt-get install -y locales
RUN locale-gen en_US.UTF-8  
ENV LANG en_US.UTF-8  
ENV LANGUAGE en_US:en  
ENV LC_ALL en_US.UTF-8  
COPY readme.md /input/readme.md

ENTRYPOINT ["autorest"]