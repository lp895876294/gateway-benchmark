package com.framework.gateway.sdk.simple.fegin;

import feign.Feign;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * @author toquery
 * @version 1
 */
public class OpenFeign {

    public static void main(String[] args) {

        /* create a pooling connection manager */
        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager();

        /* set our connection manager to support 50 simultaneous connections
         * total and limit the number of connections to each host to 5
         */
        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(5);

        /* create a new HttpClient using our Pooled Connection Manager */
        CloseableHttpClient httpClient = HttpClients.custom().
                setConnectionManager(connectionManager).build();


        Bank github = Feign.builder()
                .target(Bank.class, "http://192.168.0.135:8081");

        // Fetch and print a list of the contributors to this library.
        String contributors = github.contributors();
        contributors = github.contributors();
    }
}
