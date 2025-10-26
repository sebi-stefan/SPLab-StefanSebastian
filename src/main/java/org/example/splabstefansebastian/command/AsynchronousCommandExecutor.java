package org.example.splabstefansebastian.command;

import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class AsynchronousCommandExecutor implements CommandExecutor{

    private ExecutorService executorService;

    public AsynchronousCommandExecutor() {
        this.executorService = Executors.newFixedThreadPool(10);
    }

    @Override
    public <T> T executeCommand(Command<T> command) {
        Future<T> future = executorService.submit(command::execute);
        return null;
    }
}
