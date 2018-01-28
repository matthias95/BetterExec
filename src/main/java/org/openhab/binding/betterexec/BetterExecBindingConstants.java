/**
 * Copyright (c) 2014,2018 by the respective copyright holders.
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.betterexec;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link BetterExecBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Matthias Bernard - Initial contribution
 */
@NonNullByDefault
public class BetterExecBindingConstants {

    public static final String BINDING_ID = "betterexec";

    // List of all Thing Type UIDs
    public final static ThingTypeUID COMMAND = new ThingTypeUID(BINDING_ID, "command");

    // List of all Channel ids
    public final static String EXECUTE = "execute";

    // Properties
    public final static String ONCOMMAND = "oncommand";
    public final static String OFFCOMMAND = "offcommand";
    public final static String TIMEOUT = "timeout";

}
