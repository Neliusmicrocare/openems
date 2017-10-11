package io.openems.backend.openemswebsocket.session;

import org.java_websocket.WebSocket;

import com.google.gson.JsonObject;

import io.openems.backend.metadata.api.device.MetadataDevices;
import io.openems.common.session.SessionData;

public class OpenemsSessionData extends SessionData {
	private final MetadataDevices devices;
	private final WebSocket websocket;

	public OpenemsSessionData(WebSocket websocket, MetadataDevices devices) {
		this.devices = devices;
		this.websocket = websocket;
	}

	public WebSocket getWebsocket() {
		return websocket;
	}

	public MetadataDevices getDevices() {
		return devices;
	}

	@Override
	public JsonObject toJsonObject() {
		JsonObject j = new JsonObject();
		j.add("devices", this.devices.toJson());
		return j;
	}
}
