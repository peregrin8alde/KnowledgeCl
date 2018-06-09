#/bin/sh

mvn archetype:generate \
  -DgroupId=myapps \
  -DartifactId=KnowledgeApiCl \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false

exit 0;
