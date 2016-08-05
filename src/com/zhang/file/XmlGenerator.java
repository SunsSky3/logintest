package com.zhang.file;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Document;  
import org.dom4j.DocumentHelper;  
import org.dom4j.Element;  
import org.dom4j.io.XMLWriter; 


public class XmlGenerator {
	String originalDocument ;
	String pdfDocument ;
	String swfDocument ;
//	String originalDocument ="123" ;
//	String pdfDocument ="321" ;
//	String swfDocument ="234" ;
	
	public void build(String sourceXml,String filepath,String originalDocument,String pdfDocument,String swfDocument,String size1,String size2,String size3,String lastmod1,String lastmod2,String lastmod3){  
        try {  
            //DocumentHelper提供了创建Document对象的方法  
            Document document = DocumentHelper.createDocument();  
            //添加节点信息  
            Element rootElement = document.addElement("presentation");
            
            rootElement.addElement("originalDocument")
            			.addAttribute("lastmod", lastmod1)
            			.addAttribute("size", size1)
            			.addText(originalDocument);
            
            if (pdfDocument!=null) {
				rootElement.addElement("pdfDocument")							
							.addAttribute("lastmod", lastmod2)
							.addAttribute("size", size2)
							.addText(pdfDocument);				
			}
            
            if (swfDocument!=null) {
            	rootElement.addElement("swfDocument")
							.addAttribute("lastmod", lastmod3)
							.addAttribute("size", size3)
							.addText(swfDocument);					
			}
                        
//            Element thumbs = rootElement.addElement( "thumbs" );
            rootElement.addElement( "thumbs" );            
//            System.out.println(document.asXML()); //将document文档对象直接转换成字符串输出  
//            Writer fileWriter = new FileWriter("library.xml");  
            Writer fileWriter = new FileWriter(sourceXml);  
            //dom4j提供了专门写入文件的对象XMLWriter  
            XMLWriter xmlWriter = new XMLWriter(fileWriter); 
            xmlWriter.write(document);             
            xmlWriter.flush();  
            xmlWriter.close();  
//            System.out.println("xml文档添加成功！");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	
      
//    public static void main(String[] args) {  
//    	XmlGenerator demo = new XmlGenerator();  
//        demo.build();  
//    }  
	
}
