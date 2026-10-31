require ./frame-image.bb

COMPATIBLE_MACHINE = "raspberrypi0-2w-64-frame-dev"


# -- Ethernet over USB --
IMAGE_INSTALL:append = " kernel-module-libcomposite kernel-module-usb-f-ecm"

KERNEL_MODULE_AUTOLOAD:append = " dwc2 libcomposite usb_f_ecm"

IMAGE_INSTALL:append = " usb-gadget"

# -- SSH --
EXTRA_IMAGE_FEATURES:append = " \
    ssh-server-dropbear \
    tools-debug \
    empty-root-password \
    allow-root-login \
    allow-empty-password \
    package-management \
"
