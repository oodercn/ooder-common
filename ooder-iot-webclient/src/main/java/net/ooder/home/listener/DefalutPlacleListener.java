package  net.ooder.home.listener;

import  net.ooder.annotation.EsbBeanAnnotation;
import  net.ooder.common.EsbFlowType;
import  net.ooder.home.event.PlaceEvent;
import  net.ooder.home.event.PlaceListener;
import  net.ooder.iot.HomeException;
import  net.ooder.iot.Place;
import  net.ooder.iot.ct.CtIotCacheManager;

@EsbBeanAnnotation(id = "DefalutPlacleListener", name = "默认家居服务监听器", expressionArr = "DefalutPlacleListener()",flowType = EsbFlowType.listener,desc = "默认家居服务监听器")

public class DefalutPlacleListener implements PlaceListener {



	@Override
	public void placeCreate(PlaceEvent event) throws HomeException {
		CtIotCacheManager appEngine=CtIotCacheManager.getInstance();
		Place place=(Place) event.getSource();
		appEngine.createArea("客厅", place.getPlaceid());
		appEngine.createArea("主卧", place.getPlaceid());
		appEngine.createArea("次卧", place.getPlaceid());
		appEngine.createArea("厨房", place.getPlaceid());
		appEngine.createArea("书房", place.getPlaceid());
	}

	@Override
	public void placeRemove(PlaceEvent event) throws HomeException {

	}

	@Override
	public void areaAdd(PlaceEvent event) throws HomeException {

	}

	@Override
	public void areaRemove(PlaceEvent event) throws HomeException {

	}

	@Override
	public void gatewayAdd(PlaceEvent event) throws HomeException {

	}

	@Override
	public void gatewayRemove(PlaceEvent event) throws HomeException {

	}
}
