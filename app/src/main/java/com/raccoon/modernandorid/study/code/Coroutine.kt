package com.raccoon.modernandorid.study.code


/**
 * 코루틴
 *
 * 루틴
 *  - 메인루틴
 *  - 서브루틴
 *  - 코루틴
 *
 * 코루틴 쓰는 이유
 *  - 어떤 루틴이 시작되었더라도 우선 순위가 높은 다른 루틴이 있다면
 *  현재 실행 중인 루틴은 멈추고 (suspend) 우선 순위가 높은 다른 루틴을
 *  먼저 실행 시킬 수 있다.
 *  뭐 어쩃든 비동기
 *
 *  코루틴은 쓰레드가 아니다!
 *      쓰레드 || 코루틴
 *  - 메모리 구조 차이
 *      할당 || 공유
 *  - 수행방식의 차이
 *      선점형 || 비선점형 -> 쓰레드는 동시에 가능해서 병행성 // 코루틴은 빠르게 두가지를 일을 스위칭하면서 일하기때문에 동시성!
 *  - 코루틴의 장점
 *      메모리 || 오버헤드
 *  - 코루틴에서의 사용
 *      - suspend
 *
 * 코루틴 구조
 *  - Coroutine Scope
 *  - Coroutine Context
 *      - Dispatchers
 *          - Default : CPU 연산
 *          - IO
 *          - Main : UI 쓰레드
 *          - Unconfined : 사용하지 않음
 *  - Coroutine Builder
 *      - Job & Deferred : val job = scope.launch { ... } : Job으로 추상화 cance, join, start으로 흐름을 제어할 수 있음
 *  - 코루틴 지연
 *      - delay
 *      - join
 *      - await
 *  - 코루틴 취소
 *      - cancel
 *      - cancelAndJoin
 *      - withTimeout -> 시간 걸리면 exception 발생시켜버림
 *      - withTimeoutOrNull
 *  - 예외처리
 *  - 정리
 *      - CoroutineScope를 사용
 *      - CPU ? IO : Default : IO
 *      - ㅗ루틴 처리후 값이 나와야 하는가 ? No -> launch : YES -> async
 * */