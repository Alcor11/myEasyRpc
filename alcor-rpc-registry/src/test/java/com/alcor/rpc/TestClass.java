package com.alcor.rpc;

import lombok.Data;

import java.io.Serializable;

/**
 * @author guchun
 * @description TODO
 * @date 2022/6/10 20:39
 */
@Data
public class TestClass implements Serializable {
    private String name;
    private Integer age;
}
