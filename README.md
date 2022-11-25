## PS Api #

### How do I get set up?
#
### In Vscode:
#### CTRL+Shift+P -> Clone repository in container volume: `git@github.com:t3hw/clothes-marketplace.git`
#### CTRL+Shift+P -> `Reload window` if ide requires.
#### *clothes-marketplace-swaggers repository will be automatically installed*
#
### To install the Swagger jar on the local maven repository *(outside of vscode dev container)*
#### get the repository `git@github.com:t3hw/clothes-marketplace-swagger.git`
#### run `./mvnw install` in the project root folder
#### In maven, run clean, and compile
#


### Running the image locally
#### Remove commented env variables from Dockerfile
#### run `docker-compose up`

#### swagger page:

`{protocol}:{host}:{port}/swagger-ui.html`