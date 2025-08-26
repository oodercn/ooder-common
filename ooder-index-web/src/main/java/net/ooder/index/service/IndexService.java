/**
 * $RCSfile: IndexService.java,v $
 * $Revision: 1.0 $
 * $Date: 2025/08/25 $
 * <p>
 * Copyright (c) 2025 ooder.net
 * </p>
 * <p>
 * Company: ooder.net
 * </p>
 * <p>
 * License: MIT License
 * </p>
 */
package net.ooder.index.service;

import net.ooder.common.ConditionKey;
import net.ooder.annotation.JLuceneIndex;
import net.ooder.common.Condition;
import net.ooder.config.ListResultModel;
import net.ooder.config.ResultModel;
import net.ooder.index.config.JLucene;

import java.util.List;

public interface IndexService {

    /**
     * 添加索引
     * @return
     */
    public  <T extends  JLucene> ResultModel<T> addIndex(T luceneBean);

    /**
     * 根据主键删除索引
     * @return
     */
    public  ResultModel<Boolean>  deleteIndex(JLucene luceneBean) ;

    /**
     * 删除索引
     * @return
     */
    public  <V extends JLuceneIndex,T extends ConditionKey>  ResultModel<Boolean>  deleteAllIndex(Condition<T, V> condition) ;


    /**
     * 根据条件查询
     * @param condition
     * @return
     */
    public <V extends JLuceneIndex,T extends ConditionKey> ListResultModel<List<V>> search(Condition<T, V> condition);
}
