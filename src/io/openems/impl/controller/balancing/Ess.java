/*******************************************************************************
 * OpenEMS - Open Source Energy Management System
 * Copyright (c) 2016 FENECON GmbH and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *   FENECON GmbH - initial API and implementation and initial documentation
 *******************************************************************************/
package io.openems.impl.controller.balancing;

import io.openems.api.channel.Channel;
import io.openems.api.channel.IsRequired;
import io.openems.api.channel.WriteableChannel;
import io.openems.api.controller.IsThingMap;
import io.openems.api.controller.ThingMap;
import io.openems.api.device.nature.EssNature;
import io.openems.api.exception.InvalidValueException;

@IsThingMap(type = EssNature.class)
public class Ess extends ThingMap {

	@IsRequired(channelId = "ActivePower")
	public Channel activePower;

	@IsRequired(channelId = "AllowedCharge")
	public Channel allowedCharge;

	@IsRequired(channelId = "AllowedDischarge")
	public Channel allowedDischarge;

	@IsRequired(channelId = "GridMode")
	public WriteableChannel gridMode;

	@IsRequired(channelId = "MinSoc")
	public Channel minSoc;

	@IsRequired(channelId = "SetActivePower")
	public WriteableChannel setActivePower;

	@IsRequired(channelId = "Soc")
	public Channel soc;

	@IsRequired(channelId = "SystemState")
	public WriteableChannel systemState;

	public Ess(String thingId) {
		super(thingId);
	}

	public long useableSoc() throws InvalidValueException {
		return soc.getValue() - minSoc.getValue();
	}
}