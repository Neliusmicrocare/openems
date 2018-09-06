package io.openems.edge.bridge.mc_comms.api.task;

import io.openems.common.exceptions.OpenemsException;
import io.openems.edge.bridge.mc_comms.BridgeMCComms;
import io.openems.edge.common.taskmanager.ManagedTask;
import io.openems.edge.common.taskmanager.Priority;

public interface WriteMCCommsTask extends MCCommsTask, ManagedTask {

    void executeWrite(BridgeMCComms bridge) throws OpenemsException;

    default Priority getPriority() { return Priority.HIGH; }

}