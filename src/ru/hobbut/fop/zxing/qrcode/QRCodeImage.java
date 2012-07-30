package ru.hobbut.fop.zxing.qrcode;

import org.apache.xmlgraphics.image.loader.ImageFlavor;
import org.apache.xmlgraphics.image.loader.ImageInfo;
import org.apache.xmlgraphics.image.loader.impl.AbstractImage;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.ByteMatrix;

public class QRCodeImage extends AbstractImage {
    
    public static final ImageFlavor QR_CODE_IMAGE_FLAVOR = new ImageFlavor("QRCode");
    
    private final ByteMatrix byteMatrix;

    public QRCodeImage(ImageInfo info, ByteMatrix byteMatrix) {
        super(info);
        this.byteMatrix = byteMatrix;
    }
    
    public ByteMatrix getBitMatrix() {
        return this.byteMatrix;
    }

    public ImageFlavor getFlavor() {
        return QR_CODE_IMAGE_FLAVOR;
    }

    public boolean isCacheable() {
        return true;
    }
}
