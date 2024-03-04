package com.studio.mutiny;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.subscription.UniSubscriber;
import io.smallrye.mutiny.subscription.UniSubscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.concurrent.CompletableFuture.delayedExecutor;

public class UniEx {

    private Logger logger = LoggerFactory.getLogger(UniEx.class);

    public void main(String[] args) throws InterruptedException {
        // exemplo1();
        // ex2();
        // ex3();
        // ex4();
        // exUniFromSupplier();
        // ex5();
        // uniFromSupplierAndState();
        // uniDeferred();
        // uniFromEmitter();
        // uniFromEmitterAndState();
        // uniFromFailure();
        // uniFromCompleteStage();
        // uniMisc();
        new UniEx().uniDelay();

    }

    private void uniDelay() {
        Uni.createFrom().item(666)
                .onItem()
                .delayIt().by(Duration.ofSeconds(1))
                .map(String::valueOf)
                .subscribe().with(logger::info);

        logger.info("wait...");

        Uni.createFrom().item(666)
                .onItem()
                .delayIt().until(n -> Uni.createFrom()
                        .completionStage(CompletableFuture.supplyAsync(() -> "Ok",
                                CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS))))
                .map(String::valueOf)
                .subscribe().with(logger::info);
    }

    private  void uniMisc() {
        Uni.createFrom().nothing()
                .onItem().transform(Object::toString)
                .subscribe().with(logger::info, failure -> System.out.println(failure.getMessage()));

        Uni.createFrom().voidItem()
                .onItem().transform(Object::toString)
                .subscribe().with(logger::info, failure -> System.out.println(failure.getMessage()));

        Uni.createFrom().nullItem()
                .onItem().transform(Object::toString)
                .subscribe().with(logger::info, failure -> System.out.println(failure.getMessage()));

        Uni.createFrom().optional(Optional.of("Hello"))
                .onItem().transform(Object::toString)
                .subscribe().with(logger::info, failure -> System.out.println(failure.getMessage()));

        Uni.createFrom().converter(i -> Uni.createFrom().item("[" + i + "]"), 10)
                .subscribe().with(logger::info, failure -> System.out.println(failure.getMessage()));
    }

    /**
     * This code snippet creates a CompletableFuture to supply a string "Hello!"
     * after a delay,
     * then transforms the string to uppercase. It then converts the
     * CompletableFuture to a Uni,
     * logs the result, and subscribes to it, printing the result or failure
     * message. Finally,
     * it waits for 2 seconds before completing.
     *
     * @throws InterruptedException
     */
    private void uniFromCompleteStage() throws InterruptedException {

        var cs = CompletableFuture.supplyAsync(() -> "Hello!", delayedExecutor(3, TimeUnit.SECONDS))
                .thenApply(String::toUpperCase);

        Uni.createFrom()
                .completionStage(cs).log()
                .subscribe().with(logger::info, failure -> System.out.println(failure.getMessage()));

        Thread.sleep(4000);
    }

    private void exemplo1() {
        Uni.createFrom().item("hello")
                .onItem().transform(item -> item + " mutiny")
                .onItem().transform(String::toUpperCase)
                .subscribe().with(item -> logger.info(">> " + item));
    }

    private void ex2() {
        Uni<String> uni1 = Uni.createFrom().item("hello");
        Uni<String> uni2 = uni1.onItem().transform(item -> item + " mutiny");
        Uni<String> uni3 = uni2.onItem().transform(String::toUpperCase);

        uni3.subscribe().with(item -> System.out.println(">> " + item));
    }

    private void ex3() {
        Uni<String> uni = Uni.createFrom().item("hello");
        uni.onItem().transform(item -> item + " mutiny");
        uni.onItem().transform(String::toUpperCase);
        // uni.subscribe().with(item -> System.out.println(">> " + item));
    }

    private void ex4() {
        Uni<String> uni = Uni.createFrom().item("Hello, world!");

        uni.subscribe().withSubscriber(new UniSubscriber<String>() {
            @Override
            public void onSubscribe(UniSubscription subscription) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onItem(String item) {
                System.out.println("onItem: " + item);
            }

            @Override
            public void onFailure(Throwable failure) {
                System.out.println("onFailure: " + failure.getMessage());
            }
        });
    }

    private void exUniFromSupplier() {
        Random random = new Random();
        Uni<Integer> uniFromSupplier = Uni.createFrom().item(random::nextInt);
        for (var i = 0; i < 5; i++) {
            uniFromSupplier
                    .map(String::valueOf)
                    .subscribe().with(logger::info);
        }
    }

    private void ex5() {
        Uni.createFrom().item(1)
                .onItem().transform(i -> "hello-" + i).onItem()
                .delayIt().by(Duration.ofMillis(1000))
                .subscribe().with(logger::info);
    }

    private void uniFromSupplierAndState() {
        Uni<Integer> uniFromSupplierAndState = Uni.createFrom().item(AtomicInteger::new, i -> i.addAndGet(10));

        for (var i = 0; i < 5; i++) {
            uniFromSupplierAndState
                    .map(String::valueOf)
                    .subscribe().with(logger::info);
        }
    }

    /**
     * A description of the entire Java function.
     *
     * @return description of return value
     */

    private void uniDeferred() {
        AtomicLong ids = new AtomicLong();
        Uni<Long> deferredUni = Uni.createFrom().deferred(() -> Uni.createFrom().item(ids::incrementAndGet));

        for (var i = 0; i < 5; i++) {
            deferredUni.map(String::valueOf).subscribe().with(logger::info);
        }
    }

    /**
     * A private static function that creates a Uni from an emitter and subscribes
     * to it,
     * printing the emitted value. It throws InterruptedException.
     */
    private void uniFromEmitter() throws InterruptedException {

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        CountDownLatch emitterLatch = new CountDownLatch(1);

        Uni<String> uniFromEmitter = Uni.createFrom().emitter(emitter -> {
            forkJoinPool.submit(() -> {
                emitter.complete("Hello");
                emitterLatch.countDown();
            });
        });

        uniFromEmitter.subscribe().with(logger::info);

        emitterLatch.await();
    }

    /**
     * Generates a Uni from an emitter and state, and subscribes to it 5 times.
     */
    private void uniFromEmitterAndState() {

        Uni<Integer> uniFromEmitterAndState = Uni.createFrom().emitter(AtomicInteger::new, (i, e) -> e.complete(i.addAndGet(10)));

        for (var i = 0; i < 5; i++) {
            uniFromEmitterAndState .map(String::valueOf).subscribe().with(logger::info);
        }
    }

    private void uniFromFailure() {

        Uni.createFrom().failure(new IOException("Boom"))
                .map(String::valueOf)
                .subscribe().with(logger::info, failure -> logger.error(failure.getMessage()));

        Uni.createFrom().failure(() -> new IOException("Badaboom"))
                .map(String::valueOf)
                .subscribe().with(logger::info, failure -> logger.error(failure.getMessage()));
    }

}
