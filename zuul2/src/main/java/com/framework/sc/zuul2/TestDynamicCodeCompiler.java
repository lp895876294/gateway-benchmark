package com.framework.sc.zuul2;

import com.netflix.zuul.DynamicCodeCompiler;

import java.io.File;

public class TestDynamicCodeCompiler implements DynamicCodeCompiler {
    @Override
    public Class compile(String sCode, String sName) throws Exception {
        return null;
    }

    @Override
    public Class compile(File file) throws Exception {
        return null;
    }
}
