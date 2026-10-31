This README file contains information on the contents of the meta-frame layer.

Please see the corresponding sections below for details.


Dependencies
============

This layer depends on:

  URI: https://git.openembedded.org/openembedded-core
  layers: meta
  branch: wrynose

  URI: https://git.yoctoproject.org/meta-yocto
  layers: meta-poky, meta-yocto-bsp
  branch: wrynose

  URI: https://git.openembedded.org/meta-openembedded
  layers: meta-oe, meta-python, meta-networking
  branch: wrynose

  URI: https://git.yoctoproject.org/meta-raspberrypi
  branch: wrynose


Patches
=======

Please submit any patches or pull requests via GitHub:
https://github.com/josephabbey/meta-frame

Maintainer: Joseph Abbey <me@josephabbey.dev>


Table of Contents
=================

  I. Adding the meta-frame layer to your build
 II. Machines
III. Images and Recipes
 IV. Setting up with bitbake-setup (Frame OS)


I. Adding the meta-frame layer to your build
============================================

Run 'bitbake-layers add-layer meta-frame'

Alternatively, add the location of the meta-frame layer to your conf/bblayers.conf:

  BBLAYERS ?= " \
    /path/to/layers/openembedded-core/meta \
    /path/to/layers/meta-yocto/meta-yocto-bsp \
    /path/to/layers/meta-yocto/meta-poky \
    /path/to/layers/openembedded-core/meta-raspberrypi \
    /path/to/layers/openembedded-core/meta-openembedded/meta-oe \
    /path/to/layers/openembedded-core/meta-openembedded/meta-python \
    /path/to/layers/openembedded-core/meta-openembedded/meta-networking \
    /path/to/layers/meta-frame \
    "


II. Machines
============

The meta-frame layer provides machine definitions for the Raspberry Pi Zero 2 W:

  - raspberrypi0-2w-64-frame:
    Target machine for running the Frame e-ink display appliance. Enables SPI
    and I2C buses, configures kernel device tree overlays (spi0-0cs), and
    autoloads the required kernel modules (spidev, i2c-dev, spi-bcm2835).

  - raspberrypi0-2w-64-frame-dev:
    Development configuration based on raspberrypi0-2w-64-frame. Enables the
    UART serial console and DWC2 USB peripheral mode for Ethernet-over-USB
    gadget networking and debugging.


III. Images and Recipes
=======================

  - frame-image:
    Minimal production image booting directly into the Frame display service.

  - frame-image-dev:
    Development image featuring an automatic USB Ethernet gadget, Dropbear SSH
    server with empty root password, package management, and debug tools.

  - recipes-core/frame:
    BitBake recipe packaging the Frame Python application
    (https://github.com/josephabbey/frame) and its SysV init service.

  - recipes-core/usb-gadget:
    SysV init service setting up Ethernet-over-USB (RNDIS/ECM) with static IP.

  - recipes-lib:
    Python recipes providing runtime dependencies for Frame, including:
    - python3-inky (Pimoroni Inky Impression driver)
    - python3-gpiodevice
    - python3-material-color-utilities
    - python3-dunamai
    - python3-uv-dynamic-versioning


IV. Setting up with bitbake-setup (Frame OS)
============================================

This layer includes build configuration templates under conf/templates/default/.
To initialize a complete build environment automatically using bitbake-setup:

  bitbake-setup init https://raw.githubusercontent.com/josephabbey/frame-os/main/frame.conf.json
