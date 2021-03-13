#!/bin/bash
# update git repository from local asop
SRC_DIR=~/workspace/android-11.0.0_r27/frameworks
DST_DIR=~/workspace/MobilePlus-Prototype/aosp/frameworks

rsync -au ${SRC_DIR}/base/core ${DST_DIR}/base
rsync -au ${SRC_DIR}/base/services ${DST_DIR}/base
rsync -au ${SRC_DIR}/native/libs/binder ${DST_DIR}/native/libs
rsync -au ${SRC_DIR}/native/include/binder ${DST_DIR}/native/include
