#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#
SUMMARY = "Simple helloworld application"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = "file://${THISDIR}/files/helloworld.c \
           file://${THISDIR}/files/hello.service \
"

S = "${WORKDIR}"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = " hello.service"

FILES_${PN} +=  " ${systemd_unitdir}/system/hello.service \
"

do_compile() {
         ${CC} ${THISDIR}/files/helloworld.c -o hello ${LDFLAGS}
}

do_install() {
         install -d ${D}${bindir}
         install -m 0755 hello ${D}${bindir}
         install -d ${D}${systemd_unitdir}/system/
         install -m 0644 ${WORKDIR}/hello.service ${D}${systemd_unitdir}/system/
}
