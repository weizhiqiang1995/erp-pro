package com.skyeye.common.util;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Base64.Decoder;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFReport {
	static Document document = new Document();// 建立一个Document对象

	private static Font keyfont;// 设置字体大小
	private static Font textfont;// 设置字体大小
	
	@Value("${IMAGES_PATH}")
	private static String tPath;

	public PDFReport(File file) {
		BaseFont bfChinese;
		try {
			URL url = PDFReport.class.getResource("simyou.TTF");
			bfChinese = BaseFont.createFont(url.getPath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			new Font(bfChinese, 10, Font.BOLD);
			keyfont = new Font(bfChinese, 8, Font.BOLD);// 设置字体大小
			textfont = new Font(bfChinese, 8, Font.NORMAL);// 设置字体大小
		} catch (Exception e) {
			e.printStackTrace();
		}
		document = new Document();
		document.setPageSize(PageSize.A4);// 设置页面大小
		try {
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static int maxWidth = 520;

	public static PdfPCell createCell(String value, com.itextpdf.text.Font font, int align) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	public static PdfPCell createCell(String value, com.itextpdf.text.Font font) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	public PdfPCell createCell(String value, com.itextpdf.text.Font font, int align, int colspan) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		cell.setPhrase(new Phrase(value, font));
		return cell;
	}

	public static PdfPCell createCell(String value, com.itextpdf.text.Font font, int align, int colspan, boolean boderFlag) {
		PdfPCell cell = new PdfPCell();
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(align);
		cell.setColspan(colspan);
		cell.setPhrase(new Phrase(value, font));
		cell.setPadding(3.0f);
		if (!boderFlag) {
			cell.setBorder(0);
			cell.setPaddingTop(15.0f);
			cell.setPaddingBottom(8.0f);
		}
		return cell;
	}

	public static PdfPTable createTable(int colNumber) {
		PdfPTable table = new PdfPTable(colNumber);
		try {
			table.setTotalWidth(maxWidth);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setBorder(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	public PdfPTable createTable(float[] widths) {
		PdfPTable table = new PdfPTable(widths);
		try {
			table.setTotalWidth(maxWidth);
			table.setLockedWidth(true);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setBorder(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}

	public PdfPTable createBlankTable() {
		PdfPTable table = new PdfPTable(1);
		table.getDefaultCell().setBorder(0);
		table.addCell(createCell("", keyfont));
		table.setSpacingAfter(20.0f);
		table.setSpacingBefore(20.0f);
		return table;
	}

	/**
	 * 
	     * @Title: generatePDF
	     * @Description: pdf导出
	     * @param @param fileName
	     * @param @param titleName
	     * @param @param list
	     * @param @param keys
	     * @param @param columnNames
	     * @param @param request
	     * @param @param response
	     * @param @param filePath
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	public static void generatePDF(String fileName, String titleName, List<Map<String, Object>> list, String []keys, 
    		String columnNames[], HttpServletRequest request, HttpServletResponse response, String filePath) throws Exception {
		//设置图片
		String imgUrl = request.getParameter("base64Info");
		if (!ToolUtil.isBlank(imgUrl)) {
			String[] imgUrlArr = imgUrl.split("base64,");// 拆分base64编码后部分
			Decoder decoder = Base64.getDecoder();
	        byte[] buffer = decoder.decode(imgUrlArr[1]);
	        String picPath = tPath + "\\images\\upload\\";
	        File pack = new File(picPath);// 图片文件
	        if(!pack.isDirectory())//目录不存在 
				pack.mkdirs();//创建目录
	        picPath += ToolUtil.getSurFaceId() + ".png";
			File file = new File(picPath);// 图片文件

			try {
				// 生成图片
				OutputStream out = new FileOutputStream(file);// 图片输出流
				out.write(buffer);
				out.flush();// 清空流
				out.close();// 关闭流
				ByteArrayOutputStream outStream = new ByteArrayOutputStream(); // 将图片写入流中
				BufferedImage bufferImg = ImageIO.read(new File(picPath));
				ImageIO.write(bufferImg, "PNG", outStream);
				//
				Image png = Image.getInstance(picPath);
				png.setAlignment(Image.ALIGN_CENTER);
				png.scaleAbsolute(530, 160);
				document.add(png);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (file.exists()) {
				file.delete();// 删除图片
			}
		}
		
		//设置表格数据
		PdfPTable table = createTable(columnNames.length);
		table.addCell(createCell(titleName, keyfont, Element.ALIGN_LEFT, columnNames.length, false));
		for(short j = 0; j < columnNames.length; j++){
			table.addCell(createCell(columnNames[j], keyfont, Element.ALIGN_CENTER));
		}
		
		//设置每行每列的值
        for (short i = 0; i < list.size(); i++) {
            // 在row行上创建一个方格
            for(short j = 0; j < keys.length; j++){
            	table.addCell(createCell(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString(), textfont));
            }
        }
		
		document.add(table);
		document.close();
		
		//输出
		InputStream in = null;
        OutputStream out = null;
        File file = new File(filePath);
        /* 读取要下载的文件，保存到文件输入流 */
        in = new FileInputStream(file);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".pdf").getBytes(), "iso-8859-1"));
        try {
        	/* 创建缓冲输出流 */
            out = new BufferedOutputStream(response.getOutputStream());
            /* 定义缓冲区大小，开始读写 */
            byte buffer[] = new byte[2048];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            /* 刷新缓冲区，写出 */
            out.flush();
        } catch (final IOException e) {
            throw e;
        } finally {
            if (in != null)
            	in.close();
            if (out != null)
            	out.close();
        }
        
        if (file.exists()) {
			file.delete();// 删除pdf
		}
		
	}

}
