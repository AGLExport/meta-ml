SUMMARY = "oneAPI Deep Neural Network Library (oneDNN)"
DESCRIPTION = "Open-source library of optimized deep learning operations (matmul, convolution, attention)."
HOMEPAGE = "https://github.com/uxlfoundation/oneDNN"
BUGTRACKER = "https://github.com/uxlfoundation/oneDNN/issues"
SECTION = "lib"
LICENSE  = "Apache-2.0 & BSD-3-Clause & BSL-1.0 & MIT"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=05fda7e0b3a0fe6749e8443316fc9a3f \
    file://third_party/gtest/LICENSE;md5=cbbd27594afd089daa160d3a16dd515a \
    file://third_party/ittnotify/LICENSE.BSD;md5=e671ff178b24a95a382ba670503c66fb \
    file://third_party/kleidiai/LICENSES/Apache-2.0.txt;md5=c846ebb396f8b174b10ded4771514fcc \
    file://third_party/ngen/COPYRIGHT;md5=5a03e1700352959938c2b169e3270087 \
    file://third_party/opencl/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
    file://third_party/spdlog/spdlog/spdlog.h;md5=bbdc71f447b0d487c15bf3cc03ca02bd \
    file://third_party/xbyak/COPYRIGHT;md5=3b9bf048d063d54cdb28964db558bcc7 \
    "

DEPENDS:append = " arm-compute-library"

SRC_URI = " \
    git://github.com/uxlfoundation/oneDNN.git;protocol=https;branch=rls-v3.14 \
"

SRCREV = "4db284662a37cf46401655b5ee99391d9de52c00"

inherit cmake

EXTRA_OECMAKE = "\
    -DCMAKE_BUILD_TYPE=Release \
    -DACL_ROOT_DIR=${PKG_CONFIG_SYSROOT_DIR}/usr \
    -DDNNL_AARCH64_USE_ACL=ON \
"
