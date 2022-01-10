package com.untucapital.usuite.utg.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "files")
public class DatabaseFile extends AbstractEntity{
	//@Id
	//@GeneratedValue(generator = "uuid")
	//@GenericGenerator(name = "uuid", strategy = "uuid2")
	//private String id;

	private String fileName;

	private String fileType;

	private String fileDescription;

	@NotNull
	@JoinColumn(nullable = false)
	private String userId;

	@Lob
	private byte[] data;

	public DatabaseFile() {

	}

	public DatabaseFile(String fileName, String fileType,String fileDescription,String userId, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileDescription = fileDescription;
		this.userId = userId;
		this.data = data;
	}

	//public String getId() {
		//return id;
	//}

	public String getFileName() {
		return fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public String getFileDescription() {
		return fileDescription;
	}
	public byte[] getData() {
		return data;
	}

	//public void setId(String id) {
		//this.id = id;
	//}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}
}
