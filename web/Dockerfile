FROM fedora:24
MAINTAINER Jeffrey.N.Davis@gmail.com

# ensure image is up to date, install node and npm
RUN dnf upgrade --setopt=deltarpm=false -y && \
  dnf install --setopt=deltarpm=false -y tar npm node && \
  dnf clean all --setopt=deltarpm=false -y

# set App version & Port
ARG FERRYMAN_VERSION
env FERRYMAN_VERSION=$FERRYMAN_VERSION

# Create App base directory
RUN mkdir -p /usr/local/src/ferryman

# Install App
COPY ferryman-$FERRYMAN_VERSION.tgz /tmp
RUN tar -xvf /tmp/ferryman-$FERRYMAN_VERSION.tgz -C /usr/local/src/ferryman && \
  rm /tmp/ferryman-$FERRYMAN_VERSION.tgz

# Install dependencies
WORKDIR /usr/local/src/ferryman
RUN npm install

EXPOSE 8080
CMD ["npm", "start"]
