package com.xzsd.pc.topOfColumn.dao;

import com.xzsd.pc.topOfColumn.entity.TopOfColumnInfo;

public interface TopOfColumnDao {

    /**
     * 顶部栏接口
     * @param userId
     * @return
     */
    TopOfColumnInfo getTopOfColumn(String userId);
}
