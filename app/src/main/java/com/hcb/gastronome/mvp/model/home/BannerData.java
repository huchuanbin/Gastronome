package com.hcb.gastronome.mvp.model.home;

/**
 * Created by huchuanbin on 16/4/6.
 */
public class BannerData {

    /**
     * createdAt : 2016-04-06 09:23:49
     * objectId : bi1CZZZa
     * pic : {"__type":"File","filename":"96936e58e2c.jpg","group":"group1","url":"M03/1A/19/oYYBAFcE0qmAZ04SAAFEuDiyH00274.jpg"}
     * updatedAt : 2016-04-06 21:14:41
     * url : https://www.baidu.com
     */

    private String createdAt;
    private String objectId;
    /**
     * __type : File
     * filename : 96936e58e2c.jpg
     * group : group1
     * url : M03/1A/19/oYYBAFcE0qmAZ04SAAFEuDiyH00274.jpg
     */

    private PicBean pic;
    private String updatedAt;
    private String url;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public PicBean getPic() {
        return pic;
    }

    public void setPic(PicBean pic) {
        this.pic = pic;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class PicBean {
        private String __type;
        private String filename;
        private String group;
        private String url;

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
