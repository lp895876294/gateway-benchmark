/*
 * Copyright 2018 Netflix, Inc.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */
package com.framework.sc.zuul2;

import com.framework.sc.zuul2.filter.endpoint.Healthcheck;
import com.framework.sc.zuul2.filter.inbound.Routes;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.netflix.zuul.*;
import com.netflix.zuul.guice.GuiceFilterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User: michaels@netflix.com
 * Date: 5/8/15
 * Time: 6:15 PM
 */
public class TestZuulFiltersModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(TestZuulFiltersModule.class) ;

    @Override
    protected void configure() {
        LOG.info("Starting Groovy Filter file manager");

        bind(DynamicCodeCompiler.class).to(TestDynamicCodeCompiler.class);

        bind(FilterFactory.class).to(GuiceFilterFactory.class);

        bind(FilterUsageNotifier.class).to(BasicFilterUsageNotifier.class);

        LOG.info("Groovy Filter file manager started");
    }

    @Provides
    public FilterFileManager.FilterFileManagerConfig provideFilterFileManagerConfig() {

        List<String> classNames = Lists.newArrayList(
                Healthcheck.class.getName() ,
                Routes.class.getName()
        ) ;
        // Init the FilterStore.
        FilterFileManager.FilterFileManagerConfig filterConfig = new FilterFileManager.FilterFileManagerConfig(
                new String[]{}, classNames.toArray( new String[]{} ) , 5) ;

        LOG.info("加载filter->{}" , Joiner.on(",").join( classNames )) ;

        return filterConfig;
    }

}
