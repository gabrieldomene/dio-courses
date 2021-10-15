package org.dio.Threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FutureExample {

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        House house = new House(new Bedroom());

        CopyOnWriteArrayList<? extends Future<?>> futures = house.getTaskToDo().stream()
                .map(task -> threadPool.submit(task::doTask)).collect(Collectors.toCollection(CopyOnWriteArrayList::new));

        while (!futures.stream().allMatch(Future::isDone)) {
            AtomicInteger tasksNotFinished = new AtomicInteger();
            for (Future<?> future : futures) {
                if (future.isDone()) {
                    try {
                        System.out.println("You finished " + future.get());
                        futures.remove(future);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } else {
                    tasksNotFinished.getAndIncrement();
                }
            }
            System.out.println("Number of tasks not finished " + tasksNotFinished);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
    }

}

class House {
    private final List<Room> rooms;

    House(Room... rooms) { this.rooms = Arrays.asList(rooms); }

    List<Task> getTaskToDo() {
        return this.rooms.stream()
                .map(Room::obtainTaskRoom)
                .reduce(new ArrayList<Task>(), (pivo, tasks) -> {
                    pivo.addAll(tasks);
                    return pivo;
                });
    }
}

interface Task {
    String doTask() throws InterruptedException;
}

abstract class Room {
    abstract List<Task> obtainTaskRoom();
}

class Bedroom extends Room {
    @Override
    List<Task> obtainTaskRoom() {
        return Arrays.asList(
                this::cleanTheCloset,
                this::cleanTheRoom,
                this::makeTheBed
        );
    }

    private String cleanTheCloset() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "cleanTheCloset";
    }

    private String cleanTheRoom() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "cleanTheRoom";
    }

    private String makeTheBed() {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "clean The Bed";
    }
}
