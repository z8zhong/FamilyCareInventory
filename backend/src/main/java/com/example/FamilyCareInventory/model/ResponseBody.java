package com.example.FamilyCareInventory.model;

import java.io.Serializable;

public class ResponseBody implements Serializable {
    public Object data;
    public Meta meta;

    public ResponseBody(Object data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }

    public ResponseBody() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public static ResponseBody ok() {
        Meta meta = new Meta();
        meta.setMsg("成功");
        meta.setStatus(200);
        ResponseBody responseBody = new ResponseBody();
        responseBody.setMeta(meta);
        return responseBody;
    }

    public static ResponseBody notAuthorized() {
        Meta meta = new Meta();
        meta.setMsg("你没有权限");
        meta.setStatus(400);
        ResponseBody responseBody = new ResponseBody();
        responseBody.setMeta(meta);
        return responseBody;
    }

    public static ResponseBody error() {
        Meta meta = new Meta();
        meta.setMsg("错误");
        meta.setStatus(-1);
        ResponseBody responseBody = new ResponseBody();
        responseBody.setMeta(meta);
        return responseBody;
    }

    public ResponseBody data(Object object) {
        this.setData(object);
        return this;
    }
}
