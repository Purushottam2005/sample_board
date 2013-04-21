package com.jkonury.www.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;

import com.jkonury.www.board.model.Board;
import com.jkonury.www.board.model.BoardFile;

public class FileUtil {
	public static void filDown(HttpServletRequest request, HttpServletResponse response, String filePath, String copyFileName, String fileName) throws Exception {
		System.out.println(filePath);
		System.out.println(copyFileName);
		System.out.println(fileName);
		try {
			File file = new File(filePath + copyFileName);
			if(file.exists() && file.isFile()){
				response.setContentType("application/octet-stream; charset=utf-8");
				response.setContentLength((int) file.length());
				String browser = getBrowser(request);
				String disposition = getDisposition(fileName, browser);
				
				System.out.println(disposition);
				
				response.setHeader("Content-Disposition", disposition);
				response.setHeader("Content-Transfer-Encoding", "binary");
				OutputStream out = response.getOutputStream();
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					FileCopyUtils.copy(fis, out);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (fis != null) {
						try {
							fis.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}
	
	private static String getDisposition(String filename, String browser) throws UnsupportedEncodingException {
		String dispositionPrefix = "attachment;filename=";
		String encodedFilename = null;
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new RuntimeException("Not supported browser");
		}

		return dispositionPrefix + encodedFilename;
	}
	
	public static void fileUpload(HttpServletRequest request, Board board, BoardFile boardFile) {
		if (board.getFile() != null && board.getFile().getOriginalFilename().trim().length() != 0) {
			/**
			 * 1.파일 저장 처리
			 */
//			String defaultPath = request.getContextPath().getRealPath("/") + "upload" + File.separator + "oto" + File.separator;
			String defaultPath = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator ;
			File uploadPath = new File(defaultPath +  board.getUserSeq() );
			
			System.out.println("path = " + request.getSession().getServletContext().getRealPath("/")); 
			
			if(!uploadPath.exists()) {
				uploadPath.mkdirs();	
			}

//			File file = new File(uploadPath, board.getFile().getOriginalFilename());

			
			String copyFileName = UUID.randomUUID() + board.getFile().getOriginalFilename();
			String orgFileName = board.getFile().getOriginalFilename();
			String ext = orgFileName;
			ext = ext.substring(ext.lastIndexOf(".") + 1);

//			File file = new File(uploadPath, board.getFile().getOriginalFilename());
			
			File file = new File(uploadPath, copyFileName);
			
			int parseFileSize = (int) board.getFile().getSize();
			try {
				board.getFile().transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
//			#boardSeq#, #userSeq#, #filePath#, #orgFileName#, #copyFileName#, #fileSize#, #fileExt#			
			boardFile.setBoardSeq(board.getBoardSeq());
			boardFile.setUserSeq(board.getUserSeq());
			boardFile.setFilePath(uploadPath.getAbsolutePath());
			boardFile.setOrgFileName(orgFileName);
			boardFile.setCopyFileName(copyFileName);
			boardFile.setFileSize(parseFileSize);
			boardFile.setFileExt(ext);
		}
	
	}
	
}
