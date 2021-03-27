package com.iorichina.chatroom.endpoint;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iorichina.chatroom.message.Message;
import com.iorichina.websocket.SocketEndPoint;
import com.iorichina.websocket.SocketMessageFace;

@ServerEndpoint(value = "/websocket/chat")
public class ChatWebSocketServlet extends SocketEndPoint {
	public static final Logger log = LoggerFactory
			.getLogger(ChatWebSocketServlet.class);

	private String id;

	public String getId() {
		return id;
	}

	public ChatWebSocketServlet() {
	}

	@Override
	@OnOpen
	public void onOpen(Session session) {
		if (session.getRequestParameterMap().containsKey("id")) {
			this.id = session.getRequestParameterMap().get("id").get(0);

			super.onOpen(session);

			this.log(session.getRequestParameterMap().toString() + "[opened]",
					null);
		} else {
			try {
				this.log("connection close: param id is requied", null);
				session.close(new CloseReason(CloseCodes.GOING_AWAY,
						"param id is requied"));
			} catch (IOException e) {
				this.log(ChatWebSocketServlet.class.getName()
						+ " onClose error", e);
			}
		}
	}

	@Override
	@OnMessage
	public void onMessage(String message) {
		super.onMessage(message);
	}

	@Override
	public void sendTo(Session session, SocketMessageFace msg) {
		super.sendTo(session, msg);
	}

	@Override
	protected String genConnectionKey(Session session) {
		return this.id;
	}

	@Override
	protected SocketMessageFace parseMessage(String message) {
		return new Message(this, message);
	}

	@Override
	protected void log(Exception e) {
		log.error(" Web Socket Servlet Error", e);
	}

	@Override
	protected void log(String msg, Exception e) {
		log.info(msg, e);
	}
}
