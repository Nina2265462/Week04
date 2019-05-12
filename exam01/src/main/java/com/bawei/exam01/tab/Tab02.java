package com.bawei.exam01.tab;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.exam01.R;
import com.bawei.exam01.base.BaseTabFragment;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Tab02 extends BaseTabFragment {

    private WebView web;

    @Override
    public int tabLayout() {
        return R.layout.tab02;
    }

    @Override
    public void tabView() {
        web = tabId(R.id.web);
    }

    @Override
    public void tabData() {
        web.loadUrl("http://image.baidu.com/search/detail?z=0&word=%E6%91%84%E5%BD%B1%E5%B8%88%E7%89%9F%E6%B5%B7%E6%B3%A2&hs=0&pn=53&spn=0&di=0&pi=61714251389&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cs=2660695592%2C1390067504&os=&simid=&adpicid=0&lpn=0&fm=&sme=&cg=&bdtype=-1&oriquery=&objurl=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Feac4b74543a98226b2426a498482b9014b90ebd9.jpg&fromurl=&gsm=5a0000000000005a&catename=pcindexhot&islist=&querylist=1");
        web.setWebViewClient(new WebViewClient());
    }

    @Override
    public void tabEvent() {

    }
}
