package  net.ooder.iot.api.inner;

import  net.ooder.annotation.JLuceneIndex;
import  net.ooder.common.Condition;
import  net.ooder.config.ListResultModel;
import  net.ooder.config.ResultModel;
import  net.ooder.home.query.HomeConditionKey;
import  net.ooder.home.query.IOTConditionKey;
import  net.ooder.iot.Device;
import  net.ooder.iot.DeviceEndPoint;
import  net.ooder.iot.ZNode;

import java.util.List;
import java.util.Set;

public interface DeviceSearchService {


    public ListResultModel<List<ZNode>> loadZNodeList(String[] strings);

    public ListResultModel<List<Device>> loadDeviceList(String[] strings);

    public ListResultModel<List<DeviceEndPoint>> loadDeviceEndPointList(String[] strings);

    public ResultModel<ZNode> findZNodeByIeee(String ieee, String userID);

    public ListResultModel<Set<String>> findGWDevicesByFactory(String factoryName);


    public ListResultModel<Set<String>> findPlace(Condition<HomeConditionKey,JLuceneIndex> condition);

    public ListResultModel<Set<String>> findArea(Condition<HomeConditionKey,JLuceneIndex> condition);

    public ListResultModel<Set<String>> findDevice(Condition<IOTConditionKey,JLuceneIndex> condition);

    public ListResultModel<Set<String>> findEndPoint(Condition<IOTConditionKey,JLuceneIndex> condition);

    public ListResultModel<Set<String>> findZNode(Condition<IOTConditionKey,JLuceneIndex> condition);

    public ListResultModel<Set<String>> findAlarm(Condition<HomeConditionKey,JLuceneIndex> condition);

    public ListResultModel<Set<String>> findScene(Condition<HomeConditionKey,JLuceneIndex> condition);

}
