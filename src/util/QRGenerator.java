package util;

import java.awt.image.BufferedImage;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import ru.hobbut.fop.zxing.qrcode.QRCodeSVGImageConverter;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

//From https://groups.google.com/forum/?fromgroups#!topic/zxing/6PvN5S-e_YA
public class QRGenerator {
	private static final int BLACK = 0xFF000000; // ARGB
	private static final int WHITE = 0xFFFFFFFF; // ARGB

	public static BufferedImage generateQRCode(String content)
			throws Exception {
		QRCode q;
		q = new QRCode();
		Encoder.encode(content, ErrorCorrectionLevel.L, q);

		ByteMatrix m = q.getMatrix();
		int w = m.getWidth();
		int h = m.getHeight();

		// 4 pixels on all sides for the mandatory quiet zone.
		BufferedImage img;
		img = new BufferedImage(w + 8, h + 8, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				img.setRGB(x + 4, y + 4, m.get(x, y) != 0 ? BLACK : WHITE);
			}
		}
		return img;
	}

	public static Document generateQRCodeSVG(String content) throws Exception {
		QRCode q;
		q = new QRCode();
		Encoder.encode(content, ErrorCorrectionLevel.L, q);
		QRCodeSVGImageConverter qrSVGConverter = new QRCodeSVGImageConverter();

		return qrSVGConverter.byteMatrixToSVGDocument(q.getMatrix());
	}

	public static byte[] generateQRCodeSVGString(String content)
			throws Exception {
		QRCode q;
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);

		q = new QRCode();
		Encoder.encode(content, ErrorCorrectionLevel.L, q);
		DOMSource source = new DOMSource(QRGenerator.generateQRCodeSVG(content));

		transformer.transform(source, result);
		System.out.println(sw.toString());

		return sw.toString().getBytes();
	}

}