SUMMARY = "Llama.cpp – Run LLM Inference in C/C++"
DESCRIPTION = "Llama.cpp (LLaMA C++) allows you to run efficient Large Language Model Inference in pure C/C++."
HOMEPAGE = "https://llama-cpp.com/"
BUGTRACKER = "https://github.com/ggml-org/llama.cpp/issues"
SECTION = "base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=223b26b3c1143120c87e2b13111d3e99"

DEPENDS = "openssl"

PV = "1.1.0"
SRC_URI = "\
    git://github.com/ggml-org/llama.cpp.git;protocol=https;branch=master \
    file://llama-server.service \
    file://llama-server \
"

SRCREV = "171527522610996f5f0acf12334e185156ca8f44"

inherit cmake pkgconfig systemd

# Enable optional features
PACKAGECONFIG ??= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'vulkan', '', d)} \
    "
PACKAGECONFIG[vulkan] = " \
    -DGGML_VULKAN=ON -DVulkan_GLSLC_EXECUTABLE=${STAGING_BINDIR_NATIVE}/glslc, \
    -DGGML_VULKAN=OFF, \
    vulkan-loader vulkan-headers shaderc-native spirv-headers \
    "

EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=Release \
    -DLLAMA_BUILD_TESTS=OFF \
"

do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${UNPACKDIR}/llama-server.service ${D}/${systemd_unitdir}/system

    install -m 0755 -d ${D}${sysconfdir}/default/
    install -m 0755 ${UNPACKDIR}/llama-server ${D}${sysconfdir}/default/
}

PACKAGES:prepend = " \
    lib${PN} ${PN}-services ${PN}-tests ${PN}-tools ${PN}-examples \
    "

FILES:lib${PN} = " \
    ${libdir}/libllama${SOLIBS} ${libdir}/libllama-common${SOLIBS} \
    ${libdir}/libmtmd${SOLIBS} \
    ${libdir}/libggml${SOLIBS} ${libdir}/libggml-base${SOLIBS} \
    ${libdir}/libggml-cpu${SOLIBS} ${libdir}/libggml-vulkan${SOLIBS} \
    "

FILES:${PN}-services  = " \
    ${systemd_unitdir} \
    ${sysconfdir}/*/* \
    "
SYSTEMD_PACKAGES = "${PN}-services"
SYSTEMD_SERVICE:${PN}-services = "llama-server.service"
RDEPENDS:${PN}-services = "${PN}-tools"

FILES:${PN}-tests  = " \
    ${bindir}/test-* \
    "
RDEPENDS:${PN}-tests = "${PN}"

FILES:${PN}-tools  = " \
    ${bindir}/llama-bench       ${bindir}/llama-cli \
    ${bindir}/llama-completion  ${bindir}/llama-quantize \
    ${bindir}/llama-server      ${bindir}/llama-batched-bench \
    ${bindir}/llama-fit-params  ${bindir}/llama-gguf-split \
    ${bindir}/llama-imatrix     ${bindir}/llama-mtmd-cli \
    ${bindir}/llama-perplexity  ${bindir}/llama-results \
    ${bindir}/llama-tokenize    ${bindir}/llama-tts \
    ${bindir}/llama-export-lora ${bindir}/llama-cvector-generator \
    ${bindir}/llama-convert-llama2c-to-ggml \
    ${libdir}/libllama-bench-impl.so \
    ${libdir}/libllama-cli-impl.so \
    ${libdir}/libllama-completion-impl.so \
    ${libdir}/libllama-quantize-impl.so \
    ${libdir}/libllama-server-impl.so \
    ${libdir}/libllama-fit-params-impl.so \
    ${libdir}/libllama-batched-bench-impl.so \
    ${libdir}/libllama-perplexity-impl.so \
    "
RDEPENDS:${PN}-tools = "${PN}"

FILES:${PN}-examples  = " \
    ${bindir}/llama-batched       ${bindir}/llama-eval-callback \
    ${bindir}/llama-gguf-hash     ${bindir}/llama-lookup-create \
    ${bindir}/llama-passkey       ${bindir}/llama-speculative \
    ${bindir}/llama-debug         ${bindir}/llama-finetune \
    ${bindir}/llama-idle          ${bindir}/llama-lookup-merge \
    ${bindir}/llama-retrieval     ${bindir}/llama-speculative-simple \
    ${bindir}/llama-diffusion-cli ${bindir}/llama-gen-docs \
    ${bindir}/llama-lookahead     ${bindir}/llama-lookup-stats \
    ${bindir}/llama-simple        ${bindir}/llama-embedding \
    ${bindir}/llama-gguf          ${bindir}/llama-lookup \
    ${bindir}/llama-parallel      ${bindir}/llama-simple-chat \
    "
RDEPENDS:${PN}-examples = "${PN}-tools"

RDEPENDS:${PN} = "lib${PN}"
