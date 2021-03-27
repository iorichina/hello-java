package com.iorichina.chatroom.message;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.iorichina.chatroom.endpoint.ChatWebSocketServlet;
import com.iorichina.websocket.SocketMessageFace;

public class Message implements SocketMessageFace {
	public static final Logger log = LoggerFactory.getLogger(Message.class);
	public static final Gson gson = new Gson();
	private String message;
	private String to;
	private ChatWebSocketServlet from;

	public Message(ChatWebSocketServlet socket, String message) {
		this.from = socket;
		this.parse(message);
	}

	public SocketMessageFace parse(String message) {
		log.info("[parse]" + message);
		this.message = message;
		try {
			Matcher m = Pattern.compile("\\{(\\d{1,3}):(.+?)\\}").matcher(
					message);
			while (m.find()) {
				int type = Integer.parseInt(m.group(1));
				switch (MessageType.find(type)) {
				case MTYPE_TO:
					this.to = m.group(2);
					break;
				}
			}
		} catch (Exception e) {
			log.error("parse error", e);
		}
		log.info("[after find]" + this.toString());
		return this;
	}

	public String getMessage() {
		HashMap<String, String> msg = new HashMap<String, String>();
		msg.put("from", this.from.getId());
		if (null != this.to) {
			msg.put("to", this.to);
		}
		msg.put("message", this.message);
		String rs = "";

		log.info(msg.toString());
		try {
			rs = gson.toJson(msg);
		} catch (Exception e) {
			log.error("json build error", e);
			rs = this.message;
		}
		return rs;
	}

	public boolean isSendToOne() {
		if (null != this.to) {
			return true;
		}
		return false;
	}

	public String getOneKey() {
		return this.to;
	}

	public String toString() {
		return this.from.getId() + "|" + this.to + "|" + this.message;
	}
}
