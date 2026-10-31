SUMMARY = "USB Gadget ConfigFS Initialization Service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://usb-gadget"

S = "${UNPACKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "usb-gadget"
INITSCRIPT_PARAMS = "start 10 S ."

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/usb-gadget ${D}${sysconfdir}/init.d/usb-gadget
}

FILES:${PN} += "${sysconfdir}/init.d/usb-gadget"
