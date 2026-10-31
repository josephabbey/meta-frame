SUMMARY = "Material color utilities for Python"
HOMEPAGE = "https://github.com/material-foundation/material-color-utilities"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=62326d0721ad672a2df7c469e39e68f5 \
                    file://abseil-cpp/LICENSE;md5=df52c6edb7adc22e533b2bacc3bd3915"

EXTRA_FILESPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "gitsm://github.com/RuurdBijlsma/material-color-utilities.git;protocol=https;branch=main;tag=${PV} \
           file://0001-python3-material-color-utilities-fix-install.patch \
           "
SRCREV = "${PV}"

inherit cmake python3native python3-dir

# Map CMake package dependencies to Yocto recipes
DEPENDS = " \
    python3-pybind11 \
    googletest \
    python3 \
"

# Handle missing execinfo library dependency if building with musl libc
DEPENDS:append:libc-musl = " libexecinfo"

# Force CMake to target cross-compiled Python and pass python site-packages path
EXTRA_OECMAKE += " \
    -DPython3_EXECUTABLE=${STAGING_BINDIR_NATIVE}/python3-native/python3 \
    -DPython3_INCLUDE_DIRS=${STAGING_INCDIR}/python${PYTHON_BASEVERSION} \
    -DPython3_LIBRARIES=${STAGING_LIBDIR}/libpython${PYTHON_BASEVERSION}.so \
    -DPython3_FIND_STRATEGY=LOCATION \
    -DPYTHON_SITEPACKAGES_DIR=${PYTHON_SITEPACKAGES_DIR} \
"

RDEPENDS:${PN} = " \
    python3-core \
    python3-pillow \
    python3-numpy \
"

# Ship files installed into Python site-packages
FILES:${PN} += " \
    ${PYTHON_SITEPACKAGES_DIR}/* \
"

do_install:append() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/material_color_utilities
    if [ -d "${S}/src/material_color_utilities" ]; then
        cp -r ${S}/src/material_color_utilities/*.py ${D}${PYTHON_SITEPACKAGES_DIR}/material_color_utilities/
    fi

    for f in ${D}${PYTHON_SITEPACKAGES_DIR}/material_color_utilities/_core*.so; do
        if [ -f "$f" ]; then
            target_file=$(echo "$f" | sed 's/x86_64-linux-gnu/aarch64-linux-gnu/g')
            if [ "$f" != "$target_file" ]; then
                mv "$f" "$target_file"
            fi
        fi
    done
}

# Bypass QA check for binaries pre-stripped upstream
INSANE_SKIP:${PN} += "already-stripped"
