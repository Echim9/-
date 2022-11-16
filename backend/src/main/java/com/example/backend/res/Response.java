package com.example.backend.res;

import com.example.backend.enums.Rcode;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

@Data
public class Response<T> {

    private Rcode code;

    private String msg;

    private T data;

    public Response() {
    }

    public Response(builder builder) {
        this.code = builder.code;
        if (!StringUtils.isEmpty(builder.msg)) {
            this.msg = builder.msg;
        }
        if (!ObjectUtils.isEmpty(builder.data)) {
            this.data = (T) builder.data;
        }
    }

    public static builder builder() {
        return new builder();
    }

    public static class builder<T> {

        Rcode code;

        String msg;

        T data;

        public builder code(Rcode code) {
            this.code = code;
            return this;
        }

        public builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public builder data(T data) {
            this.data = data;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }
}