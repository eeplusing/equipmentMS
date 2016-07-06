package com.moutum.equ.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/************************************************************************************
 * @Title        : DeCode.java
 * @Description : 二维码编码解码工具类
 * @Author       : BianWeiqing
 * @DateTime     : 2015年3月10日 下午4:24:41
 * @Copyright    : 2015 Moutum All Rights Reserved
 * @version      : V1.0
 ************************************************************************************/
public class DeCode
{

    /********************************************************************************
     * 二维码编码
     * @param content 二维码内容
     * @param imgPath 生成的二维码文件路径
     * @param width 二维码图片的宽
     * @param height 二维码图片的高
     * @return
     ********************************************************************************/
    public static boolean encode(String content, String imgPath, int width, int height)
    {
        try
        {
            BitMatrix byteMatrix;
            byteMatrix = new MultiFormatWriter().encode(new String(content.getBytes("UTF8"), "iso-8859-1"), BarcodeFormat.QR_CODE, width, height);
            File file = new File(imgPath);
            File pf = file.getParentFile();
            if(!pf.exists())
            {
                pf.mkdirs();
            }
            if(file.exists())
            {
                file.delete();
            }
            MatrixToImageWriter.writeToFile(byteMatrix, "png", file);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /********************************************************************************
     * 二维码解码
     * @param imgPath 二维码文件路径
     * @return
     ********************************************************************************/
    public static String decode(String imgPath)
    {
        try
        {
            File file = new File(imgPath);
            BufferedImage image;
            try
            {
                image = ImageIO.read(file);
                if (image == null)
                {
                    System.out.println("Could not decode image");
                }
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result;
                Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
                hints.put(DecodeHintType.CHARACTER_SET, "UTF8");
                result = new MultiFormatReader().decode(bitmap, hints);
                
                return result.getText();

            }
            catch (IOException ioe)
            {
                System.out.println(ioe.toString());
            }
            catch (ReaderException re)
            {
                System.out.println(re.toString());
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    /********************************************************************************
     * 打印文件
     * @param file 文件路径
     * @return
     ********************************************************************************/
    public static boolean print(File file)
    {
        try
        {
            HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob job = defaultService.createPrintJob(); // 创建打印作业
            Object fis = new FileInputStream(file); // 构造待打印的文件流
            DocAttributeSet das = new HashDocAttributeSet();
            Doc doc = new SimpleDoc(fis, flavor, das);
            job.print(doc, pras);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public static void main(String[] args)
    {
        encode("EQU_0408", "/D:/home/decode/EQU_040801.png" , 15, 15);
//        System.out.println(decode("D:/home/decode/EQU_00268.png"));
    }
}