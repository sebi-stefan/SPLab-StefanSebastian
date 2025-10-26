package org.example.splabstefansebastian.command;

public interface CommandExecutor {

    <T> T executeCommand(Command<T> command);

}
