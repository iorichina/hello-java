package com.iorichina.websocket;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

public interface SocketFace {
	@OnOpen
	public abstract void onOpen(Session session);

	@OnClose
	public abstract void onClose(CloseReason reason);

	@OnMessage
	public abstract void onMessage(String message);

	@OnError
	public abstract void onError(Throwable t) throws Throwable;

	public abstract void sendTo(Session session, SocketMessageFace msg);

	public abstract void broadcast(SocketMessageFace msg);

	public abstract void closeSession(Session session);
}
