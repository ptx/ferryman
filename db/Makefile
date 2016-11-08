# Simple Makefile that builds the tar file and the docker image

VERSION := v0.0.1

# arguments to pass docker when running the image locally
PASSWORD := password
DB       := ferryman
USER     := charon
PORT     := 5432

all: build 

# push docker images to remote
build:
	docker build -t ptx/ferryman-db:$(VERSION) .

dpush:
	docker push ptx/ferryman-db:$(VERSION)

# run the docker container locally
runlocal:
	docker run --rm --name ferryman-db -e POSTGRES_PASSWORD=$(PASSWORD) -e POSTGRES_DB=$(DB) -e POSTGRES_USER=$(USER) -p $(PORT):$(PORT) ptx/ferryman-db:$(VERSION)

