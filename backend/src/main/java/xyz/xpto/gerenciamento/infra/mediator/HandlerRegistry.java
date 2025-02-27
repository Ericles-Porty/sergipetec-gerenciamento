// package xyz.xpto.gerenciamento.infra.mediator;

// import java.util.Map;
// import java.util.concurrent.ConcurrentHashMap;

// import org.springframework.stereotype.Component;
// import org.springframework.web.server.ResponseStatusException;

// @Component
// public class HandlerRegistry {

//     private final Map<Class<?>, Object> handlers = new ConcurrentHashMap<>();

//     public <T> void registerHandler(Class<T> commandType, Object handler) {
//         handlers.put(commandType, handler);
//     }

//     @SuppressWarnings("unchecked")
//     public <T, R> R handle(T command) {
//         Object handler = handlers.get(command.getClass());
//         if (handler == null) {
//             throw new IllegalArgumentException("Handler não registrado para: " + command.getClass());
//         }
//         try {
//             return (R) handler.getClass().getMethod("handle", command.getClass()).invoke(handler, command);
//         } catch (ResponseStatusException e) {
//             throw e;
//         } catch (Exception e) {
//             throw new RuntimeException("Falha ao executar handler", e);
//         }
//     }
// }