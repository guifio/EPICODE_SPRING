package com.example.model;

import java.time.LocalDate;

public class MessageDTO {

	private String title;
	private String content;
	private LocalDate sendDate;
	private Long fromUser;
	private Long toUser;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDate getSendDate() {
		return sendDate;
	}
	public void setSendDate(LocalDate sendDate) {
		this.sendDate = sendDate;
	}
	public Long getFromUser() {
		return fromUser;
	}
	public void setFromUser(Long fromUser) {
		this.fromUser = fromUser;
	}
	public Long getToUser() {
		return toUser;
	}
	public void setToUser(Long toUser) {
		this.toUser = toUser;
	}
	
	
	
	
}
