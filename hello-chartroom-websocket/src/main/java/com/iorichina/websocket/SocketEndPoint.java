package com.iorichina.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

public abstract class SocketEndPoint implements SocketFace {
	private Session session;
	private static final HashMap<String, SocketEndPoint> connections = new HashMap<String, SocketEndPoint>();

	public SocketEndPoint() {
	}

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		connections.put(this.genConnectionKey(session), this);
	}

	@OnClose
	public void onClose(CloseReason reason) {
		this.log("user close session", null);
	}

	@OnMessage
	public void onMessage(String message) {
		this.log("onMessage:" + message, null);

		SocketMessageFace msg = this.parseMessage(message);
		
		if (msg.isSendToOne()) {
			SocketEndPoint connection = connections.get(msg.getOneKey());
			this.sendTo(connection.session, msg);
		} else {
			this.broadcast(msg);
		}
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
	}

	public void sendTo(Session session, SocketMessageFace msg) {
		this.log("sendTo:" + session.getRequestParameterMap().toString(), null);

		Basic bc = session.getBasicRemote();
		String message = msg.getMessage();
		this.log("bc " + message, null);

		try {
			bc.sendText(message);
		} catch (IOException e) {
			this.log("IOException", e);
			// this.closeSession(session);
		}
	}

	public void broadcast(SocketMessageFace msg) {
		this.log("broadcast:" + msg, null);

		Iterator<Session> it = this.session.getOpenSessions().iterator();
		while (it.hasNext()) {
			Session session = (Session) it.next();
			if (session.equals(this.session)) {
				continue;
			}
			this.sendTo(session, msg);
		}
	}

	public void closeSession(Session session) {
		if (session.isOpen()) {
			try {
				this.log("close Session", null);
				session.close();
			} catch (IOException e) {
				this.log(e);
			}
		}
	}

	protected abstract String genConnectionKey(Session session);

	protected abstract SocketMessageFace parseMessage(String message);

	protected abstract void log(Exception e);

	protected abstract void log(String msg, Exception e);
}
