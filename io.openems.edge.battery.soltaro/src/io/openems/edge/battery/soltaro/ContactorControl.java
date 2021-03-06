package io.openems.edge.battery.soltaro;

import io.openems.edge.common.channel.doc.OptionsEnum;

public enum ContactorControl implements OptionsEnum {
	UNDEFINED(-1, "Undefined"), //
	CUT_OFF(0, "Cut off"), //
	CONNECTION_INITIATING(1, "Connection initiating"), //
	ON_GRID(3, "On grid");

	int value;
	String name;

	private ContactorControl(int value, String name) {
		this.value = value;
		this.name = name;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public OptionsEnum getUndefined() {
		return UNDEFINED;
	}
}