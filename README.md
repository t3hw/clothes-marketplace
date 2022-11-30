## Clothes Marketplace #

### How do I get set up?
#
### In Vscode:
#### CTRL+Shift+P -> Clone repository in container volume: `https://github.com/t3hw/clothes-marketplace.git`
#### If there are compilation errors such as "missing maven repository", run `./.devcontainer/devcontainer.sh` to manually pull the swagger jar repository to the local machine
#### CTRL+Shift+P -> `Reload window` if there are any errors that refuse to go away after a couple of minutes.
#### *if there are no errors, it means that clothes-marketplace-swaggers repository was automatically loaded into the dev container and installed into the local maven repo*
#### If for some reason the script hasnt installed the swagger to the local repository, run `"/workspaces/clothes-marketplace-swagger/mvnw" install -f "/workspaces/clothes-marketplace-swagger/pom.xml"`
#
### To install the Swagger jar on the local maven repository *(outside of vscode dev container)*
#### get the repository `git clone git@github.com:t3hw/clothes-marketplace-swagger.git`
#### run `./mvnw install` in the project root folder
#### In maven, run clean, and compile
#


### Running the image locally
#### docker compose
#### *paths are relative to project root*
#### Local dev db image build and run: `docker-compose -f ./scripts/docker-compose/marketplace-db/docker-compose.yaml up -d`
#### Local docker image build and run: `docker-compose -f ./scripts/docker-compose/marketplace-dev/docker-compose.yaml up -d`
#### Local docker image build:         `docker-compose -f ./scripts/docker-compose/marketplace-dev/docker-compose.yaml build`
#### Push images to Docker Hub:        `docker-compose -f ./scripts/docker-compose/marketplace-dev/docker-compose.yaml push`
#### Prod-like environment:            `docker-compose -f ./scripts/docker-compose/marketplace-prod/docker-compose.yaml up -d`


#### swagger page:

`{protocol}:{host}:{port}/swagger-ui/index.html`