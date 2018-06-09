#/bin/sh

mvn package \
  && mvn dependency:copy-dependencies

exit 0;
