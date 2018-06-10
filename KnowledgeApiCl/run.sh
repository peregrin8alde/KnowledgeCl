#/bin/sh

java -cp target/KnowledgeApiCl-1.0-SNAPSHOT.jar:target/dependency/* \
  myapps.App "$@"

exit 0;
