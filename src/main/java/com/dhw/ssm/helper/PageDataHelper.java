package com.dhw.ssm.helper;

import lombok.Data;

import java.util.*;

/**
 * 分页参数，分页结果集等
 * @author
 */
@Data
public class PageDataHelper {

    /**
     * 页码
     */
    private int page;

    /**
     * 每页显示条数
     */
    private int pageSize;

    public PageDataHelper() {
    }

    public PageDataHelper(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }


    public static PageDataHelper pagingOracleVersion(int page, int pageSize) {
        return new PageDataHelper((page - 1) * pageSize, page * pageSize);
    }

    public static PageDataHelper pagingMySQLVersion(int page, int pageSize) {
        return new PageDataHelper((page - 1) * pageSize, pageSize);
    }

    public static Map<String, Object> pagingEncapsulation(List<?> data, int count) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", data);
        return map;
    }
}
