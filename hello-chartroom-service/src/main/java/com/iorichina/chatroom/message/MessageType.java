package com.iorichina.chatroom.message;

public enum MessageType {
	MTYPE_TO(101, "send to", "发送到");
	private int code;
	private String title;
	private String title_cn;

	private MessageType(int code, String title, String title_cn) {
		this.code = code;
		this.title = title;
		this.title_cn = title_cn;
	}

	public int getCode() {
		return this.code;
	}

	public String getTitle() {
		return this.title;
	}

	public String getTitleCn() {
		return this.title_cn;
	}

	public static MessageType find(int type) {
		for (MessageType v : values()) {
			if (type == v.getCode()) {
				return v;
			}
		}

		return null;
	}
}
