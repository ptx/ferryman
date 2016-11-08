# Simple Makefile that builds the tar file and the docker image

VERSION        := 0.0.1
DOCKER_VERSION :=v$(VERSION)
HTTP_PORT      := 8080

# arguments to pass docker when running the image locally
POSTGRES_HOST     := $(shell docker-machine active | xargs docker-machine ip)
POSTGRES_PORT     := 5432
POSTGRES_DATABASE := ferryman
POSTGRES_USER     := charon
POSTGRES_PASSWD   := password

all: clean browserify build docker

browserify:
	mkdir -p public/scripts/compiled
	browserify -t [ babelify --presets [ react ] ] src/index/main.js -o public/scripts/compiled/index.js

build:
	$(info Building Version --> $(VERSION), Docker Version --> $(DOCKER_VERSION))
	tar -cvzf ferryman-$(VERSION).tgz README.md package.json index.js postgresql.js public/

docker:
	$(info Docker Build Version --> $(VERSION), Docker Version --> $(DOCKER_VERSION))
	docker build --build-arg FERRYMAN_VERSION=$(VERSION) -t ptx/ferryman:$(DOCKER_VERSION) .

clean:
	$(info Cleaning Version --> $(VERSION), Docker Version --> $(DOCKER_VERSION))
	rm -rf ferryman-$(VERSION).tgz public/scripts/compiled/

dpush:
	docker push ptx/ferryman:$(DOCKER_VERSION)

# run the docker container locally
runlocal:
	docker run --rm --name ferryman -p $(HTTP_PORT):$(HTTP_PORT) -e POSTGRES_HOST=$(POSTGRES_HOST) -e POSTGRES_PORT=$(POSTGRES_PORT) -e POSTGRES_DATABASE=$(POSTGRES_DATABASE) -e POSTGRES_USER=$(POSTGRES_USER) -e POSTGRES_PASSWD=$(POSTGRES_PASSWD) ptx/ferryman:$(DOCKER_VERSION)
