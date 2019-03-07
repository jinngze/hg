package com.example.moni.data.bean;

import java.util.List;

public class ZhanBean {


    /**
     * code : 0
     * data : [{"aid":1,"createtime":"2018-12-17T14:53:49","icon":"http://www.zhaoapi.cn/images/quarter/ad1.png","title":"第十三界瑞丽模特大赛","type":0,"url":"http://m.mv14449315.icoc.bz/index.jsp"},{"aid":2,"createtime":"2018-12-17T14:53:49","icon":"http://www.zhaoapi.cn/images/quarter/ad2.png","title":"文化艺术节","type":0,"url":"http://m.mv14449315.icoc.bz/index.jsp"},{"aid":3,"createtime":"2018-12-17T14:53:49","icon":"http://www.zhaoapi.cn/images/quarter/ad3.png","title":"直播封面标准","type":0,"url":"http://m.mv14449315.icoc.bz/index.jsp"},{"aid":4,"createtime":"2018-12-17T14:53:49","icon":"http://www.zhaoapi.cn/images/quarter/ad4.png","productId":"1","title":"人气谁最高，金主谁最豪气","type":1,"url":""}]
     * msg :
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * aid : 1
         * createtime : 2018-12-17T14:53:49
         * icon : http://www.zhaoapi.cn/images/quarter/ad1.png
         * title : 第十三界瑞丽模特大赛
         * type : 0
         * url : http://m.mv14449315.icoc.bz/index.jsp
         * productId : 1
         */

        private int aid;
        private String createtime;
        private String icon;
        private String title;
        private int type;
        private String url;
        private String productId;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }
    }
}
