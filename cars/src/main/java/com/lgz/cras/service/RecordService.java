package com.lgz.cras.service;

import com.lgz.cras.pojo.Record;
import com.lgz.cras.util.ResBean;

import javax.servlet.http.HttpSession;

public interface RecordService {
    ResBean update(Record record, HttpSession session);
    ResBean getPage(int page,int limit,Record record,HttpSession session);
}
