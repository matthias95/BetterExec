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
package org.openhab.binding.betterexec.handler;

import static org.openhab.binding.betterexec.BetterExecBindingConstants.*;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link BetterExecHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Matthias Bernard - Initial contribution
 */
@NonNullByDefault
public class BetterExecHandler extends BaseThingHandler {

    private Logger logger = LoggerFactory.getLogger(BetterExecHandler.class);

    private static ExecutorService execQueue = Executors.newSingleThreadExecutor();
    private Runtime runtime = Runtime.getRuntime();

    public BetterExecHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        final String oncommand = (String) this.getConfig().get(ONCOMMAND);
        final String offcommand = (String) this.getConfig().get(OFFCOMMAND);
        final long timeout = ((BigDecimal) this.getConfig().get(TIMEOUT)).longValue();
        final String commandStr;
        if (channelUID.getId().equals(EXECUTE)) {
            if (command instanceof OnOffType) {
                logger.debug("------> " + command.toString() + "<--------");
                if (command.equals(OnOffType.ON)) {
                    commandStr = oncommand;
                } else if (command.equals(OnOffType.OFF)) {
                    commandStr = offcommand;
                } else {
                    logger.error("BetterExecHandler", "Unknown OnOffType: " + command);
                    return;
                }
                synchronized (execQueue) {
                    execQueue.submit(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final Process p = runtime.exec(commandStr);
                                p.waitFor(timeout, TimeUnit.SECONDS);
                                p.destroyForcibly();
                            } catch (Exception e) {
                                logger.error("Failed to execute" + commandStr);
                                e.printStackTrace();
                            }
                        }
                    });
                }

            }
        }

    }

    @Override
    public void dispose() {
        logger.debug("dispose");
        super.dispose();
    }

    @Override
    public void initialize() {
        // TODO: Initialize the thing. If done set status to ONLINE to indicate proper working.
        // Long running initialization should be done asynchronously in background.
        updateStatus(ThingStatus.ONLINE);

        // Note: When initialization can NOT be done set the status with more details for further
        // analysis. See also class ThingStatusDetail for all available status details.
        // Add a description to give user information to understand why thing does not work
        // as expected. E.g.
        // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
        // "Can not access device as username and/or password are invalid");
    }
}