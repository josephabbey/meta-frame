# Recipe created for gpiodevice

SUMMARY = "Helper library for working with Linux gpiochip devices"
HOMEPAGE = "https://github.com/pimoroni/gpiodevice-python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7e9736f3dd7662160513710741dbad17"

DEPENDS = " \
    python3-hatchling-native \
    python3-hatch-fancy-pypi-readme-native \
    python3-hatch-requirements-txt-native \
    python3-uv-dynamic-versioning-native \
"

SRC_URI[sha256sum] = "823185e2230aba7a015f00b9f8045840f8f9253d2d58c76950065f446eb417b2"

inherit pypi python_hatchling

PYPI_PACKAGE = "gpiodevice"

RDEPENDS:${PN} = " \
    python3-core \
    python3-gpiod \
"