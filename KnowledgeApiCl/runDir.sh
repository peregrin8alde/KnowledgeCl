#/bin/sh

URL=$1
TOKEN=$2
BASEDIRNAME=$3
CONTENTSDIR=$4
PRVIVATE=$5

find $CONTENTSDIR -name "*.md" | while read FILE
do
  TITLE=`sed -n -E 's/^# (.*)/\1/p' $FILE | head -n 1`
  if [ -z "$TITLE" ]; then
    TITLE=$(basename $FILE)
  fi
  
  TAGS="api,path:$BASEDIRNAME/${FILE#$CONTENTSDIR}"
  CONTENTFILE=${FILE}

  ./run.sh -mode "POST" \
           -url $URL \
           -token $TOKEN \
           -title $TITLE \
           -tags $TAGS \
           $PRVIVATE \
           -file $CONTENTFILE
done



exit 0;
