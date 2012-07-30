package util;

import java.io.File;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


public class TransformationApp 
{
  static Document document; 

  public static void main(String argv[]) throws Exception
  {
	  TransformerFactory tFactory = TransformerFactory.newInstance();
	  Transformer transformer = tFactory.newTransformer();

	  DOMSource source = new DOMSource(QRGenerator.generateQRCodeSVG("3284983249083240932409"));
	  StringWriter sw = new StringWriter();
	  StreamResult result = new StreamResult(sw);
	  transformer.transform(source, result);
	  System.out.println( sw.toString());
	  result = new StreamResult(new File("abc.svg"));
	  transformer.transform(source, result);
  } // main 
} 