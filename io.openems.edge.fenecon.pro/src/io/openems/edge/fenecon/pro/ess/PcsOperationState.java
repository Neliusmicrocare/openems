package io.openems.edge.fenecon.pro.ess;

import io.openems.edge.common.channel.doc.OptionsEnum;

public enum PcsOperationState implements OptionsEnum {
	UNDEFINED(-1, "Undefined"), //
	SELF_CHECKING(0, "Self-checking"), //
	STANDBY(1, "Standby"), //
	OFF_GRID_PV(2, "Off-Grid PV"), //
	OFF_GRID(3, "Off-Grid"), //
	ON_GRID(4, "ON_GRID"), //
	FAIL(5, "Fail"), //
	BYPASS_1(6, "ByPass 1"), //
	BYPASS_2(7, "ByPass 2");

	private final int value;
	private final String name;

	private PcsOperationState(int value, String name) {
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