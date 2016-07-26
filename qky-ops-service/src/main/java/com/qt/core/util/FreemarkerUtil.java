package com.qt.core.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	
	private static Log logger = LogFactory.getLog(FreemarkerUtil.class);
	
	private static FreemarkerUtil util;
	private static Configuration cfg;
	private FreemarkerUtil() {
	}
	
	public static FreemarkerUtil getInstance(String pname) {
		if(util==null) {
			cfg = new Configuration(Configuration.VERSION_2_3_21);
			cfg.setClassForTemplateLoading(FreemarkerUtil.class, pname);
			util = new FreemarkerUtil();
		}
		return util;
	}
	
	private Template getTemplate(String fname) {
		try {
			return cfg.getTemplate(fname);
		} catch (IOException e) {
			logger.error(null,e);
		}
		return null;
	}
	/**
	 * 通过标准输出流输出模板的结果
	 * @param root 数据对象
	 * @param fname 模板文件
	 * @param out 
	 */
	public void sprint(Map<String,Object> root,String fname, Writer out) {
		try {
			getTemplate(fname).process(root, out);
		} catch (TemplateException e) {
			logger.error(null,e);
		} catch (IOException e) {
			logger.error(null,e);
		}
	}
	
	/**
	 * 通过标准输出流输出模板的结果
	 * @param root 数据对象
	 * @param fname 模板文件
	 */
	public void sprint(Map<String,Object> root,String fname) {
		try {
			getTemplate(fname).process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			logger.error(null,e);
		} catch (IOException e) {
			logger.error(null,e);
		}
	}
	
	/**
	 * 通过标准输出流输出模板的结果
	 * @param root 数据对象
	 * @param fname 模板文件
	
	public void print(Map<String,Object> root,String fname) {
		try {
			BufferedWriter bw = new BufferedWriter(out);
			getTemplate(fname).process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			logger.error(null,e);
		} catch (IOException e) {
			logger.error(null,e);
		}
	} */
	
	/**
	 * 基于文件的输出
	 * @param root
	 * @param fname
	 * @param outpath
	 */
	public void fprint(Map<String,Object> root,String fname,String outpath) {
		try {
			getTemplate(fname).process(root, new FileWriter(outpath));
		} catch (TemplateException e) {
			logger.error(null,e);
		} catch (IOException e) {
			logger.error(null,e);
		}
	}
}
