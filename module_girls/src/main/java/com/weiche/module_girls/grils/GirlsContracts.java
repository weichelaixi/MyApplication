package com.weiche.module_girls.grils;

import com.weiche.module_common.BasePresenter;
import com.weiche.module_common.BaseView;
import com.weiche.module_girls.grils.bean.Girls;

import java.util.List;

/**
 * Created by ${chewei} on 2018/6/29.
 * params:2018/6/29
 */

public interface GirlsContracts {

    interface View extends BaseView<Persenter>{
        /**
         * View 的存活状态
         *
         * @return true or false
         */
        boolean isActive();
        void refresh(List<Girls> girlsList);
        void loadMore(List<Girls> girlsList);
        void showError();
        void showNormol();

    }

    interface Persenter extends BasePresenter{
        void getGirls(int page, int size, boolean isRefresh);
    }
}
