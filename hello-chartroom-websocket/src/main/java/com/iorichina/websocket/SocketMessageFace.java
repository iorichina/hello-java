package com.iorichina.websocket;

public interface SocketMessageFace {

	public abstract SocketMessageFace parse(String message);

	public abstract String getMessage();

	public abstract boolean isSendToOne();

	public abstract String getOneKey();
}
