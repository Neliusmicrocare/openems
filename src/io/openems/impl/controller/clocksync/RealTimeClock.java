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
package io.openems.impl.controller.clocksync;

import io.openems.api.channel.WriteChannel;
import io.openems.api.controller.IsThingMap;
import io.openems.api.controller.ThingMap;
import io.openems.api.device.nature.realtimeclock.RealTimeClockNature;

@IsThingMap(type = RealTimeClockNature.class)
public class RealTimeClock extends ThingMap {

	public final WriteChannel<Long> year;
	public final WriteChannel<Long> month;
	public final WriteChannel<Long> day;
	public final WriteChannel<Long> hour;
	public final WriteChannel<Long> minute;
	public final WriteChannel<Long> second;

	public RealTimeClock(RealTimeClockNature rtc) {
		super(rtc);
		year = rtc.rtcYear(); // not required!
		month = rtc.rtcMonth(); // not required!
		day = rtc.rtcDay(); // not required!
		hour = rtc.rtcHour(); // not required!
		minute = rtc.rtcMinute(); // not required!
		second = rtc.rtcSecond(); // not required!
	}
}