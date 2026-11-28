SUMMARY = "Qwen3.5-4B-UD-Q4_K_XL model"
DESCRIPTION = "Qwen3.5-4B-UD-Q4_K_XL model."
HOMEPAGE = "https://huggingface.co/unsloth/Qwen3.5-4B-GGUF"
SECTION = "data"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://README.md;md5=7e03d023e1298d9d188f38f7fba17f18"

SRC_DL_REV = "e87f176479d0855a907a41277aca2f8ee7a09523"
SRC_URI = " \
    https://huggingface.co/unsloth/Qwen3.5-4B-GGUF/resolve/${SRC_DL_REV}/Qwen3.5-4B-UD-Q4_K_XL.gguf;name=model \
    https://huggingface.co/unsloth/Qwen3.5-4B-GGUF/resolve/${SRC_DL_REV}/README.md;name=license \
"
SRC_URI[model.sha256sum] = "b252c5610a42ca82d20fe2a12813e9d069eed89292907e26c783eeb0bc961bc7"
SRC_URI[license.sha256sum] = "28546f5fc4f22ee1b3f17b908957f17fcfa66174032136265bdb27f404bc1e6c"

inherit allarch

S = "${UNPACKDIR}"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_compile[noexec] = "1"
do_install() {
    install -d ${D}${datadir}/gguf/${PN}
    cp ${S}/Qwen3.5-4B-UD-Q4_K_XL.gguf ${D}${datadir}/gguf/${PN}
    cp ${S}/README.md ${D}${datadir}/gguf/${PN}
}

PACKAGES = "\
    ${PN} \
"

FILES:${PN} = " \
    ${datadir}/gguf/${PN}/* \
"
