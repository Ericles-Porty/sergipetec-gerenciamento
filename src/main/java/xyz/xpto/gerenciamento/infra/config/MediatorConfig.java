// package xyz.xpto.gerenciamento.infra.config;

// import org.springframework.beans.BeansException;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.ApplicationContextAware;
// import org.springframework.context.annotation.Configuration;

// import xyz.xpto.gerenciamento.application.mediator.HandlerComponent;
// import xyz.xpto.gerenciamento.infra.mediator.HandlerRegistry;

// import java.util.Arrays;

// @Configuration
// public class MediatorConfig implements ApplicationContextAware {

//     private final HandlerRegistry registry;

//     public MediatorConfig(HandlerRegistry registry) {
//         this.registry = registry;
//     }

//     @Override
//     public void setApplicationContext(ApplicationContext context) throws BeansException {
//         Arrays.stream(context.getBeanNamesForAnnotation(HandlerComponent.class))
//                 .forEach(beanName -> {
//                     Object handler = context.getBean(beanName);
//                     Arrays.stream(handler.getClass().getDeclaredMethods())
//                             .filter(m -> m.getName().equals("handle"))
//                             .findFirst()
//                             .ifPresent(m -> {
//                                 Class<?> paramType = m.getParameterTypes()[0];
//                                 registry.registerHandler(paramType, handler);
//                             });
//                 });
//     }
// }