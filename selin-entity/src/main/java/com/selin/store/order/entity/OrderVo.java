package com.selin.store.order.entity;

import java.util.List;

/**
 * @author 模版生成 <br/>
 *         表名： w_order <br/>
 *         描述：订单表 <br/>
 */
public class OrderVo extends Order {

	private List<OrderVo> orderList;

	public OrderVo() {
		super();
	}

	public OrderVo(Long id) {
		super();
		this.id = id;
	}

	public List<OrderVo> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderVo> orderList) {
		this.orderList = orderList;
	}

}
