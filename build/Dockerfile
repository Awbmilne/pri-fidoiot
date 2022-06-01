# Copyright 2020 Intel Corporation
# SPDX-License-Identifier: Apache 2.0

FROM ubuntu:20.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get clean && apt-get -y update && apt-get -y upgrade

RUN apt-get install -y openjdk-11-jdk maven git

RUN mkdir /pri-fidoiot/ \
    && mkdir /fdo-m2/

WORKDIR /pri-fidoiot/
ENTRYPOINT ./build/build.sh
