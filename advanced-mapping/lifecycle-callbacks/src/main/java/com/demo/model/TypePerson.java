package com.demo.model;

public enum TypePerson {

	FISICAL() {
		
		@Override
		public String format(String document) {
			return document.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	
	}, 
	
	LEGAL() {
		
		@Override
		public String format(String document) {
			return document.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-");
		}
		
	};

	public static String removeFormat(String document) {
		return document.replaceAll("\\.|/|-", "");
	}

	public abstract String format(String document);
	
}
