#/bin/sh

URL=$1
TOKEN=$2
TITLE=$3
TAGS=$4
CONTENTFILE=$5

java -cp target/KnowledgeApiCl-1.0-SNAPSHOT.jar:target/dependency/* \
  myapps.App $URL $TOKEN $TITLE $TAGS $CONTENTFILE

exit 0;
