package com.qt.core.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static JsonUtil ju;
	private static JsonFactory jf;
	private static ObjectMapper mapper;
	private JsonUtil(){}
	
	public static JsonUtil getInstance() {
		if(ju==null) ju = new JsonUtil();
		return ju;
	}
	
	public static ObjectMapper getMapper() {
		if(mapper==null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}
	
	public static JsonFactory getFactory() {
		if(jf==null) jf = new JsonFactory();
		return jf;
	}
	
	public String obj2json(Object obj) {
		StringWriter out = new StringWriter();
		this.obj2json(obj, out);
		return out.toString();
	}
	
	public void obj2json(Object obj, Writer writer) {
		JsonGenerator jg = null;
		try {
			jf = getFactory();
			mapper = getMapper();
			jg = jf.createGenerator(writer);
			mapper.writeValue(jg, obj);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(jg!=null) jg.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Object json2obj(String json,Class<?> clz) {
		try {
			mapper = getMapper();
			return mapper.readValue(json,clz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void outJson(String key,String value,Writer writer){
		JSONObject obj = new JSONObject();
		obj.put(key, value);
		obj.write(writer);
	}
	
	public void outJson(String key,Map value,Writer writer){
		JSONObject obj = new JSONObject();
		obj.put(key, value);
		obj.write(writer);
	}
}
