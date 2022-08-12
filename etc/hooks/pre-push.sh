#!/bin/bash

echo "---------------------------------------------"
echo "[INFO] Running maven build before pushing... "
echo "---------------------------------------------"

# running maven clean verify
mvn -q clean verify
rc=$?
if [ $rc -ne 0 ]; then
  echo "[ERROR] ----------------------------------------------------"
  echo "                                                            "
  echo "    Maven build has been failed... Can't push ¯\_(ツ)_/¯    "
  echo "                                                            "
  echo "    (っ▀¯▀)つ Review your code and try again                 "
  echo "                                                            "
  echo "[ERROR] ----------------------------------------------------"
  exit 1
fi
echo " "
echo "---------------------------------------------"
echo "[INFO] Maven build succeeded"
echo "---------------------------------------------"
exit 0