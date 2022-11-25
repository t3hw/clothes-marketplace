if ! [ -r /workspaces/clothes-marketplace-swagger ] ; then
    git clone git@github.com:t3hw/clothes-marketplace-swagger.git /workspaces/clothes-marketplace-swagger
fi

mvn install -f /workspaces/clothes-marketplace-swagger
mvn clean compile -f /workspaces/clothes-marketplace
apt-get update
apt-get install -y curl

echo
echo Done creating dev container
echo

java -version