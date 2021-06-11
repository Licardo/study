package com.liepu.fluttermix;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @Author: liepu
 * @CreateDate: 2021/5/17 1:52 PM.
 * @Description: 描述具体内容
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/17 1:52 PM.
 * @UpdateRemark: 更新说明
 */
public class MyViewModel extends ViewModel {
    public MutableLiveData<String> nameLiveData = new MutableLiveData<>();
}
