package org.dio.aula6;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ClientHttp {

    static ExecutorService executor = Executors.newFixedThreadPool(6, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            System.out.println("Thread created " + (thread.isDaemon() ? "daemon": "") + "Thread " +
                    "group: " + thread.getThreadGroup());
            return thread;
        }
    });

    public static void main(String[] args) throws IOException, InterruptedException {

//        HttpRequest request = HttpRequest.newBuilder().GET()
//                .uri(URI.create("https://docs.oracle.com/javase/10/language/"))
//                .build();
//
//        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println("Status code::" + response.statusCode());
//        System.out.println("Headers response::" + response.headers());
//        System.out.println(response.body());
        connectHttpClient();
        connectHttpClient2();
    }

    private static void connectHttpClient() {
        System.out.println("Running http/1.1 example...");

        try {
            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
                    .proxy(ProxySelector.getDefault())
                    .build();
            long start = System.currentTimeMillis();

            HttpRequest mainRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://http2.akamai.com/demo/h2_demo_frame.html"))
                    .build();

            HttpResponse<String> response = httpClient.send(mainRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code::" + response.statusCode());
            System.out.println("Response headers::" + response.headers());
            String responseBody = response.body();
            System.out.println(responseBody);

            List<Future<?>> futures = new ArrayList<>();

            responseBody
                    .lines()
                    .filter(line -> line.trim().startsWith("<img height"))
                    .map(line -> line.substring(line.indexOf("src='") + 5, line.indexOf("'/>")))
                    .forEach(image -> {
                        Future<?> imgFuture = executor.submit(() -> {
                            HttpRequest imgRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("https://http2.akamai.com"+image))
                                    .build();
                            try {
                                HttpResponse<String> imgResponse = httpClient.send(imgRequest, HttpResponse.BodyHandlers.ofString());
                                System.out.println("Image loaded " + imgResponse + " status " +
                                        "code::" + imgResponse.statusCode());

                            } catch (IOException | InterruptedException e) {
                                System.out.println("Error message during request to retrieve " +
                                        "image::" + image);
                                Thread.currentThread().interrupt();
                            }
                        });
                        futures.add(imgFuture);
                        System.out.println("Submitted img to future :: " + image);
                    });

            futures.forEach(f -> {
                try {
                    f.get();
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Unexpected error while loading");
                    Thread.currentThread().interrupt();
                }
            });

            long end = System.currentTimeMillis();
            System.out.println("Loading time::" + (end - start) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            executor.shutdown();
        }

    }

    private static void connectHttpClient2() {
        System.out.println("Running http/2 example...");

        try {
            HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
                    .proxy(ProxySelector.getDefault())
                    .build();
            long start = System.currentTimeMillis();

            HttpRequest mainRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://http2.akamai.com/demo/h2_demo_frame.html"))
                    .build();

            HttpResponse<String> response = httpClient.send(mainRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status code::" + response.statusCode());
            System.out.println("Response headers::" + response.headers());
            String responseBody = response.body();
            System.out.println(responseBody);

            List<Future<?>> futures = new ArrayList<>();

            responseBody
                    .lines()
                    .filter(line -> line.trim().startsWith("<img height"))
                    .map(line -> line.substring(line.indexOf("src='") + 5, line.indexOf("'/>")))
                    .forEach(image -> {
                        Future<?> imgFuture = executor.submit(() -> {
                            HttpRequest imgRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("https://http2.akamai.com"+image))
                                    .build();
                            try {
                                HttpResponse<String> imgResponse = httpClient.send(imgRequest, HttpResponse.BodyHandlers.ofString());
                                System.out.println("Image loaded " + imgResponse + " status " +
                                        "code::" + imgResponse.statusCode());

                            } catch (IOException | InterruptedException e) {
                                System.out.println("Error message during request to retrieve " +
                                        "image::" + image);
                                Thread.currentThread().interrupt();
                            }
                        });
                        futures.add(imgFuture);
                        System.out.println("Submitted img to future :: " + image);
                    });

            futures.forEach(f -> {
                try {
                    f.get();
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Unexpected error while loading");
                    Thread.currentThread().interrupt();
                }
            });

            long end = System.currentTimeMillis();
            System.out.println("Loading time::" + (end - start) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            executor.shutdown();
        }

    }
}
