SUMMARY = ""
DESCRIPTION = ""
LICENSE = "MIT"

COMPATIBLE_MACHINE = "raspberrypi0-2w-64-frame(-dev)?"

inherit core-image

IMAGE_INSTALL:append = " \
    packagegroup-core-boot \
"

IMAGE_FSTYPES ?= "wic.bz2 ext4"

# -- Time --
IMAGE_INSTALL:append = " ntp"


# -- I2C and SPI --
IMAGE_INSTALL:append = " \
    python3 \
    kernel-module-spidev \
    kernel-module-spi-bcm2835 \
    kernel-module-i2c-dev \
    udev-rules-rpi \
"

# Load the SPI and I2C kernel modules at boot
KERNEL_MODULE_AUTOLOAD:append = " \
    spi-bcm2835 \
    spidev \
    i2c-dev \
"

IMAGE_INSTALL:append = " \
    frame \
"

WKS_FILE = "sdimage-frame.wks"
