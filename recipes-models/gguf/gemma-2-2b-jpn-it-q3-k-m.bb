SUMMARY = "gemma-2-2b-jpn-it-Q3_K_M model"
DESCRIPTION = "gemma-2-2b-jpn-it-Q3_K_M model."
HOMEPAGE = "https://huggingface.co/wamo2351/gemma-2-2b-jpn-it-Q3_K_M"
SECTION = "data"
LICENSE = "Gemma"
LIC_FILES_CHKSUM = "file://README.md;md5=48bbdbc6b2160b742bcc025ea97a85c7"

SRC_DL_REV = "3a5602651028396fcf5392ef58febe4179b03a3f"
SRC_URI = " \
    https://huggingface.co/wamo2351/gemma-2-2b-jpn-it-Q3_K_M/resolve/${SRC_DL_REV}/gemma-2-2b-jpn-it-Q3_K_M.gguf;name=model \
    https://huggingface.co/wamo2351/gemma-2-2b-jpn-it-Q3_K_M/resolve/${SRC_DL_REV}/README.md;name=license \
"
SRC_URI[model.sha256sum] = "f57149e19341d2af4fc6c6084a9d4fd9657f89fa229570178c33fa2a399deaa9"
SRC_URI[license.sha256sum] = "145e39d7ec37ecfe0a09890ba6d06d88ce9fb68595da8e4321c2f70217769c53"

inherit allarch

S = "${UNPACKDIR}"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_compile[noexec] = "1"
do_install() {
    install -d ${D}${datadir}/gguf/${PN}
    cp ${S}/gemma-2-2b-jpn-it-Q3_K_M.gguf ${D}${datadir}/gguf/${PN}
    cp ${S}/README.md ${D}${datadir}/gguf/${PN}
}

PACKAGES = "\
    ${PN} \
"

FILES:${PN} = " \
    ${datadir}/gguf/${PN}/* \
"
