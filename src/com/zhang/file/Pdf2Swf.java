package com.zhang.file;

import java.io.File;

public class Pdf2Swf {
	private static String INPUT_PATH;  
    // 转换后的swf文件路径  
    private static String OUTPUT_PATH;  
    // pdf2swf.exe的路径  
    //private static String PDF2SWF_PATH = "C:\\conference\\conference\\swfttools\\pdf2swf.exe";  
    private static String PDF2SWF_PATH = "C:\\MtSyDeploy\\SWFTools\\pdf2swf.exe";

    private static String fileName = "";
 
    /**   
     * PDF转SWF 
     * @param pdffile PDF文件全路径   
     * @param swffile 转换后SWF文件存放路径   
     */  
    public static void pdfToSwf(String pdffile, String swffile,String fileName)  
    {  
        INPUT_PATH = pdffile;  
        OUTPUT_PATH = swffile;  
        if(checkContentType()==0)  
        {  
            toSwf(fileName);  
        }  
    }  
    /**   
     * 检查文件是否是pdf类型的   
     * @return   
     */   
    private static int checkContentType()  
    {  
        String type = INPUT_PATH.substring(INPUT_PATH.lastIndexOf(".") + 1, INPUT_PATH.length())  
                .toLowerCase();  
        if (type.equals("pdf")){     
        	//System.out.println("*****是pdf文件*****");     
            return 0;     
        }   
        else{  
        	//System.out.println("*****非pdf文件*****");   
            return 9;  
        }         
    }  
    /**   
     * 调用批处理文件生成swf文件   
     */   
    private static void toSwf(String fileName) {    
        if(new File(INPUT_PATH).isFile()){  
            //System.out.println("*****正在转换..*****");  
            try {      
                // 调用创建的bat文件进行转换   
                String cmd = PDF2SWF_PATH + " " + INPUT_PATH+ " -o "   
                        + OUTPUT_PATH + fileName + " -f -T 9";  
                Runtime.getRuntime().exec(cmd);
                //System.out.println("*****转换成功*******");  
            }   
            catch (Exception e) {    
                e.printStackTrace();    
                //System.out.println("*****转换失败*******");  
            }    
        }  
        else{  
        	//System.out.println("*****文件不存在*****");  
        }  
    }  
}
