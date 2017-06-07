package com.whl.util.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 
 * description: 如何使用？这个mapper被自定义业务mapper继承
 * 
 * @author whling
 * @date 2017年6月7日 下午3:00:44
 *
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
	// TODO
	// FIXME 特别注意，该接口不能被扫描到，否则会出错
}
