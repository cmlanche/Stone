package com.stone.core.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stone.core.msg.IMessage;
import com.stone.core.msg.MessageParseException;

/**
 * The game decoder;<br>
 * when i write this decoder, i have made a lots of stupid mistakes, so i named
 * this as StupidGameDecoder.
 * 
 * @author crazyjohn
 *
 */
public class StupidGameDecoder implements ProtocolDecoder {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IoBuffer readBuffer = IoBuffer.allocate(IMessage.DECODE_MESSAGE_LENGTH).setAutoExpand(true);
	private IMessageFactory messageFactory;

	public StupidGameDecoder(IMessageFactory messageFactory) {
		this.messageFactory = messageFactory;
	}

	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		// decode
		readBuffer.put(in);
		readBuffer.flip();
		while (true) {
			// 是否足够消息头的长度?
			if (readBuffer.remaining() < IMessage.HEADER_SIZE) {
				break;
			}
			// 读出消息包的长度
			short messageLength = readBuffer.getShort(readBuffer.position());
			if (messageLength <= 0) {
				break;
			}
			short messageType = readBuffer.getShort(readBuffer.position() + 2);
			logger.debug(String.format("===============================Pos: %d, type: %d, length: %d", this.readBuffer.position(), messageType,
					messageLength));
			if (readBuffer.remaining() < messageLength) {
				break;
			}
			// 读出消息包
			byte[] datas = new byte[messageLength];
			// readBuffer.flip();
			readBuffer.get(datas);
			IMessage aMessage = messageFactory.createMessage(messageType);
			if (aMessage != null) {
				try {
					IoBuffer aMessageBuffer = IoBuffer.wrap(datas).setAutoExpand(true);
					aMessage.setBuffer(aMessageBuffer);
					// read
					aMessage.read();
					// write to out stream
					out.write(aMessage);
				} catch (MessageParseException e) {
					// handle msg parse exception
					// just log???
					logger.error(String.format("Parse message error, type: %d", messageType), e);
				}
			}
		}
		// compact
		if (readBuffer.hasRemaining()) {
			readBuffer.compact();
		} else {
			readBuffer.clear();
		}
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
		// do nothing

	}

	@Override
	public void dispose(IoSession session) throws Exception {
		this.readBuffer.free();
	}

}
