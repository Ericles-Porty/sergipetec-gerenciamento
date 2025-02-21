// package xyz.xpto.gerenciamento.infra.mediator;

// import org.springframework.context.ApplicationContext;
// import org.springframework.stereotype.Component;

// import xyz.xpto.gerenciamento.application.mediator.Mediator;

// @Component
// public class SpringMediator implements Mediator {

//     private final ApplicationContext context;
//     private final HandlerRegistry registry;

//     public SpringMediator(ApplicationContext context, HandlerRegistry registry) {
//         this.context = context;
//         this.registry = registry;
//     }

//     @Override
//     public <T> T execute(Object command) {
//         return registry.handle(command);
//     }

//     @Override
//     public <T> T execute(Class<?> commandClass) {
//         return registry.handle(context.getBean(commandClass));
//     }
// }