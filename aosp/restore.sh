#!/bin/bash
# Restore local aosp to this repository's code
SRC_DIR=~/AndroidStudioProjects/MobilePlus-Prototype/aosp/frameworks
DST_DIR=/hdd_ext/hdd4000/chun/source/mobile_plus/frameworks

rsync -au ${SRC_DIR}/base/core ${DST_DIR}/base
rsync -au ${SRC_DIR}/base/services ${DST_DIR}/base
rsync -au ${SRC_DIR}/native/libs/binder ${DST_DIR}/native/libs
rsync -au ${SRC_DIR}/native/include/binder ${DST_DIR}/native/include
