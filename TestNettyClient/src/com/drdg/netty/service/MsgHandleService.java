package com.drdg.netty.service;

import io.netty.channel.ChannelHandlerContext;

import com.drdg.netty.agreement.MsgAgreement;
import com.drdg.netty.bean.InformationPacket;
import com.drdg.netty.bean.InformationPacket.Group.ServerConnectEnum;
import com.drdg.netty.bean.UserBean;
import com.drdg.netty.control.CoreBusinessControl;

public class MsgHandleService {
	
	public static ChannelHandlerContext channel;
	public static CoreBusinessControl coreBusinessControl;
	private static MsgAgreement msgAgree = new MsgAgreement(true);
	
	static public void doMsgForShunt(InformationPacket.Group group){
		
		switch (group.getMsgEnum().getNumber()) {
		//连接服务端反馈
		case InformationPacket.MsgEnum.ReuqestToConnect_VALUE:
			
			NoticeConnectState(group.getServerConnectEnum());
			
			break;
		case InformationPacket.MsgEnum.CheckToLogin_VALUE:
			
			
			break;
		case InformationPacket.MsgEnum.ChatOneToOne_VALUE:
			
			
			break;
		case InformationPacket.MsgEnum.ChatOneToAll_VALUE:
			
			
			break;
		default:
			break;
		}
		
	}
	
	/**
	 * 通知连接状态
	 * @param serverConnectEnum
	 */
	private static void NoticeConnectState(ServerConnectEnum serverConnectEnum) {
		System.out.println("发送登录信息"+serverConnectEnum);
		if(serverConnectEnum == InformationPacket.Group.ServerConnectEnum.Success){
			coreBusinessControl.doCheckLogin();
			System.out.println("发送登录信息");
		}else{
			
		}
		
		
	}

	static public void doCheckLogin(UserBean user){
		channel.writeAndFlush(msgAgree.doGetLoginInfoPacket(user.getUserName(), user.getUserPwd()));
	}
	
	
}