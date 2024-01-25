package com.lucy.springboot.config.auth;

// 세션값이 필요할 때 마다 httpSession.getAttribute 메소드를 호출하는 부분이 같은 코드가 반복되는 나쁜 코드이다.
// 이를 제거하고자 메소드 인자로 세션값을 바로 받을 수 있도록 해당 LoginUser 어노테이션 생성.

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 해당 어노테이션이 생성될 수 있는 위치 지정.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // @interface : 이 파일을 어노테이션 클래스로 지정한다.
}