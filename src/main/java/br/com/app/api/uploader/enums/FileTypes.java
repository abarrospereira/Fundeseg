package br.com.app.api.uploader.enums;

public enum FileTypes {

	/*
	 * To initialize the paths of those enumerators see file:
	 * ServiceRequestFileTypesInitializer.java
	 */
	ATTACHMENT(null), 
	PURCHASE_ORDER(null);

	private String path;

	FileTypes(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
