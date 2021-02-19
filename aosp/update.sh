#!/bin/bash
# update git repository from local asop
SRC_DIR=/hdd_ext/hdd4000/chun/source/mobile_plus/frameworks
DST_DIR=~/AndroidStudioProjects/MobilePlus-Prototype/aosp/frameworks

rsync -au ${SRC_DIR}/base/core ${DST_DIR}/base
rsync -au ${SRC_DIR}/base/services ${DST_DIR}/base
rsync -au ${SRC_DIR}/native/libs/binder ${DST_DIR}/native/libs
rsync -au ${SRC_DIR}/native/include/binder ${DST_DIR}/native/include
