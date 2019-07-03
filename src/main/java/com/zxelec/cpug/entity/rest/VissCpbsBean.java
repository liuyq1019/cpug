package com.zxelec.cpug.entity.rest;

import java.util.List;


public class VissCpbsBean<T> {
	private VissCpbsPage page;
	private VissCpbsResponseStatus responseStatus;
	private List<T> rows;

	public void setPage(VissCpbsPage page) {
		this.page = page;
	}

	public VissCpbsPage getPage() {
		return page;
	}

	public VissCpbsResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(VissCpbsResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
	
}
