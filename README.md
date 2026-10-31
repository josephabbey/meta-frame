# meta-frame

OpenEmbedded / Yocto Project BSP & application layer for [Frame](https://github.com/josephabbey/frame) — an e-ink photo frame interface for the Raspberry Pi Zero 2 W with Pimoroni Inky Impression displays.

## Overview

`meta-frame` provides board support configurations, system recipes, Python dependencies, and system images to run the Frame photo frame appliance.

### Machines

- `raspberrypi0-2w-64-frame`: Target machine for Raspberry Pi Zero 2 W (64-bit) with I2C and SPI buses enabled, device tree overlays configured (`spi0-0cs`), and required kernel modules autoloaded (`spi-bcm2835`, `spidev`, `i2c-dev`).
- `raspberrypi0-2w-64-frame-dev`: Extends `raspberrypi0-2w-64-frame` with UART enabled and DWC2 USB peripheral mode for development and debugging over USB gadget.

### Images

- `frame-image`: Minimal production image containing the `frame` daemon and display drivers.
- `frame-image-dev`: Development image with USB Ethernet gadget (`usb-gadget`), Dropbear SSH server, tools-debug, package management, and blank root password for easy network setup.

### Recipes

- `recipes-core/frame`: Recipe packaging [josephabbey/frame](https://github.com/josephabbey/frame) along with its SysV init service (`frame-display`).
- `recipes-core/usb-gadget`: RNDIS/ECM USB Ethernet gadget setup for USB OTG debugging.
- `recipes-lib`: Python dependency recipes including:
  - `python3-inky` (Pimoroni Inky Impression driver)
  - `python3-gpiodevice`
  - `python3-material-color-utilities`
  - `python3-dunamai`
  - `python3-uv-dynamic-versioning`

## Layer Dependencies

This layer depends on:

- **URI:** `https://git.openembedded.org/openembedded-core`  
  **Branch:** `wrynose` (or compatible Yocto release)  
  **Layers:** `meta`
- **URI:** `https://git.yoctoproject.org/meta-yocto`  
  **Branch:** `wrynose`  
  **Layers:** `meta-poky`, `meta-yocto-bsp`
- **URI:** `https://git.openembedded.org/meta-openembedded`  
  **Branch:** `wrynose`  
  **Layers:** `meta-oe`, `meta-python`, `meta-networking`
- **URI:** `https://git.yoctoproject.org/meta-raspberrypi`  
  **Branch:** `wrynose`

## Setup & Building

To initialize a complete build environment with all required layers and dependencies using `bitbake-setup`, see the [frame-os](https://github.com/josephabbey/frame-os) setup repository.

```bash
bitbake-setup init https://raw.githubusercontent.com/josephabbey/frame-os/main/frame.conf.json
cd frame
source build/init-build-env
bitbake frame-image-dev
```

## License

All metadata in `meta-frame` is MIT licensed unless otherwise specified in individual recipe files.
