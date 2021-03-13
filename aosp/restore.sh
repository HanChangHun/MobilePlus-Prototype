#!/bin/bash
# Restore local aosp to this repository's code
SRC_DIR=~/workspace/MobilePlus-Prototype/aosp/frameworks
DST_DIR=~/workspace/android-11.0.0_r27/frameworks

rsync -au ${SRC_DIR}/base/core/ ${DST_DIR}/base/core
rsync -au ${SRC_DIR}/base/services/ ${DST_DIR}/base/services
rsync -au ${SRC_DIR}/native/libs/binder/ ${DST_DIR}/native/libs/binder
rsync -au ${SRC_DIR}/native/include/binder/ ${DST_DIR}/native/include/binder
