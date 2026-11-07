LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

EXTRA_FILESPATHS:prepend := "${THISDIR}/files:"

SRC_URI = " \
    git://github.com/josephabbey/frame.git;protocol=https;branch=main;rev=main \
    file://frame \
    file://Pictures/ \
"

PV = "git+1.0.0"

inherit python_pep517 update-rc.d useradd

DEPENDS += "python3-uv-build-native"

python_pep517_do_compile:prepend() {
    export PYTHONPATH="${STAGING_LIBDIR_NATIVE}/${PYTHON_DIR}/site-packages:${PYTHONPATH}"
}

RDEPENDS:${PN} = " \
    python3-core \
    python3-pillow \
    python3-inky \
    python3-qrcode \
    python3-material-color-utilities \
    python3-dacite \
"

# Create required system groups and user with access to SPI, I2C, and GPIO groups.
USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system i2c; --system spi; --system gpio"
USERADD_PARAM:${PN} = "--system --create-home --home-dir /var/lib/frame --groups spi,i2c,gpio frame"

INITSCRIPT_NAME = "frame"
INITSCRIPT_PARAMS = "defaults 90 10"

do_install:append() {
    install -d ${D}/var/lib/frame
    cp -r ${UNPACKDIR}/Pictures ${D}/var/lib/frame
    chown -R frame:frame ${D}/var/lib/frame
    chmod 755 ${D}/var/lib/frame

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${UNPACKDIR}/frame ${D}${sysconfdir}/init.d/frame
}

FILES:${PN} += " \
    ${sysconfdir}/init.d/frame \
    /var/lib/frame \
"

INSANE_SKIP:${PN} += "already-stripped ldflags"
CONFIGURE_FILES += "pyproject.toml"
