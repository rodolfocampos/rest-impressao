package br.com.rdo.clientrest.service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

import org.springframework.stereotype.Component;

/*Testado em uma impressora Engin i9 SO Windows*/
@Component
public class PrintUtilities {

	private String cortar = "" + (char) 29 + (char) 86 + (char) 0;
	private String desativaNegrito = "" + (char) 27 + (char) 69 + (char) 0;
	private String negrito = "" + (char) 27 + (char) 69 + (char) 1;

	public Boolean imprimirTexto(String textoImpressao) {
		textoImpressao = textoImpressao.replaceAll("AtivaNegrito", negrito).replaceAll("DesativaNegrito",
				desativaNegrito);
		StringBuilder texto = new StringBuilder();
		texto.append(textoImpressao);
		texto.append("\n \n \n \n");
		texto.append(cortar);
		String textoFinal = String.valueOf(texto);
		try {
			InputStream prin = new ByteArrayInputStream((textoFinal).getBytes("ISO-8859-1"));
			DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			SimpleDoc documentoTexto = new SimpleDoc(prin, docFlavor, null);
			PrintService impressora = PrintServiceLookup.lookupDefaultPrintService();
			prin.close();
			PrintRequestAttributeSet printAtrributes = new HashPrintRequestAttributeSet();
			printAtrributes.add(OrientationRequested.PORTRAIT);
			printAtrributes.add(MediaSizeName.ISO_A4);
			DocPrintJob printJob = impressora.createPrintJob();

			printJob.print(documentoTexto, printAtrributes);

			return true;
		} catch (PrintException | IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/* Imprime na impressora padrão do SO */
	public void imprimirArquivo(String caminho) throws IOException {
		PrintService impressora = PrintServiceLookup.lookupDefaultPrintService();
		DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		try {
			FileInputStream stream = new FileInputStream(caminho);
			Doc doc = new SimpleDoc(stream, docFlavor, null);
			DocPrintJob printJob = impressora.createPrintJob();
			printJob.print(doc, null);
			cortarPapel();
			stream.close();
		} catch (PrintException e) {
			e.printStackTrace();
		}

	}

	public void cortarPapel() throws IOException {
		try {
			InputStream prin = new ByteArrayInputStream((cortar).getBytes());
			DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			SimpleDoc documentoTexto = new SimpleDoc(prin, docFlavor, null);
			PrintService impressora = PrintServiceLookup.lookupDefaultPrintService();
			DocPrintJob printJob = impressora.createPrintJob();
			printJob.print(documentoTexto, null);
			prin.close();
		} catch (PrintException e) {
			e.printStackTrace();
		}
	}

}
