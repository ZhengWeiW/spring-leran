package com.zcc.springleran.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @description:
 * 1. JDK动态代理
 * 使用场景：当被代理的目标对象实现了至少一个接口时，Spring AOP会默认使用JDK动态代理。
 * 工作原理：JDK动态代理基于接口生成代理类，通过反射机制调用目标对象的方法。代理类和目标对象实现了相同的接口，代理对象在调用方法时会将调用转发给目标对象，并在调用前后执行额外的逻辑（如增强逻辑）。
 * 优点：不需要额外的依赖库，JDK自带支持。
 * 缺点：只能代理实现了接口的类。
 * 2. CGLIB代理
 * 使用场景：当被代理的目标对象没有实现任何接口时，Spring AOP会选择使用CGLIB代理。此外，如果明确配置了使用CGLIB代理，或者目标对象是final的，
 * 或者目标方法是final的、static的、private的，Spring AOP也会使用CGLIB代理（但请注意，对于final类和方法，实际上只能使用JDK动态代理，
 * 因为CGLIB无法代理它们）。然而，这里有一个需要澄清的点：尽管有说法认为final类和方法会使用JDK动态代理，但实际上在Spring AOP中，
 * 如果目标对象是final的或者目标方法是final的、static的、private的，通常会导致AOP无法应用，因为这些方法或类无法被代理。
 * 工作原理：CGLIB动态代理通过继承目标对象创建代理类，并重写目标对象的方法来实现代理功能。代理类在调用方法时会先执行额外的逻辑（如增强逻辑），
 * 然后再调用目标对象的方法。
 * 优点：可以代理没有实现接口的类。
 * 缺点：需要额外的依赖库（CGLIB库）。
 *
 *
 *
 *
 *
 * 代理失效
 * 1. 非Spring管理的对象
 * 场景描述：如果目标对象不是由Spring容器管理的Bean，那么AOP将无法对其进行拦截。
 * 原因：Spring AOP是基于Spring容器的，它只能对Spring容器中的Bean进行代理和拦截。
 * 2. 私有方法调用
 * 场景描述：当一个Bean内部的方法直接调用同一个Bean内部的另一个私有方法时，AOP无法拦截这个内部方法调用。
 * 原因：AOP是基于代理的，私有方法无法被子类继承或重写，因此无法被代理对象拦截。
 * 3. 静态方法调用
 * 场景描述：如果目标方法是静态的，那么AOP将无法拦截它。
 * 原因：静态方法是属于类的，不是对象的，因此无法通过对象的代理来拦截。
 * 4. final方法调用
 * 场景描述：如果目标类或方法被final修饰，那么AOP将无法代理它们。
 * 原因：final修饰的方法或类无法被子类重写或继承，因此AOP无法生成代理对象来拦截这些方法。
 * 5. 类内部自调用   演示类com.zcc.springleran.aop.MyService
 * 场景描述：当一个Bean内部的方法直接调用同一个Bean内部的另一个方法时（无论该方法是否私有），AOP可能无法拦截这个内部调用（取决于调用方式）。
 * 原因：AOP是基于代理的，如果调用是通过原始对象而非代理对象进行的，那么AOP将无法拦截。
 * 6. 异步方法调用
 * 场景描述：如果目标方法使用了Spring的异步执行机制（如@Async注解），AOP拦截器可能无法正常工作。
 * 原因：异步方法在运行时会创建新的线程或使用线程池，AOP拦截器可能无法跟踪到这些新线程中的方法调用。
 * 7. 配置错误
 * 场景描述：AOP的配置出现错误，如切点表达式错误、切面顺序设置错误等。
 * 原因：配置错误会导致AOP无法正确识别需要拦截的方法或无法按预期顺序执行增强逻辑。
 * 8. 代理对象无法创建
 * 场景描述：在某些情况下，如果目标对象没有实现任何接口或没有提供合适的构造函数，Spring AOP可能无法为其创建代理对象。
 * @author: zcc
 * @createDate: 2024/7/18
 * @version: 1.0
 */
@Aspect
@Component
public class LogAspect {
    /** 切入点表达式 */
    @Pointcut("execution(* com.zcc.springleran.aop.SysUserService.*(..))")
    public void logPointCut(){}

    /** 前置通知 */
    @Before("logPointCut()")
    public void before(){
        System.out.println("前置通知");
    }

    @Before("execution(* com.zcc.springleran.aop.MyService.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("Before method: " + joinPoint.getSignature().getName());
    }

}
