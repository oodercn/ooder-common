/**
 * $RCSfile: JFieldBean.java,v $
 * $Revision: 1.0 $
 * $Date: 2025/08/25 $
 * <p>
 * Copyright (c) 2025 ooder.net
 * </p>
 * <p>
 * Company: ooder.net
 * </p>
 * <p>
 * License: MIT License
 * </p>
 */
package net.ooder.index.config.bean;

import org.apache.lucene.document.Field.Store;

public class JFieldBean implements JField{
    
    String id;
    String name;



    Boolean highlighter;
    String converter;  
    Object value;
   
    Store store;
    Class clazz;

    public JFieldBean(){

    }

    public Boolean getHighlighter() {
        return highlighter;
    }

    public void setHighlighter(Boolean highlighter) {
        this.highlighter = highlighter;
    }
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    
    @Override
    public void setClazz(Class clazz) {
	this.clazz=clazz;
    }

    @Override
    public Class getClazz(){
	return clazz;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConverter() {
        return converter;
    }

    public void setConverter(String converter) {
        this.converter = converter;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

 
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
