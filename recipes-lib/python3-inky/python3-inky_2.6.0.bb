# Recipe created by recipetool and updated for completeness

SUMMARY = "Inky pHAT Driver"
HOMEPAGE = "https://www.pimoroni.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=990500e2985e027f34bcefb602873d7f"

DEPENDS = " \
    python3-hatchling-native \
    python3-hatch-fancy-pypi-readme-native \
    python3-hatch-requirements-txt-native \
    python3-uv-dynamic-versioning-native \
"

SRC_URI[sha256sum] = "bf9e6d8d6d7b2a5b34ab3e82bd26fe3b5ccdffcbf7c01c3b49d74df1f2f85679"

inherit pypi python_hatchling

PYPI_PACKAGE = "inky"

RDEPENDS:${PN} = " \
    python3-core \
    python3-gpiodevice \
    python3-numpy \
    python3-pillow \
    python3-smbus2 \
    python3-spidev \
"