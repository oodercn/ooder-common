package  net.ooder.iot.client;

import  net.ooder.command.Command;
import  net.ooder.config.ResultModel;
import  net.ooder.iot.json.device.GWUser;
import  net.ooder.iot.json.device.Gateway;
import  net.ooder.iot.json.device.GatewayErrorReport;

import java.util.List;

/**
 * 网关账户安全管理
 * 访问无需登录，直接可访问
 * @author wenzhang
 *
 */
public interface GWAccountWebService {
   
	/**
	 * 网关注册
	 * @param gateway
	 * @return
	 */
	ResultModel<Gateway> register(Gateway gateway) ;
	
	/**
	 * 网关激活
	 * @param gateway
	 * @return
	 */
	ResultModel<Gateway> activate(Gateway gateway) ;
	
	/**
	 * 网关登录
	 * @param userInfo
	 * @return
	 */
	ResultModel<GWUser> login(GWUser userInfo) ;
	
	
	
	
	
	/**
	 * 网关登录
	 * @param gwIeee
	 * @return
	 */
	ResultModel<GWUser> gwLogin(String gwIeee) ;
	
	

	/**
	 * 退出
	 * @return
	 */
	ResultModel<Boolean> logout() ;
	
	
	
	
	/**
	 * 网关错误报告
	 * @param errorPort
	 * @return
	 */
	ResultModel<List<Command>> gatewayErrorReport(GatewayErrorReport errorPort);
	
	


}
