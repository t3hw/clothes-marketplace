
export DbUrl="jdbc:mysql://localhost:3306/"
export DbParams="?serverTimezone=UTC&useLegacyDatetimeCode=FALSE&useSSL=FALSE&allowPublicKeyRetrieval=TRUE"
export DbName="db"
export DBusername=admin
export DBpassword=admin123
export LOGGER_APPENDER=Text
export LOG_ROOT=log
export PORT=2000

java -jar target/clothes_marketplace-0.0.1-SNAPSHOT.jar
