/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.betterexec;

import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link BetterExecBinding} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Matthias Bernard - Initial contribution
 */
public class BetterExecBindingConstants {

    public static final String BINDING_ID = "betterexec";

    // List of all Thing Type UIDs
    public final static ThingTypeUID COMMAND = new ThingTypeUID(BINDING_ID, "command");

    // List of all Channel ids
    public final static String EXECUTE = "execute";

    // Properties
    public final static String ONCOMMAND = "oncommand";
    public final static String OFFCOMMAND = "offcommand";
}
