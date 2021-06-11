#!/usr/bin/env sh
flutter pub get
cd ..
./gradlew build
pwd
cd study/.android

./gradlew \
  -I=../script/flutter_aar_upload.gradle \
  -Pmaven-url=http://192.168.1.209:9081/repository/android-maven-snapshots/ \
  -Pmaven-user=android \
  -Pmaven-pwd=ichoice411 \
  -Pis-plugin=false \
  -PbuildNumber=1.0 \
  -Ptarget-platform=android-arm,android-arm64,android-x64 assembleAarRelease