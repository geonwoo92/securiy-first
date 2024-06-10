package com.exmple.webflux;

import com.exmple.webflux.common.config.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FluxAndMonoTest {


    @Mock
    CustomException customExceptionMono;

    @Mock
    CustomException customExceptionFlux;

    @BeforeEach
    void setUp() {
        customExceptionMono = new CustomException("Mono");
        customExceptionFlux = new CustomException("Flux");
    }


    @Test
    @DisplayName("Flux just() sample")
    void fluxJustTest() {
        List<String> names = new ArrayList<>();
        Flux<String> flux = Flux.just("김구", "윤봉길", "유관순").log();
        flux.subscribe(names::add);
        assertThat(names).isEqualTo(Arrays.asList("김구", "윤봉길", "유관순"));

    }

    @Test
    @DisplayName("Mono just() sample")
    void monoJustTest() {
        // Reactive Stream 에서는 Data, Error, Signal 중에서 signal을 사용한다.
        // signal은 데이터를 전달하는 방법이다.
        // onNext, onError, onComplete
        // Mono는 0 또는 1개의 데이터를 전달한다.

        List<Signal<Integer>> list = new ArrayList<>(4);
        final Integer[] result = new Integer[1];
        Mono<Integer> mono = Mono.just(1).log()
                .doOnEach(i -> {
                    list.add(i);
                    System.out.println("signal: " + 1);

                });
        mono.subscribe(i -> result[0] = i);
        assertThat(list.size()).isEqualTo(2);// 1개의 데이터를 전달한다.


    }

    @Test
    @DisplayName("Flux just() sample")
    void fluxCreateTest() {

        Flux<Integer> flux = Flux.create((FluxSink<Integer> sink) -> {
            sink.onRequest(requset -> {
                for (int i = 0; i < requset + 3; i++) {
                    sink.next(i);
                }
//                sink.complete();
            });

        });
        flux.subscribe(System.out::println);
    }

    @Test
    @DisplayName("Flux just() sample")
    void FluxGeneratorTest() {
        Flux<String> flux = Flux.generate(() -> {
            return 1; },
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3*state);
                    if (state == 10) {
                        sink.complete();
                    }
                    return state + 1;
                }

        );
        flux.subscribe(System.out::println);
    }


}