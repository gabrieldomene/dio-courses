package org.dio.Threads;

public class ThreadExample {

    public static void main(String[] args) {

        GeneratePDF starterGeneratorPDF = new GeneratePDF();
        LoadingBar loadingBar = new LoadingBar(starterGeneratorPDF);

        starterGeneratorPDF.start();
        loadingBar.start();

    }
}

class GeneratePDF extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("Initiating PDF generation");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("PDF Generated");
    }
}

class LoadingBar extends Thread {
    private Thread startGeneratePDF;

    public LoadingBar(Thread startGeneratePDF) {
        this.startGeneratePDF = startGeneratePDF;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(500);
                if (!startGeneratePDF.isAlive()) break;
                System.out.println("Loading... ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
