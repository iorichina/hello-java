package com.iorichina.chatroom.endpoint;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

public class ChatSocketServlet extends WebSocketServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5786570991965478835L;
	private static ArrayList<MyMessageInbound> mmiList = new ArrayList<MyMessageInbound>();

	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,
			HttpServletRequest request) {
		return new MyMessageInbound();
	}

	private class MyMessageInbound extends MessageInbound {
		WsOutbound myoutbound;

		@Override
		protected void onOpen(WsOutbound outbound) {
			try {
				System.out.println("Open Client.");

				this.myoutbound = outbound;
				mmiList.add(this);

				outbound.writeTextMessage(CharBuffer.wrap("Hello!"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void onClose(int status) {
			System.out.println("Close Client.");

			mmiList.remove(this);
		}

		@Override
		protected void onTextMessage(CharBuffer message) throws IOException {
			System.out.println("Accept Message : " + message);

			for (MyMessageInbound mmib : mmiList) {
				CharBuffer buffer = CharBuffer.wrap(message);
				mmib.myoutbound.writeTextMessage(buffer);
				mmib.myoutbound.flush();
			}
		}

		@Override
		protected void onBinaryMessage(ByteBuffer arg0) throws IOException {

		}

	}

}
